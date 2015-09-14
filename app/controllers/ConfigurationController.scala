package controllers

import javax.inject.Inject

import com.pellucid.caseconfig._
import com.typesafe.config.Config
import models.configuration.AppWebcamApi
import play.api.libs.json.Json

class ConfigurationController @Inject() (
  val appConfig: Config) extends ApiController {

  private val appWebcamApi = appConfig.get[AppWebcamApi]("webcam.api")

  def webcam = AuthenticationAction {
    Ok(Json.obj(
      "prefix" -> appWebcamApi.prefix,
      "actions" -> appWebcamApi.actions
    ))
  }
}