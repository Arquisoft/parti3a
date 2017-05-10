package es.uniovi.asw.cucumber;

import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;

@SuppressWarnings("deprecation")
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class ParticipantsLoginStep {

	private WebDriver driver;

	@Given("^the participant is in the login page$")
	public void isInLoginPage() {
		driver = new HtmlUnitDriver(true);
		driver.get("http://locahost:8090/");
		driver.navigate().to("http://localhost:8090/");
	}

	@Then("^introduces credentials, \"(.+)\" and \"(.+)\" into the login form$")
	public void insertCredentials(String mail, String psw) {
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).sendKeys(mail);
		
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys(psw);
	}

	@When("^pushes the \"Log in\" button and selects participants$")
	public void pushLogIn() {
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoParticipants")).click();
	}

	@And("^he gets redirected to the participants view$")
	public void isInDashboard() {
		assertEquals("Participants3a - Datos", driver.getTitle());
		SeleniumUtils.textoPresentePagina(driver, "E-mail: user1@me.com");
		SeleniumUtils.textoPresentePagina(driver, "Nombre: Juan Rodríguez García");
		SeleniumUtils.textoPresentePagina(driver, "NIF: 14321234Z");
		SeleniumUtils.textoPresentePagina(driver, "Dirección: Oviedo");
		SeleniumUtils.textoPresentePagina(driver, "Nacionalidad: ESP");
	}
}