package controllers

import di.TestGuiceApplicationBuilder
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class ProxySpec extends Specification with TestGuiceApplicationBuilder {

  "GET [/api/proxy/:cmd]" should {

    "have a 409 to GET with a bad 'cmd'" in new WithApplication(application) {
      route(FakeRequest(GET, "/api/proxy/cmd-not-exists")) must beSome.which { response =>
        status(response) === CONFLICT
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "error").as[String] === "The 'cmd-not-exists' request is not allowed on the server."
      }
    }

    "get a content" in new WithApplication(application) {
      route(FakeRequest(GET, "/api/proxy/getMotionDetectConfig")) must beSome.which { response =>
        status(response) === OK
        contentType(response) === Some("application/json")
        contentAsJson(response) === Json.obj("return" -> "ok")
      }
    }
  }

  "PUT [/api/proxy/:cmd]" should {

    "get a content" in new WithApplication(application) {
      route(FakeRequest(PUT, "/api/proxy/setMotionDetectConfig").withJsonBody(Json.obj())) must beSome.which { response =>
        status(response) === OK
        contentType(response) === Some("application/json")
        contentAsJson(response) === Json.obj("return" -> "ok")
      }
    }

    "have a 409 to PUT with a bad 'cmd'" in new WithApplication(application) {
      route(FakeRequest(PUT, "/api/proxy/cmd-not-exists").withJsonBody(Json.obj())) must beSome.which { response =>
        status(response) === CONFLICT
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "error").as[String] === "The 'cmd-not-exists' request is not allowed on the server."
      }
    }

    "have a 409 to PUT with a correct 'cmd' but bad parameter" in new WithApplication(application) {
      route(FakeRequest(PUT, "/api/proxy/setMotionDetectConfig").withJsonBody(Json.obj("bad-param" -> "value"))) must beSome.which { response =>
        status(response) === CONFLICT
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "error").as[String] === "The 'setMotionDetectConfig' request is not allowed on the server."
      }
    }
  }
}