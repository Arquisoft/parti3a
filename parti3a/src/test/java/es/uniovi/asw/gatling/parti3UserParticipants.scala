
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class parti3UserParticipants extends Simulation {

	val httpProtocol = http
		.baseURL("https://parti3a.herokuapp.com")
		.inferHtmlResources()
		.acceptHeader("text/css,*/*;q=0.1")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("es-ES,es;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Origin" -> "https://parti3a.herokuapp.com",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "https://parti3a.herokuapp.com:443"

	val scn = scenario("parti3UserParticipants")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/login_styles.css"),
            http("request_2")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(6)
		.exec(http("request_3")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.co")
			.formParam("password", "user1")
			.resources(http("request_4")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_5")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.com")
			.formParam("password", "user1")
			.resources(http("request_6")
			.get("/login_styles.css"),
            http("request_7")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(1)
		.exec(http("request_8")
			.post("/pasoParticipants")
			.headers(headers_3)
			.resources(http("request_9")
			.get("/datos_styles.css"),
            http("request_10")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(5)
		.exec(http("request_11")
			.get("/cerrarSesion")
			.headers(headers_0)
			.resources(http("request_12")
			.get("/login_styles.css"),
            http("request_13")
			.get("/bourbon")
			.check(status.is(404))))

	setUp(scn.inject(rampUsers(2000) over(180 seconds))).protocols(httpProtocol)
}