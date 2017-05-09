package asw.model.dominio;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;

public class SugerenciaTest {

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
		sugerencia1.setNegativeVotes(4);
		sugerencia1.setPositiveVotes(3);

		sugerencia2 = new Suggestion("Contenido sugerencia 2", categoria2, usuario2);
		sugerencia2.setNegativeVotes(2);
		sugerencia2.setPositiveVotes(6);

		sugerencia3 = new Suggestion("Contenido sugerencia 3", categoria3, usuario3);
		sugerencia3.setNegativeVotes(10);
		sugerencia3.setPositiveVotes(6);

		comentario1 = new Comment("Mal", sugerencia1, usuario3);
		comentario4 = new Comment("Horrible", sugerencia1, usuario2);

		comentario2 = new Comment("Bien", sugerencia2, usuario1);
		comentario3 = new Comment("Regular", sugerencia2, usuario3);
		comentario5 = new Comment("Nefasto", sugerencia2, usuario1);

		comentario6 = new Comment("Maravilloso", sugerencia3, usuario2);
	}

	@Test
	public void testTituloYContenido() {
		assertTrue(sugerencia1.getContents().equals("Contenido sugerencia 1"));

		assertTrue(sugerencia2.getContents().equals("Contenido sugerencia 2"));

		assertTrue(sugerencia3.getContents().equals("Contenido sugerencia 3"));
	}

	@Test
	public void testNumeroComentarios() {
		assertTrue(sugerencia1.getNumComments() == 2);

		assertTrue(sugerencia2.getNumComments() == 3);

		assertTrue(sugerencia3.getNumComments() == 1);
	}

	@Test
	public void testCategoria() {
		assertTrue(sugerencia1.getCategory().getName().equals("Categoria 1"));

		assertTrue(sugerencia2.getCategory().getName().equals("Categoria 2"));

		assertTrue(sugerencia3.getCategory().getName().equals("Categoria 3"));
	}

	@Test
	public void testComentarios() {
		assertTrue(sugerencia1.getComments().size() == 2);
		assertTrue(sugerencia1.getComments().contains(comentario1));
		assertTrue(sugerencia1.getComments().contains(comentario4));

		assertTrue(sugerencia2.getComments().size() == 3);
		assertTrue(sugerencia2.getComments().contains(comentario2));
		assertTrue(sugerencia2.getComments().contains(comentario3));
		assertTrue(sugerencia2.getComments().contains(comentario5));

		assertTrue(sugerencia3.getComments().size() == 1);
		assertTrue(sugerencia3.getComments().contains(comentario6));
	}

	@Test
	public void testSafeReturn() {
		Set<Comment> comentarios = sugerencia1.getComments();
		comentarios.clear();

		assertTrue(comentarios.size() == 0);
		assertTrue(sugerencia1.getComments().size() != 0);
	}

	@Test
	public void testEquals() {
		Suggestion sugerencia = new Suggestion("Contenido sugerencia 1", categoria1, usuario1);
		for (int i = 0; i < 4; i++)
			sugerencia.addNegativeVote();;
		for (int i = 0; i < 3; i++)
			sugerencia.addPositiveVote();

		assertTrue(sugerencia.equals(sugerencia1));
	}

	@Test
	public void testToString() {
		Suggestion sugerencia = new Suggestion("Contenido sugerencia 1", categoria1, usuario1);
		for (int i = 0; i < 4; i++)
			sugerencia.addNegativeVote();
		for (int i = 0; i < 3; i++)
			sugerencia.addPositiveVote();

		assertEquals(sugerencia.toString(), sugerencia1.toString());
	}

	@Test
	public void testContenido() {
		Suggestion sugerencia = new Suggestion("Contenido sugerencia", categoria1, usuario1);
		assertTrue(sugerencia.getContents().equals("Contenido sugerencia"));

		sugerencia.setContents("Ahora cambio el contenido");
		assertTrue(sugerencia.getContents().equals("Ahora cambio el contenido"));
	}

	@Test
	public void testSetComentarios() {
		assertTrue(sugerencia1.getComments().size() == 2);
		
		assertTrue(sugerencia1.getComments().size() != 0);
	}
}