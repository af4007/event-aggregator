package repositories.event

import domain.event.Event
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

trait EventRepositoryComponent {
    val eventRepository: EventRepository
    
    trait EventRepository {
        
        def sendEvent(event: Event): Event
		
		def findEvent(eventType: String): Option[Event]
		
		def countEvent(eventType: String, from: Long, to: Long): Map[Long, Event]
        
        def getEvents(from: Long, to: Long): Map[Long, Event]
    }
}

trait EventRepositoryComponentImpl extends EventRepositoryComponent {
    override val eventRepository = new EventRepositoryImpl
    
    class EventRepositoryImpl extends EventRepository {
        
        /*val eventTypeMap = new ConcurrentHashMap[String, Event]
		val eventTimeMap = new ConcurrentHashMap[Long, Event]*/
          
		var eventTypeMap: Map[String, Event] = Map()
		var eventTimeMap: Map[Long, Event] = Map()		  
        
		override def sendEvent(event: Event): Event = {
            eventTypeMap += (event.eventType -> event)
			eventTimeMap += (event.timeStamp -> event)
			event
        }
		
		override def findEvent(eventType: String): Option[Event] = {
            eventTypeMap.get(eventType)
        }
		override def getEvents(from: Long, to: Long): Map[Long, Event] = {
			eventTimeMap
		}
		
		override def countEvent(eventType: String, from: Long, to: Long): Map[Long, Event] = {
			eventTimeMap
		}
    }    
}