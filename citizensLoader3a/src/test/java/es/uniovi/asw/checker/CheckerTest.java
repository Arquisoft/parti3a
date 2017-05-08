package es.uniovi.asw.checker;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.util.BusinessException;
import es.uniovi.asw.util.CitizenChecker;


public class CheckerTest {
	
	private Date d = new Date();
	private Citizen c;
	
	@Before
	public void setUp(){
		c = new Citizen("Andres", "", "asd", d, "asdsa", "12345", "98589858");
	}
	
	@Test
	public void testCheckNombre(){
		try {
			assertEquals("Andres", CitizenChecker.checkNombre(c.getName()));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckApellido(){
		try {
			CitizenChecker.checkApellidos(c.getSurname());
		} catch (BusinessException e) {
			assertEquals("Apellidos incorrectos", e.getMessage()); 
		}
	}
	
	@Test
	public void testCheckEmail(){
		try {
			CitizenChecker.checkEmail(c.getEmail());
		} catch (BusinessException e) {
			assertEquals("Email incorrecto", e.getMessage()); 
		}
	}
	
	@Test
	public void testFechaDeNacimiento(){
		try {
			assertEquals(d, CitizenChecker.checkFechaNacimiento(c.getBirthday()));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckNacionalidad(){
		try {
			CitizenChecker.checkNacionalidad(c.getNationality());
		} catch (BusinessException e) {
			assertEquals("Nacionalidad incorrecta", e.getMessage()); 
		}
		
		try {
			assertEquals("ESPAÑA", CitizenChecker.checkNacionalidad("ESPAÑA"));
		} catch (BusinessException e) {
		}
	}
	
	@Test
	public void testCheckDni(){
		try {
			CitizenChecker.checkDni(c.getDni());
		} catch (BusinessException e) {
			assertEquals("Dni incorrecto", e.getMessage()); 
		}
		
		try {
			assertEquals(c.getDni()+"G", CitizenChecker.checkDni(c.getDni()+"G"));
		} catch (BusinessException e) {
		}
	}
}
