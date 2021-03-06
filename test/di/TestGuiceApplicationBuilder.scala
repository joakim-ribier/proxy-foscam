package di

import com.google.inject.AbstractModule
import mock.MockProxyService
import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder
import services.ProxyService

trait TestGuiceApplicationBuilder {

  protected val application = new GuiceApplicationBuilder()
    .configure(
      Configuration(
        "proxy.authentication" -> false,
        "webcam.address" -> "http://localhost:3333",
        "webcam.api.prefix" -> "/api/fake/test?"))
    .overrides(mockModule)
    .build

  protected def mockModule = new TestAppModule
}

class TestAppModule extends AbstractModule {

  def configure() = {
    bind(classOf[ProxyService]).to(classOf[MockProxyService])
  }
}
