package es.uniovi.asw.dbupdate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.UserService;
import es.uniovi.asw.model.Association;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class InsertTest {
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private UserService userService;
	
	
	private Citizen c1;
	private Citizen c2;
	private Citizen c3;
	
	private User u1;
	private User u2;
	private User u3;

	@Before
	public void setUp() {
		c1 = new Citizen("Juan", "Torres Pardo", "juan@example.com", null, "mi casa", "Español", "90500084Y");
		u1 = new User("u1","u1", c1);		
		Association.Asignar.link(u1, c1);
		
		c2 = new Citizen("Luis", "López Fernando", "luis@example.com", null, "mi casa", "Español", "19160962F");
		u2 = new User("u2"," u2", c2);
		Association.Asignar.link( u2, c2);
		
		c3 = new Citizen("Ana", "Torres Pardo", "ana@example.com", null, "mi casa", "Francés", "09940449X");
		u3 = new User("u3","u3", c3);
		Association.Asignar.link(u3, c3);
				
	}
	
	@Test
	public void t1_testInsertAndDeletes() {		
		citizenService.addCitizen(c1);
		userService.addUser(c1.getUser());
		citizenService.addCitizen(c2);
		userService.addUser(c2.getUser());
		citizenService.addCitizen(c3);
		userService.addUser(c3.getUser());
		
		Citizen aux = citizenService.findCitizen(c1.getId());
		assertEquals(c1.toString(), aux.toString());
		
		aux = citizenService.findCitizen(c2.getId());
		assertEquals(c2.toString(), aux.toString());
		
		aux = citizenService.findCitizen(c3.getId());
		assertEquals(c3.toString(), aux.toString());	
		
		User aux2 = userService.findUser(c1.getUser().getId());
		assertEquals(c1.getUser().toString(), aux2.toString());
		
		aux2 = userService.findUser(c2.getUser().getId());
		assertEquals(c2.getUser().toString(), aux2.toString());
		
		aux2 = userService.findUser(c3.getUser().getId());
		assertEquals(c3.getUser().toString(), aux2.toString());
		
		citizenService.deleteCitizen(c1);
		citizenService.deleteCitizen(c2);
		citizenService.deleteCitizen(c3);
		
		User aux3 = userService.findUser(c1.getId());
		assertNull(aux3);
		
		aux3 = userService.findUser(c2.getUser().getId());
		assertNull(aux3);
		
		aux3 = userService.findUser(c3.getUser().getId());
		assertNull(aux3);		
		
		Citizen aux4 = citizenService.findCitizen(c1.getId());
		assertNull(aux4);
		
		aux4 = citizenService.findCitizen(c2.getId());
		assertNull(aux4);
		
		aux4 = citizenService.findCitizen(c3.getId());
		assertNull(aux4);	
	}
	
}