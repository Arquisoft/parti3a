package es.uniovi.asw.parser;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;
import es.uniovi.asw.parser.readers.Reader;
import es.uniovi.asw.parser.writers.Writer;

@Component
public class RList implements ReadList {
	
	@Autowired
	private Insert insert;
	
	private List<Citizen> ciudadanos;		
	private Reader reader;
	private Writer writer;	
	
	@Override
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	@Override
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void read(String fichero) {			
		ciudadanos = reader.read(fichero);
		crearUsuarios();
		long start, end;
		start = System.currentTimeMillis();	
		List<Citizen> insertados = insertarCiudadanos(ciudadanos, fichero);	
		end = System.currentTimeMillis();
		System.out.println("Insercion TARDA: "+ (end - start)+" milisegundos");
		if (insertados != null)
			crearEmail(insertados);	
	}	

	private void crearUsuarios() {
		for(Citizen c: ciudadanos) {
			String usuario = generarUsuario(c);
			String contraseña = generarContraseña();
			User u = new User(usuario, contraseña, c);
			Association.Asignar.link(u, c);
		}
	}
	
	private String generarUsuario(Citizen ciudadano) {
		return ciudadano.getName() + RandomStringUtils.randomAlphanumeric(4);
	}
	
	private String generarContraseña() {
		return RandomStringUtils.randomAlphabetic(4) + RandomStringUtils.randomAlphanumeric(4);
	}
	
	private void crearEmail(List<Citizen> insertados) {
		for(Citizen c: insertados) {
			writer.write(c);
		}		
	}
	
	private List<Citizen> insertarCiudadanos(final List<Citizen> listaCiudadanos, final String fichero) {		
		return insert.insert(listaCiudadanos, fichero)	;
	}	
}