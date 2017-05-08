package es.uniovi.asw.parser.writers;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;
import es.uniovi.asw.parser.writers.TXTWriter;

public class TXTWriterTest {

	private Citizen citizen;
			
	@Before
	public void setUp() {
		citizen = new Citizen("Nombre", "Apellido1 Apellido2", "email@email.com", null, "mi casa", "Española", "12345678A");
		User usuario = new User("usuario", "contraseña", citizen);
		Association.Asignar.link(usuario, citizen);
		TXTWriter writer = new TXTWriter();
		writer.write(citizen);
	}
	
	@Test
	public void escribeCorrectamente() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/test/resources/emails"+citizen.getDni()+".txt"));
			String pagina = br.readLine();
			
			assertTrue(pagina.contains(citizen.getName()));
			assertTrue(pagina.contains(citizen.getSurname()));
			assertTrue(pagina.contains(citizen.getUser().getUsername()));
			assertTrue(pagina.contains(citizen.getUser().getPassword()));
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