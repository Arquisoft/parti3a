package es.uniovi.asw.dashboard.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/es/uniovi/asw/dashboard/cucumber/features")
public class CucumberTest {
	
}