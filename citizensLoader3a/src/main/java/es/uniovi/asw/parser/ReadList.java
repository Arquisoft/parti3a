package es.uniovi.asw.parser;

import es.uniovi.asw.parser.readers.Reader;
import es.uniovi.asw.parser.writers.Writer;

public interface ReadList {

	public void read (String fichero);
	public void setReader (Reader reader);
	public void setWriter (Writer writer);
	
}