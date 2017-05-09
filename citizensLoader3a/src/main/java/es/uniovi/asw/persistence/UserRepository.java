package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
}