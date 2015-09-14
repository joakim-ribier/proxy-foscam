package controllers

import com.pellucid.caseconfig._
import com.typesafe.config.Config
import models.configuration.AppProxy
import play.api.mvc.{ActionBuilder, Controller, Request, Result}

import scala.concurrent.Future

trait ApiController extends Controller {

  val appConfig: Config

  private val appProxy = appConfig.get[AppProxy]("proxy")

  object AuthenticationAction extends ActionBuilder[Request] {

    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
      appProxy.authentication match {
        case true => {
          val userOpt = request.getQueryString("user")
          val passwordOpt = request.getQueryString("password")
          (userOpt, passwordOpt) match {
            case (Some(user), Some(password))
              if (user == appProxy.user && password == appProxy.password) => {
              block(request)
            }
            case _ => Future.successful(Unauthorized)
          }
        }
        case false => block(request)
      }
    }
  }
}
