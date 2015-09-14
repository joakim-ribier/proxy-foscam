package di

import play.api.Configuration
import play.api.inject.guice.GuiceApplicationBuilder

trait TestGuiceApplicationBuilder {

  protected val application = new GuiceApplicationBuilder()
    .configure(
      Configuration(
        "proxy.authentication" -> false,
        "webcam.api.prefix" -> "/api/test"))
    .build
}
