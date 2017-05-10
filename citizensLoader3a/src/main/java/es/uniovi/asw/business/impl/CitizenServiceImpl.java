package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.CitizenRepository;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService{

	@Autowired
	private CitizenRepository citizenRepository;
	
	@Override
	public Citizen addCitizen(Citizen citizen) {		
		return citizenRepository.save(citizen);
	}

	@Override
	public void deleteCitizen(Citizen citizen) {
		citizenRepository.delete(citizen);
	}

	@Override
	public void updateCitizen(Citizen citizen) {
		citizenRepository.save(citizen);
	}

	@Override
	public Citizen findCitizen(Long citizenId) {
		return citizenRepository.findOne(citizenId);
	}

	@Override
	public Citizen findByDni(String dni) {
		return citizenRepository.findByDni(dni);
	}

	@Override
	public List<Citizen> addCitizens(List<Citizen> citizens) {
		return citizenRepository.save(citizens);
	}

}