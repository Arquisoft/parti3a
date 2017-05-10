package es.uniovi.asw;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.SuggestionService;
import es.uniovi.asw.business.UserService;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;
import es.uniovi.asw.util.Encrypter;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private SuggestionService suggestionService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//Creamos usuarios si es que no existían ya
		Citizen adminCitizen = citizenService.findByEmailAndPassword("admin@me.com", Encrypter.getInstance().makeSHA1Hash("admin"));
		Citizen participantCitizen = citizenService.findByEmailAndPassword("user1@me.com", Encrypter.getInstance().makeSHA1Hash("user1"));
		
		if (adminCitizen == null) {
			Citizen citizen = new Citizen(
					"Pedro", "Hernández Pérez", "admin@me.com", new Date(), 
					"Oviedo", "ESP", "12541211B");
			citizenService.addCitizen(citizen);
			
			String encryptedPass = Encrypter.getInstance().makeSHA1Hash("admin");
			User user = new User("admin", encryptedPass, citizen);
			user.setAdmin(true);
			userService.addUser(user);
		}
		
		if (participantCitizen == null) {
			Citizen citizen = new Citizen(
					"Juan", "Rodríguez García", "user1@me.com", new Date(),
					"Oviedo", "ESP", "14321234Z");
			citizenService.addCitizen(citizen);
			
			String encryptedPass = Encrypter.getInstance().makeSHA1Hash("user1");
			User user = new User("user1", encryptedPass, citizen);
			userService.addUser(user);		
		}

		User user = userService.findByUsernameAndPassword("user1", Encrypter.getInstance().makeSHA1Hash("user1"));
		Suggestion sugerencia = new Suggestion("Sugerencia prueba", 
				new Category("Categoría testing"), 
				user);
		suggestionService.addSuggestion(sugerencia);
	}
}
