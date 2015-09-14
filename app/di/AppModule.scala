package di

import com.google.inject.{AbstractModule, Provides}
import com.typesafe.config.{Config, ConfigFactory}
import play.api.Application
import services.impl.{ProxyServiceImpl, UrlFactoryServiceImpl}
import services.{ProxyService, UrlFactoryService}

class AppModule extends AbstractModule {

  def configure() = {
    bind(classOf[ProxyService]).to(classOf[ProxyServiceImpl])
    bind(classOf[UrlFactoryService]).to(classOf[UrlFactoryServiceImpl])
  }

  @Provides
  def providesAppConfiguration(app: Application): Config = {
    ConfigFactory.load(app.configuration.underlying)
  }
}
