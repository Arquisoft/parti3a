package es.uniovi.asw.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CitizenChecker {

	public static String checkNombre(String nombre) throws BusinessException {
		Pattern patron = Pattern.compile("^[a-zA-Z_áéíóúñ^\\s]*$");	
		if (patron.matcher(nombre.trim()).matches() &&  nombre.length() != 0) {
			return nombre;
		}
		else {
			throw new BusinessException("Nombre incorrecto");
		}		
	}
	
	public static String checkApellidos (String apellidos) throws BusinessException {
		Pattern patron = Pattern.compile("^[a-zA-Z_áéíóúñ^\\s]*$");	
		if (patron.matcher(apellidos.trim()).matches() && apellidos.trim().length() != 0) {
			return apellidos;
		}
		else {
			throw new BusinessException("Apellidos incorrectos");
		}		
	}
	
	public static String checkEmail (String email) throws BusinessException {		
		Pattern patron = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}"
				+ "\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])"
				+ "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
		if (patron.matcher(email).matches() && email.length() != 0) {
			return email;
		}
		else {
			throw new BusinessException("Email incorrecto");
		}	
	}
	
	public static Date checkFechaNacimiento (Date fechaNacimiento) throws BusinessException {
		DateFormat aux = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = aux.format(fechaNacimiento);
		Pattern patron = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d$");
		if (patron.matcher(fecha).matches()) {
			return fechaNacimiento;
		}
		else {
			throw new BusinessException("Fecha incorrecta");
		}		
	}
	
	public static String checkResidencia (String residencia) throws BusinessException {
		if(residencia.length() != 0) {
			return residencia;
		}
		else {
			throw new BusinessException("Residencia incorrecta");
		}		
	}
	
	public static String checkNacionalidad (String nacionalidad) throws BusinessException {
		Pattern patron = Pattern.compile("^[a-zA-Z_áéíóúñ^\\s]*$");		
		if (patron.matcher(nacionalidad.trim()).matches() && nacionalidad.length() != 0) {
			return nacionalidad;
		}
		else {
			throw new BusinessException("Nacionalidad incorrecta");
		}		
	}
	
	public static String checkDni (String dni) throws BusinessException {
		Pattern patron = Pattern.compile("[0-9]{8}[A-Z]");
		if (patron.matcher(dni).matches() && dni.length() != 0) {
			return dni;
		}
		else {
			throw new BusinessException("Dni incorrecto");
		}
	}
}