package es.uniovi.asw.model.dominio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;

public class CategoriaTest {

	private User usuario1, usuario2, usuario3;
	private Category categoria1, categoria2, categoria3;
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
		for (int i = 0; i < 4; i++)
			sugerencia1.addNegativeVote();
		for (int i = 0; i < 3; i++)
			sugerencia1.addPositiveVote();

		sugerencia2 = new Suggestion("Contenido sugerencia 2", categoria2, usuario2);
		sugerencia2.setContents("Titulo sugerencia 2");
		for (int i = 0; i < 2; i++)
			sugerencia2.addNegativeVote();
		for (int i = 0; i < 6; i++)
			sugerencia2.addPositiveVote();

		sugerencia3 = new Suggestion("Contenido sugerencia 3", categoria3, usuario3);
		sugerencia3.setContents("Titulo sugerencia 3");
		for (int i = 0; i < 10; i++)
			sugerencia3.addNegativeVote();
		for (int i = 0; i < 6; i++)
			sugerencia3.addPositiveVote();

	}

	@Test
	public void testSugerencia() {
		assertTrue(sugerencia1.getCategory().equals(categoria1));
		assertTrue(sugerencia2.getCategory().equals(categoria2));
		assertTrue(sugerencia3.getCategory().equals(categoria3));
	}

	@Test
	public void testEquals() {
		Category categoria = new Category("Categoria 1");

		assertTrue(categoria.equals(categoria1));
	}
}