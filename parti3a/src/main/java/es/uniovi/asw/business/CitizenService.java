package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.Citizen;

public interface CitizenService {

	//CRUD
	public Citizen addCitizen (Citizen citizen);
	public void deleteCitizen (Citizen citizen);
	public void updateCitizen (Citizen citizen);
	public Citizen findCitizen (Long citizenId);
	
	//Otros 
	public Citizen findByDni (String dni);
	public List<Citizen> findAllCitizens ();
	
	public Citizen getParticipant(String email, String password);
	
	public void changeEmail(String email, String password, String newEmail);
	public void changePassword(String email, String password, String newPassword);
}