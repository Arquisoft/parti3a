package es.uniovi.asw.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.uniovi.asw.model.asociacion.AsociacionTest;
import es.uniovi.asw.model.dominio.CategoriaTest;
import es.uniovi.asw.model.dominio.ComentarioTest;
import es.uniovi.asw.model.dominio.SugerenciaTest;
import es.uniovi.asw.model.dominio.UsuarioTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	CategoriaTest.class,
	ComentarioTest.class,
	SugerenciaTest.class,
	UsuarioTest.class,
	AsociacionTest.class
})
public class AllTests {

}