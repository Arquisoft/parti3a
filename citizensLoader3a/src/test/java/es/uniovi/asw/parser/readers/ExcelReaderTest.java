package es.uniovi.asw.parser.readers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Citizen;

public class ExcelReaderTest {

	private Citizen c1;
	private Citizen c2;
	private Citizen c3;
	private Reader reader;

	@Before
	public void setUp() {
		c1 = new Citizen("Juan", "Torres Pardo", "juan@example.com", null, null, "Español", "90500084Y");
		c2 = new Citizen("Luis", "López Fernando", "luis@example.com", null, null, "Español", "19160962F");
		c3 = new Citizen("Ana", "Torres Pardo", "ana@example.com", null, null, "Francés", "09940449X");
		reader = new ExcelReader();
	}
	
	@Test
	public void testReader() {		
		List<Citizen> lista = reader.read("testPruebas.xlsx");
		
		//Compruebo Citizen 1
		assertTrue(lista.get(0).getDni().equals(c1.getDni()));
		assertFalse(lista.get(0).getBirthday().equals(c1.getBirthday()));
		assertTrue(lista.get(0).equals(c1));
		
		//Compruebo Citizen 2
		assertTrue(lista.get(1).getDni().equals(c2.getDni()));
		assertTrue(lista.get(1).getNationality().equals(c2.getNationality()));
		assertTrue(lista.get(1).equals(c2));
		
		//Compruebo Citizen 3
		assertTrue(lista.get(2).getDni().equals(c3.getDni()));
		assertFalse(lista.get(2).getNationality().equals(c3.getNationality()));
		assertTrue(lista.get(2).equals(c3));
		
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertFalse(c2.equals(c3));
	}
	
	@Test
	public void testReadEmpty() {
		List<Citizen> Citizens = reader.read("testEmpty.xlsx");
		
		assertEquals(true, Citizens.isEmpty());
	}
	
	@Test
	public void testFileNotFound() {
		List<Citizen> Citizens = reader.read("notFound.xlsx");
		
		assertEquals(true, Citizens.isEmpty());
	}
	
	@Test
	public void testFileNotXlsx() {
		List<Citizen> Citizens = reader.read("test.txt");
		
		assertEquals(true, Citizens.isEmpty());
	}
}