package es.uniovi.asw.parser.writers;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;
import es.uniovi.asw.parser.writers.WORDWriter;

public class WORDWriterTest {

	private Citizen citizen;
		
	@Before
	public void setUp() {
		citizen = new Citizen("Nombre", "Apellido1 Apellido2", "email@email.com", null, "mi casa", "Española", "12345678A");
		User User = new User("User", "contraseña", citizen);
		Association.Asignar.link(User, citizen);
		WORDWriter writer = new WORDWriter();
		writer.write(citizen);
	}
	
	@Test
	public void escribeCorrectamente() {
		XWPFDocument document;
		try {
			document = new XWPFDocument(new FileInputStream("src/test/resources/emails/"+citizen.getDni()));
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);
			String pagina = extractor.getText();
			
			assertTrue(pagina.contains(citizen.getName()));
			assertTrue(pagina.contains(citizen.getSurname()));
			assertTrue(pagina.contains(citizen.getUser().getUsername()));
			assertTrue(pagina.contains(citizen.getUser().getPassword()));
			
			extractor.close();			
		} catch(IOException e){
//			Console.println("No se encuentra el fichero");
		}
	}		
}
