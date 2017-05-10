
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class parti3UserParticipationSystem extends Simulation {

	val httpProtocol = http
		.baseURL("https://parti3a.herokuapp.com")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("es-ES,es;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_3 = Map(
		"Accept-Encoding" -> "gzip, deflate, br",
		"Origin" -> "https://parti3a.herokuapp.com",
		"Upgrade-Insecure-Requests" -> "1")

    val uri1 = "https://parti3a.herokuapp.com:443"

	val scn = scenario("parti3UserParticipationSystem")
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
		.pause(3)
		.exec(http("request_3")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.co")
			.formParam("password", "user1")
			.resources(http("request_4")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_5")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404))))
		.pause(5)
		.exec(http("request_6")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.com")
			.formParam("password", "user")
			.resources(http("request_7")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_8")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404))))
		.pause(3)
		.exec(http("request_9")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.com")
			.formParam("password", "user1")
			.resources(http("request_10")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_11")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404)),
            http("request_12")
			.post("/pasoPSystem")
			.headers(headers_3)))
		.pause(6)
		.exec(http("request_13")
			.post("/mostrar?sugerencia=20")
			.headers(headers_3)
			.formParam("ver", "Contenido"))
		.pause(2)
		.exec(http("request_14")
			.post("/nuevoComentario?sugerencia=20")
			.headers(headers_3)
			.formParam("nuevoComentario", "")
			.resources(http("request_15")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_16")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_17")
			.post("/anadirComentario")
			.headers(headers_3)
			.formParam("contenido", "Probando desde Gatling!"))
		.pause(3)
		.exec(http("request_18")
			.post("/ordenarPorFecha")
			.headers(headers_3)
			.formParam("ordenarDate", ""))
		.pause(11)
		.exec(http("request_19")
			.post("/listarSugerencias")
			.headers(headers_3)
			.formParam("listar", ""))
		.pause(3)
		.exec(http("request_20")
			.post("/mostrar?sugerencia=16")
			.headers(headers_3)
			.formParam("ver", "sugerencia en cucumber"))
		.pause(2)
		.exec(http("request_21")
			.post("/listarSugerencias")
			.headers(headers_3)
			.formParam("listar", "")
			.resources(http("request_22")
			.post("/logout")
			.headers(headers_3)
			.formParam("logout", ""),
            http("request_23")
			.get("/login_styles.css")
			.headers(headers_1),
            http("request_24")
			.get("/bourbon")
			.headers(headers_1)
			.check(status.is(404))))

	setUp(scn.inject(rampUsers(2000) over(300 seconds))).protocols(httpProtocol) 
}