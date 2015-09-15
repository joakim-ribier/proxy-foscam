package mock

import play.api.libs.json.{JsValue, Json}
import services.ProxyService

import scala.concurrent.Future

class MockProxyService extends ProxyService {

  override def get(cmd: String, params: Option[JsValue]): Future[JsValue] = {
    Future.successful(Json.obj("return" -> "ok"))
  }
}
