package es.uniovi.asw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.readers.ExcelReader;
import es.uniovi.asw.parser.readers.Reader;
import es.uniovi.asw.parser.writers.PDFWriter;
import es.uniovi.asw.parser.writers.TXTWriter;
import es.uniovi.asw.parser.writers.WORDWriter;
import es.uniovi.asw.parser.writers.Writer;
import es.uniovi.asw.util.Console;

@Component
public class Application implements CommandLineRunner{		
	
	@Autowired
	private ReadList rList;  
    
	@Override
	public void run(String... args) throws Exception {		
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("----------------------------------------CARGANDO CIUDADANOS----------------------------------------");
		loadUsers(args);			
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////");
	}
	
	public void loadUsers(String... args) throws Exception {		
		Reader reader = null;
		Writer writer = null;
		String fichero;
		String formatoCorreo;	
		
		if(args.length ==0 || args[0].equals("--help")){
			mostrarAyuda();
		}
		else if(args[0].equals("info")){
			mostrarInfo();
		}
		else if(args[0].equals("load")){
			
			if(args.length == 3){	
				fichero = args[1];
				reader = comprobarReader(fichero);
				if(reader!=null){
					
					formatoCorreo = args[2];
					writer = comprobarWriter(formatoCorreo);
					if(writer!=null){						
						loadUsers(reader, writer, fichero);
					}
					
				}
			}
			else{
				error();
			}
		}
		else{
			error();
		}	
	}	
	
	private void loadUsers(Reader reader, Writer writer, String fichero) {	
		rList.setReader(reader);
		rList.setWriter(writer);
		rList.read(fichero);
	}
	
	private static void mostrarAyuda(){
		Console.println("Este sistema dispone de las siguientes opciones:");
		Console.println("\t-Si desea ejecutar el programa haga lo siguiente:\n"
				+ "\t\t- Escriba 'load' 'espacio en blanco' 'fichero.xlsx' 'espacio en blanco'"
				+ " 'formato del correo'\n"
				+ "\t\t- El formato del correo puede ser: TXT, DOCX o PDF");
		Console.println("\t-Si desea obtener información sobre el sistema:\n"
				+ "\t\t- Escriba el comando 'info'");
		Console.println("\t-Si desea ver el sistema de ayuda:\n"
				+ "\t\t- Escriba el comando '--help' (si no introduce ningún comando"
				+ " también visualizará el sistema de ayuda).");
	}
	
	private static void mostrarInfo() {
		Console.println("Arquisoft citizensLoader3a");		
		Console.println("Mas información en el repositorio git: "
				+ "https://github.com/Arquisoft/citizensLoader3a.git");
	}
	
	public static Reader comprobarReader (String fichero) {
		if (fichero.split("\\.")[1].equals("xlsx")){
			return new ExcelReader();
		} 
		//Segun tengamos más tipos de readers se añadirían aquí
		Console.println("Fichero con extensión incorrecta.\nRecuerde que la extensión ha de ser xlsx");
		return null;
	}
	
	public static Writer comprobarWriter (String formatoCorreo) {
		if (formatoCorreo.equalsIgnoreCase("TXT")) {
			return new TXTWriter();
		}
		else if (formatoCorreo.equalsIgnoreCase("DOCX")) {
			return new WORDWriter();
		}
		else if (formatoCorreo.equalsIgnoreCase("PDF")) {
			return new PDFWriter();
		}
		//Según tengamos más tipo de writers se añadirían aquí
		Console.println("El formato con el que desea enviar los correos es incorrecto.\n"
				+ "Recuerde que el formato ha de ser:\n"
				+ "- TXT, DOCX o PDF");
		return null;
	}
	
	private static void error(){
		Console.println("Orden desconocida.");
	}		
}