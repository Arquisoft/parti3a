package es.uniovi.asw.parser.writers;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Ciudadano;
import es.uniovi.asw.model.Usuario;
import es.uniovi.asw.parser.writers.WORDWriter;

public class WORDWriterTest {

	private Ciudadano ciudadano;
		
	@Before
	public void setUp() {
		ciudadano = new Ciudadano("Nombre", "Apellido1 Apellido2", "email@email.com", null, "mi casa", "Española", "12345678A");
		Usuario usuario = new Usuario("usuario", "contraseña", ciudadano);
		Association.Asignar.link(usuario, ciudadano);
		WORDWriter writer = new WORDWriter();
		writer.write(ciudadano);
	}
	
	@Test
	public void escribeCorrectamente() {
		XWPFDocument document;
		try {
			document = new XWPFDocument(new FileInputStream("src/test/resources/emails/"+ciudadano.getDni()));
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);
			String pagina = extractor.getText();
			
			assertTrue(pagina.contains(ciudadano.getNombre()));
			assertTrue(pagina.contains(ciudadano.getApellidos()));
			assertTrue(pagina.contains(ciudadano.getUsuario().getUsuario()));
			assertTrue(pagina.contains(ciudadano.getUsuario().getContraseña()));
			
			extractor.close();			
		} catch(IOException e){
//			Console.println("No se encuentra el fichero");
		}
	}		
}
