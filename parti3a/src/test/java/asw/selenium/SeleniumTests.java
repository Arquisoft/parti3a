package asw.selenium;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.Application;
import es.uniovi.asw.ParticipationSystem.Mensajeria.KafkaProducer;
import es.uniovi.asw.cucumber.SeleniumUtils;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;
import es.uniovi.asw.util.Encrypter;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class SeleniumTests {
	private WebDriver driver;
	private static String URL = "http://localhost:9000/";
	
	@Value("${local.server.port}")
	private int port;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Before
	public void setUp() {
		URL = "http://localhost:" + port + "/";
		driver = new HtmlUnitDriver(true);
		driver.get(URL);
		driver.navigate().to(URL);
		System.out.println(driver.getPageSource());
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void testLoginIncorrecto() {
		//E-mail correcto y clave incorrecta
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("1234");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario o contraseña incorrecto", 10);
		
		//E-mail incorrecto y clave correcta
		driver.get(URL);
		driver.navigate().to(URL);
		
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("hola@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario o contraseña incorrecto", 10);
	}
	
	@Test
	public void testLoginParticipant() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("user1@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		SeleniumUtils.textoPresentePagina(driver, "Participants");
		SeleniumUtils.textoPresentePagina(driver, "Participation System");
		
		driver.findElement(By.id("pasoParticipants")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sus datos:", 10);
		assertEquals("Participants3a - Datos", driver.getTitle());
		SeleniumUtils.textoPresentePagina(driver, "E-mail: user1@me.com");
		SeleniumUtils.textoPresentePagina(driver, "Nombre: Juan Rodríguez García");
		SeleniumUtils.textoPresentePagina(driver, "NIF: 14321234Z");
		SeleniumUtils.textoPresentePagina(driver, "Dirección: Oviedo");
		SeleniumUtils.textoPresentePagina(driver, "Nacionalidad: ESP");
		
		//Accedemos a la raíz y vemos que nos redirige
		driver.get(URL);
		driver.navigate().to(URL);
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
	}
	
	@Test
	public void testLoginAdministrator() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoDashboard")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Propuestas actualmente en el sistema", 10);
		assertEquals("Dashboard3a - Listado sugerencias", driver.getTitle());
		
		//Accedemos a la raíz y vemos que nos redirige
		driver.get(URL);
		driver.navigate().to(URL);
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
	}
	
	@Test
	public void testCambiaPasswordCorrectoParticipant() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("user1@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		
		driver.findElement(By.id("pasoParticipants")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sus datos:", 10);
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.id("newpassword")).click();
		driver.findElement(By.id("newpassword")).sendKeys("user2");
		
		driver.findElement(By.id("submitPassword")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Intentamos loguearnos con la nueva clave
		driver.navigate().to(URL + "cerrarSesion");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenido/a", 10);
		
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("user1@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user2");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		
		//Hemos entrado correctamente, restauramos la clave anterior
		driver.findElement(By.id("pasoParticipants")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sus datos:", 10);
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user2");
		
		driver.findElement(By.id("newpassword")).click();
		driver.findElement(By.id("newpassword")).sendKeys("user1");
		
		driver.findElement(By.id("submitPassword")).click();
	}
	
	@Test
	public void testCambiaPasswordIncorrectoParticipant() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("user1@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoParticipants")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sus datos:", 10);
		
		//Clave anterior incorrecta
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user2");
		
		driver.findElement(By.id("newpassword")).click();
		driver.findElement(By.id("newpassword")).sendKeys("newpassword");
		
		driver.findElement(By.id("submitPassword")).click();
		
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Contraseña inválida", 10);
		//Nueva clave vacía
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.id("newpassword")).click();
		driver.findElement(By.id("newpassword")).clear();
		
		driver.findElement(By.id("submitPassword")).click();
		System.out.println(driver.getPageSource());
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Nueva contraseña no debe estar en blanco", 10);
		//Nueva clave igual a la anterior
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.id("newpassword")).click();
		driver.findElement(By.id("newpassword")).sendKeys("user1");
		
		driver.findElement(By.id("submitPassword")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "La nueva contraseña debe ser diferente", 10);
	}
	
	@Test
	public void testCambiaEmailIncorrectoParticipant() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("user1@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoParticipants")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sus datos:", 10);
		//Formato e-mail inválido
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("3");
		
		driver.findElement(By.id("submitEmail")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Formato de e-mail incorrecto", 10);
	}
	
	@Test
	public void testParticipantsAdminError() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		
		//Intentamos acceder a participants y vemos que nos redirige
		driver.navigate().to(URL + "datos");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
	}
	
	@Test
	public void testDashboardParticipantError() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("user1@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("user1");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		
		//Intentamos acceder al dashboard y vemos que nos redirige
		driver.navigate().to(URL + "sugerencias");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
	}
	
	@Test
	public void testDashboardAdmin() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoDashboard")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Propuestas actualmente en el sistema", 10);
		
		SeleniumUtils.textoPresentePagina(driver, "Sugerencia prueba");
	}
	
	@Test
	public void testVistaSugerenciaAdmin() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoDashboard")).click();
		System.out.println(driver.getPageSource());
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Propuestas actualmente en el sistema", 10);
		
		driver.findElements(By.linkText("Detalles")).get(0).click();
		System.out.println("A VER=====");
		System.out.println(driver.getPageSource());
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Detalles de la sugerencia", 10);
	}

	@Test
	public void testActualizaTiempoRealDashboard() {
		System.out.println(driver.getPageSource());
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoDashboard")).click();
		System.out.println(driver.getPageSource());
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Propuestas actualmente en el sistema", 10);
		
		SeleniumUtils.textoPresentePagina(driver, "Sugerencia prueba");
		
		Citizen citizen = new Citizen(
				"Juan", "Rodríguez García", "user1@me.com", new Date(),
				"Oviedo", "ESP", "14321234Z");

		String encryptedPass = Encrypter.getInstance().makeSHA1Hash("user1");
		User user = new User("user1", encryptedPass, citizen);
		
		Suggestion sugerencia = new Suggestion("Sugerencia testing", 
				new Category("Categoría testing"), 
				user);
		
		kafkaProducer.sendSugerencia(sugerencia);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLogout() {
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("admin@me.com");
		
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		
		//Accedemos a la página principal y vemos que nos redirige a la misma
		driver.navigate().to(URL);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		
		//Cerramos sesión y vemos que ya no nos redirige
		driver.navigate().to(URL + "cerrarSesion");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenido/a", 10);
		
		driver.navigate().to(URL);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenido/a", 10);
	}
}
