package controllers

import javax.inject.Inject

import com.typesafe.config.Config
import play.api.libs.json.Json

class FakeController @Inject() (val appConfig: Config) extends ApiController {

  /**
   * Send correct body format according to the FOSCAM API.
   *
   */
  def test = AuthenticationAction { request =>
    Ok("""
      <CGI_Result>
        <result>0</result>
        <isEnable>1</isEnable>
      </CGI_Result>
    """)
  }
}