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
import es.uniovi.asw.ParticipationSystem.SistemaDeParticipacion.ManageComment;
import es.uniovi.asw.ParticipationSystem.SistemaDeParticipacion.ManageSuggestion;
import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.util.Encrypter;

@ContextConfiguration(classes=Application.class)
public class AñadirComentarioTest {
	
	@Autowired
	private ManageSuggestion manageSuggestion;
	
	@Autowired
	private ManageComment manageComment;
	
	@Autowired
	private CitizenService citizenService;
	
	private Suggestion suggestion;
	
	private Comment comment;
	
	private Comment result;
	
	@Given("^creo la sugerencia para comentarla$")
	public void givenContenido(){
		List<Category> listaCategorias = manageSuggestion.findSuggestionCategories();
		String contraseñaEncripatada = Encrypter.getInstance().makeSHA1Hash("user1");
		Citizen citizen = citizenService.findByEmailAndPassword("user1@me.com", contraseñaEncripatada);
		suggestion = new Suggestion("Contenido", citizen.getUser(), listaCategorias.get(0));
		suggestion = manageSuggestion.addSuggestion(suggestion);
	}
	
	@When("^contenido del comentario \"([^\"]*)\"$")
	public void whenCreo(String comentario){
		String contraseñaEncripatada = Encrypter.getInstance().makeSHA1Hash("user1");
		Citizen citizen = citizenService.findByEmailAndPassword("user1@me.com", contraseñaEncripatada);
		comment = new Comment(comentario, citizen.getUser(), suggestion);
	}
	
	@Then("^inserto el comentario$")
	public void thenInserto(){
		result = manageComment.addComment(comment);
	}
	
	@And("^compruebo que ha sido añadido$")
	public void andCompruebo(){
		Assert.assertTrue(result!=null);
	}
}
