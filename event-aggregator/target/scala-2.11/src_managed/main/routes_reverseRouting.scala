// @SOURCE:G:/workspace/event-aggregator/conf/routes
// @HASH:23f7ac404375d9f6f1e771b773054f7375172dab
// @DATE:Wed Jun 10 20:15:07 IST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:8
def dispatcher(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "dispatcher")
}
                        

// @LINE:6
def sendEvent(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "sendEvent")
}
                        

// @LINE:7
def findEvent(eventType:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "findEvent/" + implicitly[PathBindable[String]].unbind("eventType", dynamicString(eventType)))
}
                        

}
                          
}
                  


// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:8
def dispatcher : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.dispatcher",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "dispatcher"})
      }
   """
)
                        

// @LINE:6
def sendEvent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.sendEvent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sendEvent"})
      }
   """
)
                        

// @LINE:7
def findEvent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.findEvent",
   """
      function(eventType) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "findEvent/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("eventType", encodeURIComponent(eventType))})
      }
   """
)
                        

}
              
}
        


// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:8
def dispatcher(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.dispatcher(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "dispatcher", Seq(), "GET", """""", _prefix + """dispatcher""")
)
                      

// @LINE:6
def sendEvent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.sendEvent(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "sendEvent", Seq(), "POST", """ Home page""", _prefix + """sendEvent""")
)
                      

// @LINE:7
def findEvent(eventType:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.findEvent(eventType), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "findEvent", Seq(classOf[String]), "GET", """""", _prefix + """findEvent/$eventType<[^/]+>""")
)
                      

}
                          
}
        
    