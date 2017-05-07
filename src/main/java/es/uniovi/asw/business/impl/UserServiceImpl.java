package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.UserService;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUser(Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User findByUserAndPassword(String username, String password) {
		return userRepository.findByUserAndPassword(username, password);
	}
	
}