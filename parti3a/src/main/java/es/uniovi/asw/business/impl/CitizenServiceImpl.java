package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.UserRepository;

@Service
public class CitizenServiceImpl implements CitizenService{

	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Citizen addCitizen(Citizen citizen) {
		return citizenRepository.save(citizen);
	}

	@Override
	public void deleteCitizen(Citizen citizen) {
		citizenRepository.delete(citizen);
	}

	@Override
	public void updateInfo(Citizen citizen) {
		userRepository.save(citizen.getUser());
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
	public List<Citizen> findAllCitizens() {
		return citizenRepository.findAll();
	}
	
	@Override
	public Citizen findByEmailAndPassword(String email, String password) {
		return citizenRepository.findByEmailAndPassword(email, password);
	}
}