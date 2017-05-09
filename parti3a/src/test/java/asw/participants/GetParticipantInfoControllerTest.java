package asw.participants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;

import cucumber.api.java.Before;
import es.uniovi.asw.Application;
import es.uniovi.asw.participants.rest.get_info.GetParticipantInfoRequest;
import es.uniovi.asw.participants.rest.get_info.GetParticipantInfoResponse;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class GetParticipantInfoControllerTest {

	// Test RestTemplate to invoke the APIs.
	private TestRestTemplate restTemplate;
	
	@Value("${local.server.port}")
	private int port;

	private URL base;
	
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		restTemplate = new TestRestTemplate();
	}

	@Test
	public void testRESTSuccesful() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		GetParticipantInfoRequest request = 
				new GetParticipantInfoRequest("user1@me.com", "user1");

		// Invoking the API
		ResponseEntity<GetParticipantInfoResponse> restResponse = restTemplate
				.postForEntity(base.toString(), request, 
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
				restTemplate.postForEntity(base.toString(), 
				new GetParticipantInfoRequest("user1@me.com", "1234"), 
				String.class);
		
		assertEquals(error, restResponse.getBody());		
	}
}
