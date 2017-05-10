package es.uniovi.asw.model.dominio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;

public class ComentarioTest {

	private User usuario1, usuario2, usuario3;
	private Category categoria1, categoria2, categoria3;
	private Comment comentario1, comentario2, comentario3, comentario4, comentario5, comentario6;
	private Suggestion sugerencia1, sugerencia2, sugerencia3;
	
	@Before
	public void setUp() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		usuario1 = new User("Pepe", ciudadano);
		usuario2 = new User("Jose", ciudadano);
		usuario3 = new User("Paco", ciudadano);
		
		categoria1 = new Category("Categoria 1");
		categoria2 = new Category("Categoria 2");
		categoria3 = new Category("Categoria 3");
		
		sugerencia1 = new Suggestion("Contenido sugerencia 1", categoria1, usuario1);
		sugerencia1.setContents("Titulo sugerencia 1");
		for(int i = 0; i < 4; i++)
			sugerencia1.addNegativeVote();
		for(int i = 0; i < 3; i++)
			sugerencia1.addPositiveVote();
		
		sugerencia2 = new Suggestion("Contenido sugerencia 2", categoria2, usuario2);
		sugerencia2.setContents("Titulo sugerencia 2");
		for(int i = 0; i < 2; i++)
			sugerencia2.addNegativeVote();
		for(int i = 0; i < 6; i++)
			sugerencia2.addPositiveVote();
		
		sugerencia3 = new Suggestion("Contenido sugerencia 3", categoria3, usuario3);
		sugerencia3.setContents("Titulo sugerencia 3");
		for(int i = 0; i < 10; i++)
			sugerencia3.addNegativeVote();
		for(int i = 0; i < 6; i++)
			sugerencia3.addPositiveVote();
		
		comentario1 = new Comment("Mal", sugerencia1, usuario3);
		comentario4 = new Comment("Horrible", sugerencia1, usuario2);
		
		comentario2 = new Comment("Bien", sugerencia2, usuario1);
		comentario3 = new Comment("Regular", sugerencia2, usuario3);
		comentario5 = new Comment("Nefasto", sugerencia2, usuario1);
		
		comentario6 = new Comment("Maravilloso", sugerencia3, usuario2);
	}
	
	@Test
	public void testContenido() {
		assertTrue(comentario1.getContents().equals("Mal"));
		assertTrue(comentario2.getContents().equals("Bien"));
		assertTrue(comentario3.getContents().equals("Regular"));
		assertTrue(comentario4.getContents().equals("Horrible"));
		assertTrue(comentario5.getContents().equals("Nefasto"));
		assertTrue(comentario6.getContents().equals("Maravilloso"));
	}
	
	@Test
	public void testSugerencia() {
		assertTrue(comentario1.getSuggestion().equals(sugerencia1));
		assertTrue(comentario4.getSuggestion().equals(sugerencia1));
		
		assertTrue(comentario2.getSuggestion().equals(sugerencia2));
		assertTrue(comentario3.getSuggestion().equals(sugerencia2));
		assertTrue(comentario5.getSuggestion().equals(sugerencia2));
		
		assertTrue(comentario6.getSuggestion().equals(sugerencia3));
	}
	
	@Test
	public void testEquals() {
		Comment comentario = new Comment("Mal", sugerencia1, usuario3);
		
		assertTrue(comentario.equals(comentario1));
	}
	
	@Test
	public void testToString() {
		Comment comentario = new Comment("Mal", sugerencia1, usuario3);

		assertEquals(comentario1.toString(), comentario.toString());
	}
	
	@Test
	public void testSetContenido() {
		Comment comentario = new Comment("Mal", sugerencia1, usuario3);
		assertTrue(comentario.getContents().equals("Mal"));
		
		assertFalse(comentario.getContents().equals("Ahora cambio el contenido"));
	}

}