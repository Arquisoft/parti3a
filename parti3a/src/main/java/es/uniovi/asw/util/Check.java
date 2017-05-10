package es.uniovi.asw.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.participants.errors.EmailFormatException;
import es.uniovi.asw.participants.errors.EmailUnchangedException;
import es.uniovi.asw.participants.errors.ParticipantNotFound;
import es.uniovi.asw.participants.errors.PasswordEmptyException;
import es.uniovi.asw.participants.errors.PasswordUnchangedException;

public class Check {
	
	public static Citizen participantExists(String email, String password, 
			CitizenService service) {
		
		Citizen citizen = service.findByEmailAndPassword(email, password);
		
		if (citizen == null)
			throw new ParticipantNotFound();
		
		return citizen;
	}
	
	public static void validEmailFormat(String email) {
		Pattern emailRegex = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
			    		Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = emailRegex.matcher(email);
		
		if (!matcher.find())
			throw new EmailFormatException();
	}
	
	public static void differentEmail(String email, String newEmail) {
		if (email.trim().equals(newEmail.trim()))
			throw new EmailUnchangedException();
	}
	
	public static void passwordNotEmpty(String password) {
		if (password == null || password.trim().isEmpty())
			throw new PasswordEmptyException();
	}
	
	public static void differentPassword(String password, String newPassword) {
		if (password.trim().equals(newPassword.trim())) 
			throw new PasswordUnchangedException();
	}
}
