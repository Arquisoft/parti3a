package es.uniovi.asw.gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ParticipationSystem extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
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
		"Origin" -> "http://localhost:8090",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_43 = Map("Accept" -> "*/*")

	val headers_52 = Map("Accept" -> "text/event-stream")

	val headers_53 = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Origin" -> "http://localhost:8090")



	val scn = scenario("ParticipationSystem")
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
			.get("/login_styles.css"),
            http("request_5")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_6")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.com")
			.formParam("password", "user")
			.resources(http("request_7")
			.get("/login_styles.css"),
            http("request_8")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_9")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "user1@me.com")
			.formParam("password", "user1")
			.resources(http("request_10")
			.get("/login_styles.css"),
            http("request_11")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(3)
			.exec(http("request_3")
			.post("/pasoParticipants")
			.headers(headers_0)
			.resources(http("request_4")
			.get("/datos_styles.css"),
            http("request_5")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(8)
		.exec(http("request_6")
			.get("/login_styles.css"))
		.pause(1)
		.exec(http("request_7")
			.post("/pasoPSystem")
			.headers(headers_0))
		.pause(5)
		.exec(http("request_22")
			.post("/mostrar?sugerencia=16")
			.headers(headers_3)
			.formParam("ver", "sugerencia en cucumber"))
		.pause(3)
		.exec(http("request_23")
			.post("/listarSugerencias")
			.headers(headers_3)
			.formParam("listar", ""))
		.pause(2)
		.exec(http("request_24")
			.post("/mostrar?sugerencia=20")
			.headers(headers_3)
			.formParam("ver", "Contenido"))
		.pause(5)
		.exec(http("request_25")
			.post("/ordenarPorFecha")
			.headers(headers_3)
			.formParam("ordenarDate", ""))
		.pause(2)
		.exec(http("request_26")
			.post("/listarSugerencias")
			.headers(headers_3)
			.formParam("listar", "")
			.resources(http("request_27")
			.post("/logout")
			.headers(headers_3)
			.formParam("logout", ""),
            http("request_28")
			.get("/login_styles.css"),
            http("request_29")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(7)
		.exec(http("request_30")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "admin@me.co")
			.formParam("password", "admin")
			.resources(http("request_31")
			.get("/login_styles.css"),
            http("request_32")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_33")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "admin@me.com")
			.formParam("password", "admin1")
			.resources(http("request_34")
			.get("/login_styles.css"),
            http("request_35")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(3)
		.exec(http("request_36")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "admin@me.com")
			.formParam("password", "admin1")
			.resources(http("request_37")
			.get("/login_styles.css"),
            http("request_38")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(5)
		.exec(http("request_39")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "admin@me.com")
			.formParam("password", "admin")
			.resources(http("request_40")
			.get("/login_styles.css"),
            http("request_41")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_42")
			.post("/pasoDashboard")
			.headers(headers_3)
			.resources(http("request_43")
			.get("/webjars/jquery/3.2.0/jquery.min.js")
			.headers(headers_43),
            http("request_44")
			.get("/webjars/sockjs-client/sockjs.min.js")
			.headers(headers_43),
            http("request_45")
			.get("/styles.css"),
            http("request_46")
			.get("/webjars/stomp-websocket/stomp.min.js")
			.headers(headers_43),
            http("request_47")
			.get("/bourbon")
			.check(status.is(404)),
            http("request_48")
			.get("/stomp/info?t=1494430146254")
			.headers(headers_43)))
		.pause(3)
		.exec(http("request_49")
			.get("/stomp/iframe.html")
			.headers(headers_0)
			.resources(http("request_50")
			.get("/sugerencias/20")
			.headers(headers_0),
            http("request_51")
			.get("/stomp/374/vtcf4jno/htmlfile?c=_jp.ax0cqws")
			.headers(headers_0),
            http("request_52")
			.get("/stomp/374/yqfbpchn/eventsource")
			.headers(headers_52),
            http("request_53")
			.post("/stomp/374/jace3vzo/xhr_streaming?t=1494430146885")
			.headers(headers_53),
            http("request_54")
			.get("/webjars/jquery/3.2.0/jquery.min.js")
			.headers(headers_43),
            http("request_55")
			.get("/webjars/stomp-websocket/stomp.min.js")
			.headers(headers_43),
            http("request_56")
			.get("/sugerencias/styles.css")
			.check(status.is(500)),
            http("request_57")
			.get("/webjars/sockjs-client/sockjs.min.js")
			.headers(headers_43),
            http("request_58")
			.get("/sugerencias/styles.css")
			.check(status.is(500)),
            http("request_59")
			.get("/stomp/info?t=1494430149326")
			.headers(headers_43)))
		.pause(2)
		.exec(http("request_60")
			.get("/stomp/iframe.html")
			.headers(headers_0))
		.pause(4)
		.exec(http("request_61")
			.get("/stomp/iframe.html")
			.headers(headers_0)
			.resources(http("request_62")
			.get("/stomp/899/1nh03wgf/htmlfile?c=_jp.avzlkim")
			.headers(headers_0),
            http("request_63")
			.get("/stomp/899/nhlwfvyi/eventsource")
			.headers(headers_52),
            http("request_64")
			.post("/stomp/899/ap1cahsx/xhr_streaming?t=1494430149949")
			.headers(headers_53),
            http("request_65")
			.get("/styles.css"),
            http("request_66")
			.get("/stomp/info?t=1494430153143")
			.headers(headers_43)))
		.pause(4)
		.exec(http("request_67")
			.get("/stomp/iframe.html")
			.headers(headers_0)
			.resources(http("request_68")
			.get("/cerrarSesion")
			.headers(headers_0),
            http("request_69")
			.post("/stomp/817/jfcn05tw/xhr_streaming?t=1494430153769")
			.headers(headers_53),
            http("request_70")
			.get("/stomp/817/lsgxbpx3/eventsource")
			.headers(headers_52),
            http("request_71")
			.get("/login_styles.css"),
            http("request_72")
			.get("/bourbon")
			.check(status.is(404))))
		.pause(6)
		.exec(http("request_73")
			.post("/validarse")
			.headers(headers_3)
			.formParam("email", "admin@me.com")
			.formParam("password", "admin")
			.resources(http("request_74")
			.get("/login_styles.css"),
            http("request_75")
			.get("/bourbon")
			.check(status.is(404)),
            http("request_76")
			.post("/pasoPSystem")
			.headers(headers_3)))
		.pause(2)
		.exec(http("request_77")
			.post("/mostrarAdmin?sugerencia=31")
			.headers(headers_3)
			.formParam("ver", "sugerencia en cucumber"))
		.pause(3)
		.exec(http("request_78")
			.post("/listarSugerenciasAdmin")
			.headers(headers_3)
			.formParam("listar", ""))
		.pause(2)
		.exec(http("request_79")
			.post("/mostrarAdmin?sugerencia=20")
			.headers(headers_3)
			.formParam("ver", "Contenido"))
		.pause(5)
		.exec(http("request_80")
			.post("/ordenarPorFechaAdmin")
			.headers(headers_3)
			.formParam("ordenarDate", ""))
		.pause(4)
		.exec(http("request_81")
			.post("/logout")
			.headers(headers_3)
			.formParam("logout", "")
			.resources(http("request_82")
			.get("/login_styles.css"),
            http("request_83")
			.get("/bourbon")
			.check(status.is(404))))

	setUp(scn.inject(rampUsers(1000) over(1000 seconds))).protocols(httpProtocol)

}