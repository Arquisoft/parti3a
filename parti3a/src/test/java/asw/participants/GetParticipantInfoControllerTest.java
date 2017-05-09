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
public class GetParticipantInfoControllerTest {

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

		assertEquals("Juan", response.getFirstName());
		assertEquals("Rodríguez García", response.getLastName());
		assertEquals(0, response.getAge());
		assertEquals("user1@me.com", response.getEmail());
	}

	@Test
	public void testRESTFailed() throws JsonProcessingException {
		
		//Expected return in all cases
		String error = "{\"reason\": \"404: Not found\"}";
		
		// Testing with wrong password
		ResponseEntity<String> restResponse = 
				restTemplate.postForEntity("http://localhost:8090/user", 
				new GetParticipantInfoRequest("user1@me.com", "1234"), 
				String.class);
		
		assertEquals(error, restResponse.getBody());		
	}
}
