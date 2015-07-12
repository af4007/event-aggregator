package services.event

import domain.event.Event
import repositories.event.EventRepositoryComponent

trait EventServiceComponent {
    val eventService: EventService    
    trait EventService {
        
        def sendEvent(event: Event): Event
		
		def findEvent(eventType: String): Option[Event]
        
        def countEvent(eventType: String, startTime: Long, endTime: Long): Map[Long, Iterable[Event]]
           
		def getEvents(eventType: String, startTime: Long, endTime: Long) : Map[Long, Event]
    }
}

trait EventServiceComponentImpl extends EventServiceComponent {
    self: EventRepositoryComponent =>
    
    override val eventService = new EventServiceImpl
    
    class EventServiceImpl extends EventService {
       
        override def sendEvent(event: Event): Event = {
            eventRepository.sendEvent(event)
        }
		
		override def findEvent(eventType: String): Option[Event] = {
            eventRepository.findEvent(eventType)
        }
        
        override def countEvent(eventType: String, startTime: Long, endTime: Long) : Map[Long, Iterable[Event]] = {
            eventRepository.countEvent(eventType,startTime,endTime).filter(_._1 >= startTime).filter(_._1 <= endTime).filter(_._2.eventType == eventType).values.groupBy { x => x.timeStamp / (60) }
        }
       
        override def getEvents(eventType: String, startTime: Long, endTime: Long): Map[Long, Event] = {
            eventRepository.getEvents(startTime,endTime).filter(_._2.eventType.equalsIgnoreCase(eventType)).filter(_._1 >= startTime).filter(_._1 <= endTime)
        }
    }
}
