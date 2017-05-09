package asw.participants;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.uniovi.asw.Application;
import es.uniovi.asw.participants.information.rest.GetParticipantInfoRequest;
import es.uniovi.asw.participants.information.rest.GetParticipantInfoResponse;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class GetParticipantInfoController {

	// Test RestTemplate to invoke the APIs.
	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testRESTSuccesful() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		GetParticipantInfoRequest request = 
				new GetParticipantInfoRequest("user1@me.com", "user1");

		// Invoking the API
		ResponseEntity<GetParticipantInfoResponse> restResponse = restTemplate
				.postForEntity("http://localhost:8090/user", request, 
						GetParticipantInfoResponse.class);
		
		//Making sure the response exists and HTTP code is 200
		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());
		
		//Getting the body of the response (which contains the user's info)		
		GetParticipantInfoResponse response = restResponse.getBody();

		assertEquals(new Long(1), response.getId());
		assertEquals("Juan", response.getFirstName());
		assertEquals("Rodríguez García", response.getLastName());
		assertEquals(0, response.getAge());
		assertEquals("user1@me.com", response.getEmail());

		// Testing another Citizen (SAME STEPS AS BEFORE)
		request = new GetParticipantInfoRequest("admin@me.com", "admin");

		restResponse = restTemplate.postForEntity("http://localhost:8090/user", 
				request, GetParticipantInfoResponse.class);		

		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());
		
		response = restResponse.getBody();

		assertEquals(new Long(6), response.getId());
		assertEquals("Pedro", response.getFirstName());
		assertEquals("Hernández Pérez", response.getLastName());
		assertEquals(0, response.getAge());
		assertEquals("admin@me.com", response.getEmail());

		/*// Testing another Citizen
		request = new GetParticipantInfoRequest("isalopez@yourmail.com", "1234");
		restResponse = restTemplate.postForEntity("http://localhost:8080/user",
				request, GetParticipantInfoResponse.class);

		assertNotNull(restResponse);

		assertEquals(new Long(10), restResponse.getBody().getId());
		assertEquals("Isabel", restResponse.getBody().getFirstName());
		assertEquals("Lopez Perez", restResponse.getBody().getLastName());
		assertEquals(58, restResponse.getBody().getAge());
		assertEquals("isalopez@yourmail.com", restResponse.getBody().getEmail());*/
	}

	@Test
	public void testRESTFailed() throws JsonProcessingException {

	/*	// Building the Request body data
		// Creating http entity object
		// Testing with wrong password
		GetParticipantInfoRequest request = 
				new GetParticipantInfoRequest("pperez@prueba.com", "1234");

		// Invoking the API
		ResponseEntity<GetParticipantInfoResponse> restResponse = restTemplate
				.postForEntity("http://localhost:8090/user", request, 
						GetParticipantInfoResponse.class);

		//Making sure the response exists and HTTP code is 200
		assertNotNull(restResponse);
		assertEquals(HttpStatus.NOT_FOUND, restResponse.getStatusCode());

		// Testing wrong email
		request = new GetParticipantInfoRequest("agolmay@goomail.com", "1234");

		restResponse = restTemplate.postForEntity("http://localhost:8090/user",
				request, GetParticipantInfoResponse.class);

		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());*/
	}
}
