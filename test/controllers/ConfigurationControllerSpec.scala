package controllers

import di.TestGuiceApplicationBuilder
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class ConfigurationControllerSpec extends Specification with TestGuiceApplicationBuilder {

  "Configuration controller" should {

    "get the 'webcam.api' configuration" in new WithApplication(application) {
      route(FakeRequest(GET, "/api/configuration/webcam")) must beSome.which { response =>
        status(response) === OK
        contentType(response) === Some("application/json")
        (contentAsJson(response) \ "prefix").as[String] === "/api/fake/test?"
        (contentAsJson(response) \ "actions").as[Seq[String]] must not be empty
      }
    }
  }
}