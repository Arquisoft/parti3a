package es.uniovi.asw.partitipationSystem.cucumber;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;
import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.participationSystem.SistemaDeParticipacion.ManageSuggestion;
import es.uniovi.asw.util.Encrypter;

@ContextConfiguration(classes=Application.class)
public class EliminarSugerenciaTest {
	
	@Autowired
	private ManageSuggestion manageSuggestion;
	
	@Autowired
	private CitizenService citizenService;
	
	private Suggestion suggestion;
	
	private Suggestion result;
	
	@Given("^contenido de la sugerencia \"([^\"]*)\"$")
	public void givenContenido(String contenido){
		List<Category> listaCategorias = manageSuggestion.findSuggestionCategories();
		String contraseñaEncripatada = Encrypter.getInstance().makeSHA1Hash("user1");
		Citizen citizen = citizenService.findByEmailAndPassword("user1@me.com", contraseñaEncripatada);
		suggestion = new Suggestion(contenido, citizen.getUser(), listaCategorias.get(0));
		result = manageSuggestion.addSuggestion(suggestion);
	}
	
	@When("^he añadido la sugerencia$")
	public void whenCreo(){
		Assert.assertTrue(result!=null);
	}
	
	@Then("^la elimino$")
	public void thenInserto(){
		manageSuggestion.deleteSuggestion(result.getId());
	}
	
	@And("^compruebo que no existe$")
	public void andCompruebo(){
		Assert.assertNull(manageSuggestion.getSuggestion(result.getId()));
	}
}
