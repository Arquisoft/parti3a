package es.uniovi.asw.business.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.UserRepository;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService{

	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JpaContext jpaContext;
	
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
		User user = citizen.getUser();
		user = jpaContext.getEntityManagerByManagedType(user.getClass()).merge(user);
		userRepository.save(user);
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