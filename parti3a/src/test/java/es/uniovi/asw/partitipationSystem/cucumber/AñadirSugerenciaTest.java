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
public class AñadirSugerenciaTest {
	
	@Autowired
	private ManageSuggestion manageSuggestion;
	
	@Autowired
	private CitizenService citizenService;
	
	public String content;
	
	private Suggestion suggestion;
	
	private Suggestion result;
	
	@Given("^contenido a añadir \"([^\"]*)\"$")
	public void givenContenido(String contenido){
		this.content = contenido;
	}
	
	@When("^creo la sugerencia$")
	public void whenCreo(){List<Category> listaCategorias = manageSuggestion.findSuggestionCategories();
		String contraseñaEncripatada = Encrypter.getInstance().makeSHA1Hash("user1");
		Citizen citizen = citizenService.findByEmailAndPassword("user1@me.com", contraseñaEncripatada);
		suggestion = new Suggestion(content, citizen.getUser(), listaCategorias.get(0));
	}
	
	@Then("^la inserto en la BDD$")
	public void thenInserto(){
		result = manageSuggestion.addSuggestion(suggestion);
	}
	
	@And("^el resultado es distinto de null$")
	public void andCompruebo(){
		Assert.assertTrue(result!=null);
	}
}
