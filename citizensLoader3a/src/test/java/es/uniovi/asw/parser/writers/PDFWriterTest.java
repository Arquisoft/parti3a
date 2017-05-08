package es.uniovi.asw.parser.writers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;
import es.uniovi.asw.parser.writers.PDFWriter;

public class PDFWriterTest {

	private Citizen citizen;
			
	@Before
	public void setUp() {
		citizen = new Citizen("Nombre", "Apellido1 Apellido2", "email@email.com", null, "mi casa", "Española", "12345678A");
		User User = new User("User", "contraseña", citizen);
		Association.Asignar.link(User, citizen);
		PDFWriter writer = new PDFWriter();
		writer.write(citizen);
	}
	
	@Test
	public void escribeCorrectamente() {
		try {
			PdfReader reader = new PdfReader("src/test/resources/emails/"+citizen.getDni());
			String pagina = PdfTextExtractor.getTextFromPage(reader, 1);
			
			assertTrue(pagina.contains(citizen.getName()));
			assertTrue(pagina.contains(citizen.getSurname()));
			assertTrue(pagina.contains(citizen.getUser().getUsername()));
			assertTrue(pagina.contains(citizen.getUser().getPassword()));
			
		} catch(IOException e){
//			Console.println("No se encuentra el fichero");
		}
	}	
}