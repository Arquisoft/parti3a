package es.uniovi.asw.parser.readers;

import java.util.List;

import es.uniovi.asw.model.Citizen;

public interface Reader {
	
	public List<Citizen> read(String fichero);
	public boolean comprobarExtension(String fichero);
	
}