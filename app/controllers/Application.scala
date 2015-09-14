package controllers

import buildinfo.BuildInfo
import play.api.libs.json.Json
import play.api.mvc._

class Application extends Controller {

  def index() = status()

  def status() = Action {
    Ok(Json.obj(
      "status" -> "ok",
      "version" -> BuildInfo.version,
      "build" -> BuildInfo.builtAtString
    ))
  }
}
