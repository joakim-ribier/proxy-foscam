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

  private val cmdNotExists = "cmd-not-exists"

  "Proxy controller" should {

    "have a 409 to GET with a bad 'cmd'" in new WithApplication(application) {
      route(FakeRequest(GET, s"/api/proxy/$cmdNotExists")) must beSome.which { response =>
        status(response) === CONFLICT
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "error").as[String] === s"The '$cmdNotExists' request is not allowed on the server."
      }
    }

    "have a 409 to PUT with a bad 'cmd'" in new WithApplication(application) {
      route(FakeRequest(PUT, s"/api/proxy/$cmdNotExists").withJsonBody(Json.obj())) must beSome.which { response =>
        status(response) === CONFLICT
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "error").as[String] === s"The '$cmdNotExists' request is not allowed on the server."
      }
    }
  }
}