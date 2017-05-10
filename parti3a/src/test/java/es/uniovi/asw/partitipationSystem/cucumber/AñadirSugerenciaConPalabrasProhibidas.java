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
import es.uniovi.asw.ParticipationSystem.SistemaDeParticipacion.ManageSuggestion;
import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.RestringedWords;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.util.Encrypter;

@ContextConfiguration(classes=Application.class)
public class AñadirSugerenciaConPalabrasProhibidas {
	
	@Autowired
	private ManageSuggestion manageSuggestion;
	
	@Autowired
	private CitizenService citizenService;
	
	private Suggestion suggestion;
	
	private Suggestion result;
	
	@Given("^añado palabras prohibidas \"([^\"]*)\"$")
	public void given(String contenido){
		RestringedWords word = new RestringedWords(contenido);
		word = manageSuggestion.addRestringedWord(word);
		Assert.assertFalse(word==null);
	}
	
	@When("^la creo con contenido prohibido$")
	public void when(){
		List<Category> listaCategorias = manageSuggestion.findSuggestionCategories();
		String contraseñaEncripatada = Encrypter.getInstance().makeSHA1Hash("user1");
		Citizen citizen = citizenService.findByEmailAndPassword("user1@me.com", contraseñaEncripatada);
		suggestion = new Suggestion("imbecil", citizen.getUser(), listaCategorias.get(0));
	}
	
	@Then("^trato de insertarla$")
	public void then(){
		result = manageSuggestion.addSuggestion(suggestion);
	}
	
	@And("^compruebo que no fue posible$")
	public void and(){
		Assert.assertNull(result);
	}
}
