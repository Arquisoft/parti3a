package asw.model.dominio;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;
import es.uniovi.asw.model.Citizen;

public class UsuarioTest {

	private User usuario1, usuario2, usuario3;
	private Category categoria1, categoria2, categoria3;
	private Comment comentario1, comentario2, comentario3, comentario4, comentario5, comentario6;
	private Suggestion sugerencia1, sugerencia2, sugerencia3, sugerencia4;

	@Before
	public void setUp() {
		
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		usuario1 = new User("Pepe", ciudadano);
		usuario2 = new User("Jose", ciudadano);
		usuario3 = new User("Paco",ciudadano);

		categoria1 = new Category("Categoria 1");
		categoria2 = new Category("Categoria 2");
		categoria3 = new Category("Categoria 3");

		sugerencia1 = new Suggestion("Contenido sugerencia 1", categoria1, usuario1);

		sugerencia2 = new Suggestion("Contenido sugerencia 2", categoria2, usuario2);

		sugerencia3 = new Suggestion("Contenido sugerencia 3", categoria3, usuario3);

		sugerencia4 = new Suggestion("Contenido sugerencia 4", categoria2, usuario1);

		comentario1 = new Comment("Mal", sugerencia1, usuario3);
		comentario4 = new Comment("Horrible", sugerencia1, usuario2);

		comentario2 = new Comment("Bien", sugerencia2, usuario1);
		comentario3 = new Comment("Regular", sugerencia2, usuario3);
		comentario5 = new Comment("Nefasto", sugerencia2, usuario1);

		comentario6 = new Comment("Maravilloso", sugerencia3, usuario2);
	}

	@Test
	public void testNombre() {
		assertTrue(usuario1.getUsername().equals("Pepe"));
		assertTrue(usuario2.getUsername().equals("Jose"));
		assertTrue(usuario3.getUsername().equals("Paco"));
	}

	@Test
	public void testComentarios() {
		assertTrue(usuario1.getCommments().size() == 2);
		assertTrue(usuario1.getCommments().contains(comentario2));
		assertTrue(usuario1.getCommments().contains(comentario5));

		assertTrue(usuario2.getCommments().size() == 2);
		assertTrue(usuario2.getCommments().contains(comentario4));
		assertTrue(usuario2.getCommments().contains(comentario6));

		assertTrue(usuario3.getCommments().size() == 2);
		assertTrue(usuario3.getCommments().contains(comentario1));
		assertTrue(usuario3.getCommments().contains(comentario3));
	}

	@Test
	public void testSugerencia() {
		assertTrue(usuario1.getSuggestions().size() == 2);
		assertTrue(usuario1.getSuggestions().contains(sugerencia1));
		assertTrue(usuario1.getSuggestions().contains(sugerencia4));

		assertTrue(usuario2.getSuggestions().size() == 1);
		assertTrue(usuario2.getSuggestions().contains(sugerencia2));

		assertTrue(usuario3.getSuggestions().size() == 1);
		assertTrue(usuario3.getSuggestions().contains(sugerencia3));
	}


	@Test
	public void testSafeReturn() {
		Set<Comment> comentarios = usuario1.getCommments();
		comentarios.clear();

		assertTrue(comentarios.size() == 0);
		assertTrue(usuario1.getCommments().size() == 2);

		Set<Suggestion> sugerencias = usuario1.getSuggestions();
		sugerencias.clear();

		assertTrue(sugerencias.size() == 0);
		assertTrue(usuario1.getSuggestions().size() == 2);
	}

	@Test
	public void testConstructorConCiudadano() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española",
				"123456S");

		User user = new User("Paco", "ocap", ciudadano);

		assertTrue(user.getCitizen().equals(ciudadano));
		assertTrue(ciudadano.getUser().equals(user));

		assertTrue(user.getPassword().equals("ocap"));
	}

	@Test
	public void testEquals() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		User usuario = new User("Pepe", ciudadano);

		assertTrue(usuario1.equals(usuario));
	}

	@Test
	public void testToString() {
		Citizen ciudadano = new Citizen("Fran", "Garcia", "e@email.com", new Date(), "Pamplona", "Española", "123456S");
		User usuario = new User("Borja", ciudadano);

		String u = "User [username=" + "Borja" + ", password=" + "null" + "]";
		assertEquals(u, usuario.toString());
	}

}