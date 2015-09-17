package services

import mock.MockUrlFactoryService
import models.configuration.{AppWebcam, AppWebcamApi}
import org.junit.runner.RunWith
import org.specs2.mock._
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json

@RunWith(classOf[JUnitRunner])
class UrlFactoryServiceSpec extends Specification with Mockito {

  private val appWebcam = AppWebcam("http://localhost", "user", "test")
  private val appWebcamApi = AppWebcamApi("/mock/url?", List())

  "URL factory" should {

    "build without parameters" in {
      val service = new MockUrlFactoryService(appWebcam, appWebcamApi)
      service.build("getMotionDetectConfig", None) ===
        "http://localhost/mock/url?usr=user&pwd=test&cmd=getMotionDetectConfig"
    }

    "build with parameters" in {
      val appWebcamApiWithParams = appWebcamApi.copy(actions = List("param1", "param2"))
      val service = new MockUrlFactoryService(appWebcam, appWebcamApiWithParams)
      val parameters = Json.obj("param1" -> 1, "param2" -> 2)
      service.build("getMotionDetectConfig", Some(parameters)) ===
        "http://localhost/mock/url?usr=user&pwd=test&cmd=getMotionDetectConfig&param1=1&param2=2"
    }
  }
}
