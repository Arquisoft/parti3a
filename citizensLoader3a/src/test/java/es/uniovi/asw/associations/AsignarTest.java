package es.uniovi.asw.associations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;

public class AsignarTest {

	private Citizen ciudadano;
	private User usuario;
	
	@Before
	public void setUp() {
		ciudadano = new Citizen("Pepe", "Pérez", "pp@gmail.es", null, "33586", "España", "12345678-A");
		usuario = new User("Pepe", "abcd", ciudadano);
		Association.Asignar.link(usuario, ciudadano);
	}

	@Test
	public void testAsignarLink() {
		assertTrue(ciudadano.getUser() == usuario);
		assertTrue(usuario.getCitizen() == ciudadano);
	}
	
	@Test
	public void testAsignarUnlink() {
		Association.Asignar.unlink(usuario, ciudadano);

		assertTrue(ciudadano.getUser() == null);
		assertTrue(usuario.getCitizen() == null);
	}

	@Test
	public void testAtributos() {
		assertTrue(ciudadano.getName().equals("Pepe"));
		assertTrue(ciudadano.getSurname().equals("Pérez"));
		assertTrue(ciudadano.getEmail().equals("pp@gmail.es"));
		assertTrue(ciudadano.getBirthday() == null);
		assertTrue(ciudadano.getResidence().equals("33586"));
		assertTrue(ciudadano.getNationality().equals("España"));
		assertTrue(ciudadano.getDni().equals("12345678-A"));

		assertTrue(usuario.getUsername().equals("Pepe"));
		assertTrue(usuario.getPassword().equals("abcd"));
	}
	
	@Test
	public void testToStringCiudadano(){
		assertTrue(ciudadano.toString().equals("Citizen [name=" + ciudadano.getName() + ", surname=" + ciudadano.getSurname() + ", email=" + ciudadano.getEmail() + ", birthday=" + ciudadano.getBirthday()
				+ ", residence=" + ciudadano.getResidence() + ", nationality=" + ciudadano.getNationality() + ", dni=" + ciudadano.getDni() + ", user=" + ciudadano.getUser()+"]"));
	}
	
	@Test
	public void testToStringUsuario(){
		assertTrue(usuario.toString().equals("User [username=" + usuario.getUsername() + ", password=" + usuario.getPassword() + "]"));
	}	
	
	@Test
	public void testHashCodeOfEquals(){
		Citizen c2 = new Citizen("Pepe", "Pérez", "pp@gmail.es", new java.util.Date(), "33586", "España", "12345678-A");
//		assert ciudadano.equals(c2): "Los objetos deberian ser iguales en valor";
//		assert ciudadano.hashCode() == c2.hashCode(): "Hash en objetos iguales no son iguales";
		assertEquals(ciudadano, c2);
		assertEquals(ciudadano.hashCode(), c2.hashCode());		
		
		User u2 = new User("Pepe", "abcd", ciudadano);
//		assert usuario.equals(u2): "Los objetos deberian ser iguales en valor";
//		assert usuario.hashCode() == u2.hashCode(): "Hash en objetos iguales no son iguales";
		assertEquals(usuario, u2);
		assertEquals(usuario.hashCode(), u2.hashCode());		
	}
}