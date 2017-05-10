package es.uniovi.asw.model.asociacion;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import es.uniovi.asw.model.Association.*;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;
import es.uniovi.asw.model.Citizen;

public class AsociacionTest {
	
	@Test
	public void testAsignar() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		User user = new User("Paco", "ocap", ciudadano);
		
		assertTrue(ciudadano.getUser().equals(user));
		assertTrue(user.getCitizen().equals(ciudadano));
		
		Asignar.unlink(user, ciudadano);
		
		assertTrue(ciudadano.getUser() == null);
		assertTrue(user.getCitizen() == null);
	}
	
	@Test
	public void testAsignarSugerencia() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		User user = new User("Paco", ciudadano);
		Category categoria = new Category("Categoria");
		Suggestion sugerencia = new Suggestion("Contenido sugerencia", categoria, user);
		
		assertTrue(user.getSuggestions().contains(sugerencia));
		assertTrue(sugerencia.getUser().equals(user));
		
		AsignarSugerencia.unlink(user, sugerencia);
		
		assertTrue(sugerencia.getUser() == null);
		assertEquals(user.getSuggestions().contains(sugerencia), true);
	}
	
	@Test
	public void testAsignarComentario() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		User user = new User("Paco", ciudadano);
		Category categoria = new Category("Categoria");
		Suggestion sugerencia = new Suggestion("Contenido sugerencia", categoria, user);
		Comment comentario = new Comment("Mal", sugerencia, user);
		
		assertTrue(user.getCommments().contains(comentario));
		assertTrue(sugerencia.getComments().contains(comentario));
		assertTrue(comentario.getSuggestion().equals(sugerencia));
		assertTrue(comentario.getUser().equals(user));
		
		AsignarComentario.unlink(comentario, sugerencia, user);
		
		assertTrue(user.getCommments().contains(comentario));
		assertTrue(sugerencia.getComments().contains(comentario));
		assertTrue(comentario.getSuggestion() == null);
		assertTrue(comentario.getUser() == null);
	}


}