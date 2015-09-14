package controllers

import javax.inject.Inject

import com.typesafe.config.Config
import play.api.libs.json.Json

class TestController @Inject() (
  val appConfig: Config) extends ApiController {

  def test() = AuthenticationAction { request =>
    Ok(Json.obj("uri" -> request.uri))
  }
}