// @SOURCE:G:/workspace/event-aggregator/conf/routes
// @HASH:23f7ac404375d9f6f1e771b773054f7375172dab
// @DATE:Wed Jun 10 20:15:07 IST 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_sendEvent0_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sendEvent"))))
private[this] lazy val controllers_Application_sendEvent0_invoker = createInvoker(
controllers.Application.sendEvent,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "sendEvent", Nil,"POST", """ Home page""", Routes.prefix + """sendEvent"""))
        

// @LINE:7
private[this] lazy val controllers_Application_findEvent1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("findEvent/"),DynamicPart("eventType", """[^/]+""",true))))
private[this] lazy val controllers_Application_findEvent1_invoker = createInvoker(
controllers.Application.findEvent(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "findEvent", Seq(classOf[String]),"GET", """""", Routes.prefix + """findEvent/$eventType<[^/]+>"""))
        

// @LINE:8
private[this] lazy val controllers_Application_dispatcher2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dispatcher"))))
private[this] lazy val controllers_Application_dispatcher2_invoker = createInvoker(
controllers.Application.dispatcher,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "dispatcher", Nil,"GET", """""", Routes.prefix + """dispatcher"""))
        
def documentation = List(("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sendEvent""","""controllers.Application.sendEvent"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """findEvent/$eventType<[^/]+>""","""controllers.Application.findEvent(eventType:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dispatcher""","""controllers.Application.dispatcher""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_sendEvent0_route(params) => {
   call { 
        controllers_Application_sendEvent0_invoker.call(controllers.Application.sendEvent)
   }
}
        

// @LINE:7
case controllers_Application_findEvent1_route(params) => {
   call(params.fromPath[String]("eventType", None)) { (eventType) =>
        controllers_Application_findEvent1_invoker.call(controllers.Application.findEvent(eventType))
   }
}
        

// @LINE:8
case controllers_Application_dispatcher2_route(params) => {
   call { 
        controllers_Application_dispatcher2_invoker.call(controllers.Application.dispatcher)
   }
}
        
}

}
     