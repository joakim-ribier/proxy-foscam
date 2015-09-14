package services.impl

import com.google.inject.Inject
import com.pellucid.caseconfig._
import com.typesafe.config.Config
import models.configuration.{AppWebcam, AppWebcamApi}
import play.api.libs.json.{JsObject, JsValue}
import services.UrlFactoryService

class UrlFactoryServiceImpl @Inject() (
  appConfig: Config) extends UrlFactoryService {

  private val appWebcam = appConfig.get[AppWebcam]("webcam")
  private val appWebcamApi = appConfig.get[AppWebcamApi]("webcam.api")

  override def build(cmd: String, paramsOpt: Option[JsValue]): String = {
    val auth: String = s"usr=${appWebcam.user}&pwd=${appWebcam.password}"

    val builder = StringBuilder.newBuilder
    builder.append(appWebcam.address)
      .append(appWebcamApi.prefix)
      .append(auth)
      .append("&cmd=").append(cmd)

    paramsOpt match {
      case Some(params) => {
        val parameters: String = params.as[JsObject].value
          .map(f => s"${f._1}=${f._2}").mkString("&")
        
        builder.append("&").append(parameters).toString()
      }
      case None => builder.toString()
    }
  }
}
