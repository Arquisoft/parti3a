package es.uniovi.asw.parser.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import es.uniovi.asw.model.Citizen;

public class TXTWriter implements Writer {

	private final static String PATH = "src/main/resources/emails/";
	
	@Override
	public void write(Citizen ciudadano) {		
		BufferedWriter bw = null;
		String direccion = PATH + ciudadano.getDni() + ".txt";
		try{
			bw = new BufferedWriter(new FileWriter(direccion));
			bw.write("\n");
			bw.write("Hola " + ciudadano.getName() + " " + ciudadano.getSurname() + ",\n");
			bw.write("Este correo es para informarle de que ha sido dado de alta correctamente en el sistema de participación "
					+ "ciudadana. A continuación, le comunicamos su usuario y contraseña: \n");
			bw.write("\n");
			bw.write("\tUsuario: "+ ciudadano.getUser().getUsername() + "\n");
			bw.write("\tContraseña: "+ ciudadano.getUser().getPassword());			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(bw != null){
				try{
					bw.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
}