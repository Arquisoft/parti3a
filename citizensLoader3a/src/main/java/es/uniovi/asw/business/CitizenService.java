package es.uniovi.asw.business;

import es.uniovi.asw.model.Citizen;

public interface CitizenService {

	//CRUD
	public Citizen addCitizen (Citizen citizen);
	public void deleteCitizen (Citizen citizen);
	public void updateCitizen (Citizen citizen);
	public Citizen findCitizen (Long citizenId);
	
	//Otros 
	public Citizen findByDni (String dni);
	
}