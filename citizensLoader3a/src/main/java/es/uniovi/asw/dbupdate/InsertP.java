package es.uniovi.asw.dbupdate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.UserService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;
import es.uniovi.asw.reportwriter.Level;
import es.uniovi.asw.reportwriter.WriteReport;
import es.uniovi.asw.util.Encrypter;

@Component
public class InsertP implements Insert {

	@Autowired
	private WriteReport writeReport;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private UserService userService;	
	
	
	@Override
	public List<Citizen> insert(List<Citizen> citizens, String file) {
		
		List<Citizen> citizensInserted = new ArrayList<Citizen>();
		for(Citizen c: citizens) {
			Citizen aux = citizenService.findByDni(c.getDni());
			
			if(aux == null) {	
				String password = c.getUser().getPassword();								
				User user = c.getUser();
				user.setPassword(Encrypter.getInstance().makeSHA1Hash(user.getPassword()));
				citizenService.addCitizen(c);
				userService.addUser(user);
				user.setPassword(password);
				citizensInserted.add(c);
			}
			else {				
				String error = "No se puede introducir el ciudadano con dni "+ c.getDni() + " porque ya está cargado en la base de datos";
				writeReport.report(error, file, Level.ERROR);
			}
		}
		return citizensInserted;
	}
}