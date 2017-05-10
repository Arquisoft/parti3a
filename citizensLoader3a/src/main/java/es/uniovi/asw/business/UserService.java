package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.User;

public interface UserService {
	
	//CRUD
	public User addUser (User user);
	public void deleteUser (User user);
	public void updateUser (User user);
	public User findUser (Long userId);
	
	//Otros
	public List<User> addUsers (List<User> users);
	
}