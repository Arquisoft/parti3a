
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class parti3aAdminParticipationSystem extends Simulation {

	val httpProtocol = http
		.baseURL("https://parti3a.herokuapp.com")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")

	val headers_0 = Map(
		"Accept-Encoding" -> "gzip, deflate, sdch, br",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/css,*/*;q=0.1",
		"Accept-Encoding" -> "gzip, deflate, sdch, br")

	val headers_3 = Map(
		"Origin" -> "https://parti3a.herokuapp.com",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "https://parti3a.herokuapp.com:443"

	val scn = scenario("parti3aAdminParticipationSystem")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_2")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404))))
		.pause(5)
		.exec(http("request_3")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "admin@me.com")
			.formParam("password", "admin")
			.resources(http("request_4")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_5")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404)),
            http("request_6")
			.post("/pasoPSystem")
			.headers(headers_3)))
		.pause(2)
		.exec(http("request_7")
			.post("/anadirPalabra")
			.headers(headers_3)
			.formParam("palabra", "palabra_prohibida"))
		.pause(2)
		.exec(http("request_8")
			.post("/mostrarAdmin?sugerencia=16")
			.headers(headers_3)
			.formParam("ver", "sugerencia en cucumber"))
		.pause(1)
		.exec(http("request_9")
			.post("/ordenarPorFechaAdmin")
			.headers(headers_3)
			.formParam("ordenarDate", ""))
		.pause(6)
		.exec(http("request_10")
			.post("/listarSugerenciasAdmin")
			.headers(headers_3)
			.formParam("listar", ""))
		.pause(2)
		.exec(http("request_11")
			.post("/mostrarAdmin?sugerencia=31")
			.headers(headers_3)
			.formParam("ver", "sugerencia en cucumber"))
		.pause(4)
		.exec(http("request_12")
			.post("/listarSugerenciasAdmin")
			.headers(headers_3)
			.formParam("listar", ""))
		.pause(2)
		.exec(http("request_13")
			.post("/logout")
			.headers(headers_3)
			.formParam("logout", "")
			.resources(http("request_14")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_15")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404))))

	setUp(scn.inject(rampUsers(200) over(180 seconds))).protocols(httpProtocol) 
}