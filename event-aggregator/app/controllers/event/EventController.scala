package controllers.event

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import services.event.EventServiceComponent
import domain.event.Event

import scala.collection.mutable.Queue

trait EventController extends Controller {
    self: EventServiceComponent =>
    
    implicit val eventReads: Reads[EventResource] = ((JsPath \ "eventType").read[String] and  (JsPath \ "timeStamp").read[Long])(EventResource.apply _)
    
    implicit val eventWrites = new Writes[Event] {
        override def writes(event: Event): JsValue = {
            Json.obj(
                "eventType" -> event.eventType,
                "timeStamp" -> event.timeStamp
            )
        }
    }
	
    
	// curl -X POST http://localhost:9000/sendEvent -H "Content-Type: application/json" -d "{\"eventType\": \"pageView\",\"timeStamp\":1433942949}"
    def sendEvent = Action(parse.json) {request =>
        unmarshalEventResource(request, (resource: EventResource) => {
            val event = Event(resource.eventType, resource.timeStamp)
			eventService.sendEvent(event)
            Created
        })
    }
	
	// http://localhost:9000/findEvent/pageView
	def findEvent(eventType: String) = Action {
        val event = eventService.findEvent(eventType)
		if (event.isDefined) {
            Ok(Json.toJson(event))
        } else {
            NotFound
        }
    }
	
	def dispatcher = Action { implicit request =>
		request.queryString.get("action").flatMap(_.headOption).getOrElse("invalid") match {
		  case "countEvents" => countEvents
		  case "getEvents" => getEvents
		  case _          => BadRequest("Action not allowed!")
		}
	}
	
	// http://localhost:9000/dispatcher?action=countEvents&eventType=pageView&startTime=1133942949&to=1435998988
	def countEvents(implicit request: RequestHeader) = {
		val eventType = request.queryString.get("eventType").flatMap(_.headOption).getOrElse("")
		val startTime = request.queryString.get("startTime").flatMap(_.headOption).getOrElse("")
		val endTime = request.queryString.get("endTime").flatMap(_.headOption).getOrElse("")
		val eventCountMap = eventService.countEvent(eventType, startTime.toLong, endTime.toLong)
		
		val queue = new Queue[JsValue]
		eventCountMap.foreach(f =>  queue+=(Json.obj("timeStamp"->f._1, "count" -> f._2.count { x => true })))
		Ok(Json.toJson(queue.toArray))
	}
	
	// http://localhost:9000/dispatcher?action=getEvents&eventType=pageView&startTime=1133942949&endTime=1435998988
	def getEvents(implicit request: RequestHeader) = {
		val eventType = request.queryString.get("eventType").flatMap(_.headOption).getOrElse("")
		val startTime = request.queryString.get("startTime").flatMap(_.headOption).getOrElse("")
		val endTime = request.queryString.get("endTime").flatMap(_.headOption).getOrElse("")
		val eventMap = eventService.getEvents(eventType,startTime.toLong, endTime.toLong)
		
		val queue = new Queue[JsValue]
		eventMap.values.foreach {e => queue+=(Json.obj("eventType" -> e.eventType, "timeStamp" -> e.timeStamp))}
		Ok(Json.toJson(queue.toArray))
	}
	
    private def unmarshalEventResource(request: Request[JsValue],
                                      block: (EventResource) => Result): Result = {
        request.body.validate[EventResource].fold(
            valid = block,
            invalid = (e => {
                val error = e.mkString
                Logger.error(error)
                BadRequest(error)
            })
        )
    }
}

case class EventResource (val eventType: String, val timeStamp: Long)