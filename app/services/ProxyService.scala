package services

import play.api.libs.json.JsValue

import scala.concurrent.Future

trait ProxyService {

  def get(cmd: String, params: Option[JsValue]): Future[JsValue]
}
