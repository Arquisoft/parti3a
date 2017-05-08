package es.uniovi.asw.parser.writers;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Ciudadano;
import es.uniovi.asw.model.Usuario;
import es.uniovi.asw.parser.writers.TXTWriter;

public class TXTWriterTest {

	private Ciudadano ciudadano;
			
	@Before
	public void setUp() {
		ciudadano = new Ciudadano("Nombre", "Apellido1 Apellido2", "email@email.com", null, "mi casa", "Española", "12345678A");
		Usuario usuario = new Usuario("usuario", "contraseña", ciudadano);
		Association.Asignar.link(usuario, ciudadano);
		TXTWriter writer = new TXTWriter();
		writer.write(ciudadano);
	}
	
	@Test
	public void escribeCorrectamente() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/resources/emails"+ciudadano.getDni()+".txt"));
			String pagina = br.readLine();
			
			assertTrue(pagina.contains(ciudadano.getNombre()));
			assertTrue(pagina.contains(ciudadano.getApellidos()));
			assertTrue(pagina.contains(ciudadano.getUsuario().getUsuario()));
			assertTrue(pagina.contains(ciudadano.getUsuario().getContraseña()));
		} catch (IOException e) {
//			Console.println("No se encuentra el fichero");
		} finally {			
			if(br != null){
				try{
					br.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}