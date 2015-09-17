package mock

import models.configuration.{AppWebcam, AppWebcamApi}
import services.impl.UrlFactoryServiceImpl

class MockUrlFactoryService(
  mockAppWebcam: AppWebcam,
  mockAppWebcamApi: AppWebcamApi) extends UrlFactoryServiceImpl(null) {

  override def appWebcam = mockAppWebcam
  override def appWebcamApi = mockAppWebcamApi

}
