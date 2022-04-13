import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.restassured.RestAssured.`given`
import model.Token
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.{equalTo, notNullValue}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

class CreateTokenTests extends AnyFunSuite with BaseConfiguration with BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    setupContext("auth")
    val mapper = JsonMapper.builder().addModule(DefaultScalaModule).build()
  }

  test("Create Token with correct values") {

    val tokenTests = Token()

    val response = given
      .body(tokenTests)
      .when()
      .post()
      .`then`()

    response.statusCode(HttpStatus.SC_OK)
    response.body("token", notNullValue())
  }

  test("Create Token with wrong username") {
    val tokenBody = Token(username = "admin1")

    val response = `given`.body(tokenBody).when().post().`then`()

    response.statusCode(HttpStatus.SC_OK)
    response.body("reason", equalTo("Bad credentials"))
  }

  test("Create Token with wrong password") {
    val tokenBody = Token(password = "pass123")

    val response = `given`.body(tokenBody).when().post().`then`()

    response.statusCode(HttpStatus.SC_OK)
    response.body("reason", equalTo("Bad credentials"))
  }

}
