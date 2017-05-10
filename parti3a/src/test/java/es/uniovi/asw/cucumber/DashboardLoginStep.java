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
public class DashboardLoginStep {

	private WebDriver driver;

	@Given("^the politician is in the login page$")
	public void isInLoginPage() {
		driver = new HtmlUnitDriver(true);
		driver.get("http://locahost:8090/");
		driver.navigate().to("http://localhost:8090/");
	}

	@Then("^introduces his credentials, \"(.+)\" and \"(.+)\" into the login form$")
	public void insertCredentials(String mail, String psw) {
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).sendKeys(mail);
		
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys(psw);
	}

	@When("^he pushes the \"Log in\" button and selects dashboard$")
	public void pushLogIn() {
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Seleccione módulo", 10);
		driver.findElement(By.id("pasoDashboard")).click();
	}

	@And("^he gets redirected to the dashboard view$")
	public void isInDashboard() {
		assertEquals("Dashboard3a - Listado sugerencias", driver.getTitle());
	}
}