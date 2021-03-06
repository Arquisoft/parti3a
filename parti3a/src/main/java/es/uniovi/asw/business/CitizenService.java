package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.Citizen;

public interface CitizenService {

	//CRUD
	public Citizen addCitizen (Citizen citizen);
	public void deleteCitizen (Citizen citizen);
	public void updateInfo(Citizen citizen);
	public Citizen findCitizen (Long citizenId);
	
	//Otros 
	public Citizen findByDni (String dni);
	public List<Citizen> findAllCitizens ();
	
	public Citizen findByEmailAndPassword(String email, String password);
}