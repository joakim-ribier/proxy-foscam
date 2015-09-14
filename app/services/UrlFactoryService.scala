package services

import play.api.libs.json.JsValue

trait UrlFactoryService {

  def build(cmd: String, params: Option[JsValue]): String
}
