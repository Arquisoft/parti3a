package es.uniovi.asw.parser.readers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Ciudadano;

public class ExcelReaderTest {

	private Ciudadano c1;
	private Ciudadano c2;
	private Ciudadano c3;
	private Reader reader;

	@Before
	public void setUp() {
		c1 = new Ciudadano("Juan", "Torres Pardo", "juan@example.com", null, null, "Español", "90500084Y");
		c2 = new Ciudadano("Luis", "López Fernando", "luis@example.com", null, null, "Español", "19160962F");
		c3 = new Ciudadano("Ana", "Torres Pardo", "ana@example.com", null, null, "Francés", "09940449X");
		reader = new ExcelReader();
	}
	
	@Test
	public void testReader() {		
		List<Ciudadano> lista = reader.read("test.xlsx");
		
		//Compruebo ciudadano 1
		assertTrue(lista.get(0).getDni().equals(c1.getDni()));
		assertFalse(lista.get(0).getFechaNacimiento().equals(c1.getFechaNacimiento()));
		assertTrue(lista.get(0).equals(c1));
		
		//Compruebo ciudadano 2
		assertTrue(lista.get(1).getDni().equals(c2.getDni()));
		assertTrue(lista.get(1).getNacionalidad().equals(c2.getNacionalidad()));
		assertTrue(lista.get(1).equals(c2));
		
		//Compruebo ciudadano 3
		assertTrue(lista.get(2).getDni().equals(c3.getDni()));
		assertFalse(lista.get(2).getNacionalidad().equals(c3.getNacionalidad()));
		assertTrue(lista.get(2).equals(c3));
		
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertFalse(c2.equals(c3));
	}
	
	@Test
	public void testReadEmpty() {
		List<Ciudadano> ciudadanos = reader.read("testEmpty.xlsx");
		
		assertEquals(true, ciudadanos.isEmpty());
	}
	
	@Test
	public void testFileNotFound() {
		List<Ciudadano> ciudadanos = reader.read("notFound.xlsx");
		
		assertEquals(true, ciudadanos.isEmpty());
	}
	
	@Test
	public void testFileNotXlsx() {
		List<Ciudadano> ciudadanos = reader.read("test.txt");
		
		assertEquals(true, ciudadanos.isEmpty());
	}
}