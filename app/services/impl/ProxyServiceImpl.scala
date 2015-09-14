package services.impl

import com.google.inject.Inject
import com.typesafe.config.Config
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSClient
import services.{ProxyService, UrlFactoryService}

import scala.concurrent.Future
import scala.xml.XML._

class ProxyServiceImpl @Inject()(
  appConfig: Config,
  wsClient: WSClient,
  urlFactoryService: UrlFactoryService) extends ProxyService {

  override def get(cmd: String, params: Option[JsValue]): Future[JsValue] = {
    val url = urlFactoryService.build(cmd, params)
    wsClient.url(url).withFollowRedirects(false).get().map { response =>
      parseBody(response.body)
        .map(f => Json.obj(f._1 -> f._2))
        .fold(Json.obj()) { (obj, json) =>
        obj ++ json
      }
    }
  }

  private def parseBody(value: String): Seq[(String, String)] = {
    val xml = loadString(value)
    xml.child.flatMap { node =>
      node.label match {
        case "#PCDATA" => None
        case _ => Some((node.label, node.text))
      }
    }
  }
}