package controllers

import play.api._
import play.api.mvc._
import controllers.event.EventController
import services.event.EventServiceComponentImpl
import repositories.event.EventRepositoryComponentImpl

object Application extends EventController
                   with EventServiceComponentImpl
                   with EventRepositoryComponentImpl {

  def index = Action {
    Ok("Your new application is ready.")
  }

}