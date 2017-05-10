package es.uniovi.asw.participants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.uniovi.asw.Application;
import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.participants.rest.change_info.ChangeEmailRequest;
import es.uniovi.asw.participants.rest.change_info.ChangeEmailResponse;
import es.uniovi.asw.participants.rest.change_info.ChangePasswordRequest;
import es.uniovi.asw.participants.rest.change_info.ChangePasswordResponse;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class ChangeInfoControllerTest {

	// Test RestTemplate to invoke the APIs.
	private TestRestTemplate restTemplate;

	@Value("${local.server.port}")
	private int port;
	private URL base;

	@Autowired
	CitizenService service;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		restTemplate = new TestRestTemplate();
	}

	@Test
	public void changeEmailSuccesfullyTest() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		ChangeEmailRequest request = 
				new ChangeEmailRequest("user1@me.com", "user1", "user@me.com");

		// Invoking the API
		ResponseEntity<ChangeEmailResponse> restResponse = restTemplate
				.postForEntity(base.toString() + "changeEmail", request, 
						ChangeEmailResponse.class);

		//Making sure the response exists and HTTP code is 200
		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());

		//Getting the body of the response (which contains the message)		
		ChangeEmailResponse response = restResponse.getBody();
		assertEquals("E-mail cambiado a user@me.com", response.getMessage());

		//Reversing the changes
		request = new ChangeEmailRequest("user@me.com", "user1", "user1@me.com");

		restResponse = restTemplate.postForEntity(base.toString() + 
				"changeEmail", request, ChangeEmailResponse.class);

		//Making sure the response exists and HTTP code is 200
		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());

		//Getting the body of the response (which contains the message)		
		response = restResponse.getBody();
		assertEquals("E-mail cambiado a user1@me.com", response.getMessage());
	}

	@Test
	public void newEmailUnchangedTest() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		ChangeEmailRequest request = 
				new ChangeEmailRequest("user1@me.com", "user1", "user1@me.com");

		// Invoking the API
		ResponseEntity<String> restResponse = restTemplate
				.postForEntity(base.toString() + "changeEmail", request, 
						String.class);

		//Making sure the response exists and HTTP code is 400 (Bad Request)
		assertNotNull(restResponse);
		assertEquals(HttpStatus.BAD_REQUEST, restResponse.getStatusCode());	

		//Checking that the message in the response is the right one
		String error = "{\"reason\": \"El nuevo e-mail debe ser diferente\"}";
		assertEquals(error, restResponse.getBody());	
	}
	
	@Test
	public void newEmailWithWrongFormatTest() throws JsonProcessingException {
		
		// Building the Request body data
		// Creating http entity object
		ChangeEmailRequest request = 
				new ChangeEmailRequest("user1@me.com", "user1", "user1mecom");

		// Invoking the API
		ResponseEntity<String> restResponse = restTemplate
				.postForEntity(base.toString() + "changeEmail", request, 
						String.class);

		//Making sure the response exists and HTTP code is 400 (Bad Request)
		assertNotNull(restResponse);
		assertEquals(HttpStatus.BAD_REQUEST, restResponse.getStatusCode());	

		//Checking that the message in the response is the right one
		String error = "{\"reason\": \"Formato de e-mail incorrecto\"}";
		assertEquals(error, restResponse.getBody());	
	}

	@Test
	public void changePasswordSuccesfullyTest() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		ChangePasswordRequest request = 
				new ChangePasswordRequest("user1@me.com", "user1", "aaa");

		// Invoking the API
		ResponseEntity<ChangePasswordResponse> restResponse = restTemplate
				.postForEntity(base.toString() + "changePassword", request, 
						ChangePasswordResponse.class);

		//Making sure the response exists and HTTP code is 200
		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());

		//Getting the body of the response (which contains the message)		
		ChangePasswordResponse response = restResponse.getBody();
		assertEquals("Contraseña cambiada correctamente", response.getMessage());

		//Reversing the changes
		request = new ChangePasswordRequest("user1@me.com", "aaa", "user1");

		restResponse = restTemplate.postForEntity(base.toString() + 
				"changePassword", request, ChangePasswordResponse.class);

		//Making sure the response exists and HTTP code is 200
		assertNotNull(restResponse);
		assertEquals(HttpStatus.OK, restResponse.getStatusCode());

		//Getting the body of the response (which contains the message)		
		response = restResponse.getBody();
		assertEquals("Contraseña cambiada correctamente", response.getMessage());
	}

	@Test
	public void passwordUnchangedTest() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		ChangePasswordRequest request = 
				new ChangePasswordRequest("user1@me.com", "user1", "user1");

		// Invoking the API
		ResponseEntity<String> restResponse = restTemplate
				.postForEntity(base.toString() + "changePassword", request, 
						String.class);

		//Making sure the response exists and HTTP code is 400 (Bad Request)
		assertNotNull(restResponse);
		assertEquals(HttpStatus.BAD_REQUEST, restResponse.getStatusCode());	

		//Checking that the message in the response is the right one
		String error = "{\"reason\": \"La nueva contraseña debe ser diferente\"}";
		assertEquals(error, restResponse.getBody());	
	}

	@Test
	public void passwordEmptyTest() throws JsonProcessingException {

		// Building the Request body data
		// Creating http entity object
		ChangePasswordRequest request = 
				new ChangePasswordRequest("user1@me.com", "user1", "");

		// Invoking the API
		ResponseEntity<String> restResponse = restTemplate
				.postForEntity(base.toString() + "changePassword", request, 
						String.class);

		//Making sure the response exists and HTTP code is 400 (Bad Request)
		assertNotNull(restResponse);
		assertEquals(HttpStatus.BAD_REQUEST, restResponse.getStatusCode());	

		//Checking that the message in the response is the right one
		String error = "{\"reason\": \"Nueva contraseña en blanco\"}";
		assertEquals(error, restResponse.getBody());	
	}
}
