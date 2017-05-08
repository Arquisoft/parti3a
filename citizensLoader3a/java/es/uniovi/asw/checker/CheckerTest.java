package es.uniovi.asw.checker;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Ciudadano;
import es.uniovi.asw.util.BusinessException;
import es.uniovi.asw.util.CiudadanoChecker;


public class CheckerTest {
	
	private Date d = new Date();
	private Ciudadano c;
	
	@Before
	public void setUp(){
		c = new Ciudadano("Andres", "", "asd", d, "asdsa", "12345", "98589858");
	}
	
	@Test
	public void testCheckNombre(){
		try {
			assertEquals("Andres", CiudadanoChecker.checkNombre(c.getNombre()));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckApellido(){
		try {
			CiudadanoChecker.checkApellidos(c.getApellidos());
		} catch (BusinessException e) {
			assertEquals("Apellidos incorrectos", e.getMessage()); 
		}
	}
	
	@Test
	public void testCheckEmail(){
		try {
			CiudadanoChecker.checkEmail(c.getEmail());
		} catch (BusinessException e) {
			assertEquals("Email incorrecto", e.getMessage()); 
		}
	}
	
	@Test
	public void testFechaDeNacimiento(){
		try {
			assertEquals(d, CiudadanoChecker.checkFechaNacimiento(c.getFechaNacimiento()));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckNacionalidad(){
		try {
			CiudadanoChecker.checkNacionalidad(c.getNacionalidad());
		} catch (BusinessException e) {
			assertEquals("Nacionalidad incorrecta", e.getMessage()); 
		}
		
		try {
			assertEquals("ESPAÑA", CiudadanoChecker.checkNacionalidad("ESPAÑA"));
		} catch (BusinessException e) {
		}
	}
	
	@Test
	public void testCheckDni(){
		try {
			CiudadanoChecker.checkDni(c.getDni());
		} catch (BusinessException e) {
			assertEquals("Dni incorrecto", e.getMessage()); 
		}
		
		try {
			assertEquals(c.getDni()+"G", CiudadanoChecker.checkDni(c.getDni()+"G"));
		} catch (BusinessException e) {
		}
	}
}
