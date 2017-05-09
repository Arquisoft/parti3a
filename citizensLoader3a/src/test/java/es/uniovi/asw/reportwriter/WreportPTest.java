package es.uniovi.asw.reportwriter;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import es.uniovi.asw.reportwriter.Level;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class WreportPTest {

	@Autowired
	private WriteReport reporter;
	
	@Before
	public void setUp() {
		//Log de trace
		reporter.report("Error de prueba TRACE", "prueba.xlsx", Level.TRACE);
		
		//Log de debug
		reporter.report("Error de prueba DEBUG", "prueba.xlsx", Level.DEBUG);
		
		//Log de info
		reporter.report("Error de prueba INFO", "prueba.xlsx", Level.INFO);
		
		//Log de warn
		reporter.report("Error de prueba WARN", "prueba.xlsx", Level.WARN);
		
		//Log de error
		reporter.report("Error de prueba ERROR", "prueba.xlsx", Level.ERROR);
		
		//Log de fatal
		reporter.report("Error de prueba FATAL", "prueba.xlsx", Level.FATAL);
	}
	
	@Test
	public void escribeCorrectamente() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {			
			br = new BufferedReader(new FileReader(new File("src/test/resources/log/archivoTest.log")));
			bw = new BufferedWriter(new FileWriter(new File("src/test/resources/log/archivoTest.log")));
			bw.write("");			
			int line = 0;
			while(br.ready()) {	
				line ++;
				switch(line) {
					case 1: assertTrue(br.readLine().contains("Error de prueba TRACE"));
							break;
					case 2: assertTrue(br.readLine().contains("Error de prueba DEBUG"));
							break;				
					case 3: assertTrue(br.readLine().contains("Error de prueba INFO"));
							break;
					case 4: assertTrue(br.readLine().contains("Error de prueba WARN"));
							break;
					case 5: assertTrue(br.readLine().contains("Se ha producido el siguiente error en el fichero prueba.xlsx: Error de prueba ERROR"));
							break;
					case 6: assertTrue(br.readLine().contains("Se ha producido el siguiente error en el fichero prueba.xlsx: Error de prueba FATAL"));
							break;
					default: break;
				}				
			}
			
//			bw = new BufferedWriter(new FileWriter(new File("src/test/resources/log/archivoTest.log")));
//			bw.write("");
			
		} catch (Exception e) {
//			Console.println("No se encuentra el archivo");
		} finally {
			if(br != null){
				try{
					br.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			
			if(bw != null){
				try{
					bw.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}