package es.uniovi.asw.associations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Ciudadano;
import es.uniovi.asw.model.Usuario;

public class AsignarTest {

	private Ciudadano ciudadano;
	private Usuario usuario;
	
	@Before
	public void setUp() {
		ciudadano = new Ciudadano("Pepe", "Pérez", "pp@gmail.es", null, "33586", "España", "12345678-A");
		usuario = new Usuario("Pepe", "abcd", ciudadano);
		Association.Asignar.link(usuario, ciudadano);
	}

	@Test
	public void testAsignarLink() {
		assertTrue(ciudadano.getUsuario() == usuario);
		assertTrue(usuario.getCiudadano() == ciudadano);
	}
	
	@Test
	public void testAsignarUnlink() {
		Association.Asignar.unlink(usuario, ciudadano);

		assertTrue(ciudadano.getUsuario() == null);
		assertTrue(usuario.getCiudadano() == null);
	}

	@Test
	public void testAtributos() {
		assertTrue(ciudadano.getNombre().equals("Pepe"));
		assertTrue(ciudadano.getApellidos().equals("Pérez"));
		assertTrue(ciudadano.getEmail().equals("pp@gmail.es"));
		assertTrue(ciudadano.getFechaNacimiento() == null);
		assertTrue(ciudadano.getResidencia().equals("33586"));
		assertTrue(ciudadano.getNacionalidad().equals("España"));
		assertTrue(ciudadano.getDni().equals("12345678-A"));

		assertTrue(usuario.getUsuario().equals("Pepe"));
		assertTrue(usuario.getContraseña().equals("abcd"));
	}
	
	@Test
	public void testToStringCiudadano(){
		assertTrue(ciudadano.toString().equals("Ciudadano [nombre=" + ciudadano.getNombre() +
				", apellidos=" + ciudadano.getApellidos() + ", email=" + ciudadano.getEmail() +
				", fechaNacimiento=" + ciudadano.getFechaNacimiento() + ", residencia=" +
				ciudadano.getResidencia() + ", nacionalidad=" + ciudadano.getNacionalidad() + 
				", dni=" + ciudadano.getDni() + ", usuario=" + usuario.toString() +"]"));
	}
	
	@Test
	public void testToStringUsuario(){
		assertTrue(usuario.toString().equals("Usuario [usuario=" + usuario.getUsuario() 
					+ ", contraseña=" + usuario.getContraseña() + "]"));
	}	
	
	@Test
	public void testHashCodeOfEquals(){
		Ciudadano c2 = new Ciudadano("Pepe", "Pérez", "pp@gmail.es", new java.util.Date(), "33586", "España", "12345678-A");
//		assert ciudadano.equals(c2): "Los objetos deberian ser iguales en valor";
//		assert ciudadano.hashCode() == c2.hashCode(): "Hash en objetos iguales no son iguales";
		assertEquals(ciudadano, c2);
		assertEquals(ciudadano.hashCode(), c2.hashCode());		
		
		Usuario u2 = new Usuario("Pepe", "abcd", ciudadano);
//		assert usuario.equals(u2): "Los objetos deberian ser iguales en valor";
//		assert usuario.hashCode() == u2.hashCode(): "Hash en objetos iguales no son iguales";
		assertEquals(usuario, u2);
		assertEquals(usuario.hashCode(), u2.hashCode());		
	}
}