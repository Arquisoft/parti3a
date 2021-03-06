package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	User findByUsername(String username);
	
	@Query("select count(u) from User u where u.isAdmin = true")
	int findNumberOfAdmins();
}