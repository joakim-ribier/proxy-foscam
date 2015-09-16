package server

import di.{TestAppModule, TestGuiceApplicationBuilder}
import org.specs2.mutable.Specification
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.WS
import play.api.test.WithServer

import scala.concurrent.Await
import scala.concurrent.duration._

class ProxyServerSpec extends Specification with TestGuiceApplicationBuilder {

  "GET [/api/proxy/:cmd]" should {

    "get a content" in new WithServer(app = application, port = 3333) {
      val url = WS.url("http://localhost:3333/api/proxy/getMotionDetectConfig").get()
      val response = Await.result(url, 10 seconds)

      response.status === OK
      Json.parse(response.body) === Json.obj("result" -> "0", "isEnable" -> "1")
    }
  }

  override def mockModule = {
    new TestAppModule {
      override def configure() = { }
    }
  }

}
