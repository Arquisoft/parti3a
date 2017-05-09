package asw.dashboard.cucumber;

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
		System.out.println(driver.getPageSource());
		System.out.println("The politician is in the login page");
	}

	@Then("^introduces his credentials, \"(.+)\" and \"(.+)\" into the login form$")
	public void insertCredentials(String mail, String psw) {
		System.out.println("Introduces his credentials");
	}

	@When("^he pushes the \"Log in\" button$")
	public void pushLogIn() {
		System.out.println("He pushes the login button");
	}

	@And("^he gets redirected to the dashboard view$")
	public void isInDashboard() {
		System.out.println("He gets redirected to the dashboard view");
	}
}