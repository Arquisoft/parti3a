package es.uniovi.asw.parser.writers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Ciudadano;
import es.uniovi.asw.model.Usuario;
import es.uniovi.asw.parser.writers.PDFWriter;

public class PDFWriterTest {

	private Ciudadano ciudadano;
			
	@Before
	public void setUp() {
		ciudadano = new Ciudadano("Nombre", "Apellido1 Apellido2", "email@email.com", null, "mi casa", "Española", "12345678A");
		Usuario usuario = new Usuario("usuario", "contraseña", ciudadano);
		Association.Asignar.link(usuario, ciudadano);
		PDFWriter writer = new PDFWriter();
		writer.write(ciudadano);
	}
	
	@Test
	public void escribeCorrectamente() {
		try {
			PdfReader reader = new PdfReader("src/test/resources/emails/"+ciudadano.getDni());
			String pagina = PdfTextExtractor.getTextFromPage(reader, 1);
			
			assertTrue(pagina.contains(ciudadano.getNombre()));
			assertTrue(pagina.contains(ciudadano.getApellidos()));
			assertTrue(pagina.contains(ciudadano.getUsuario().getUsuario()));
			assertTrue(pagina.contains(ciudadano.getUsuario().getContraseña()));
			
		} catch(IOException e){
//			Console.println("No se encuentra el fichero");
		}
	}	
}