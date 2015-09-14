package controllers

import di.TestGuiceApplicationBuilder
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification with TestGuiceApplicationBuilder {

  "Application controller" should {

    "have a redirect from '/' to '/api/status'" in new WithApplication(application) {
      route(FakeRequest(GET, "/")) must beSome.which { response =>
        status(response) === OK
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "status").as[String] === "ok"
      }
    }

    "get the status of the application" in new WithApplication(application) {
      route(FakeRequest(GET, "/api/status")) must beSome.which { response =>
        status(response) === OK
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "status").as[String] === "ok"
      }
    }
  }
}