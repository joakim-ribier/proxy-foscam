package controllers

import javax.inject.Inject

import com.pellucid.caseconfig._
import com.typesafe.config.Config
import models.configuration.{AppWebcamApi, AppWebcamApiDetail}
import play.api.libs.json.{Json, JsObject, JsValue}
import play.api.mvc._
import services.ProxyService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProxyController @Inject()(
  val appConfig: Config,
  proxyService: ProxyService) extends ApiController {

  private val appWebcamApi = appConfig.get[AppWebcamApi]("webcam.api")

  def set(cmd: String) = AuthenticationAction.async(BodyParsers.parse.json) { request =>
    checkData(cmd, request.body) match {
      case false => {
        Future.successful(
          Conflict(
            Json.obj("error" -> s"The '$cmd' request is not allowed on the server.")))
      }
      case true => {
        proxyService.get(cmd, Some(request.body)).map(Ok(_))
      }
    }
  }

  private def checkData(cmd: String, params: JsValue): Boolean = {
    appWebcamApi.actions.contains(cmd) match {
      case true => {
        val webcamApiDetail = appConfig.get[AppWebcamApiDetail](s"webcam.api.detail.$cmd")
        val jsObject = params.as[JsObject]
        (jsObject.fields.map(_._1).map { field =>
          webcamApiDetail.params.contains(field)
        }).filter(_ == false).isEmpty
      }
      case _ => false
    }
  }

  def get(cmd: String) = AuthenticationAction.async { request =>
    appWebcamApi.actions.contains(cmd) match {
      case false => {
        Future.successful(
          Conflict(
            Json.obj("error" -> s"The '$cmd' request is not allowed on the server.")))
      }
      case true => {
        proxyService.get(cmd, None).map(Ok(_))
      }
    }
  }
}