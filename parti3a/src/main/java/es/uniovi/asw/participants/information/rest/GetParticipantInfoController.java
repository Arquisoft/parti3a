package es.uniovi.asw.participants.information.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.participants.information.errors.ParticipantNotFound;
import es.uniovi.asw.participants.information.errors.ErrorInterface;
import es.uniovi.asw.util.Encrypter;

/**
 * Representa la información que irá en el JSON cuando se 
 * envíen sus datos al usuario
 * 
 * @author UO247242
 * 
 */
@RestController
public class GetParticipantInfoController implements GetParticipantInfo {

	@Autowired 
	CitizenService citizenService;

	/**
	 * Respuesta HTTP. Incluye información sobre el ciudadano
	 * si existe, sino devuelve error 404 
	 * 
	 * @author UO247242
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/user", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},	
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})	

	public ResponseEntity<GetParticipantInfoResponse> getParticipantInfo(@RequestBody GetParticipantInfoRequest form) {	

		String email = form.getLogin();
		String password = form.getPassword();		
		String encryptedPassword = Encrypter.getInstance().makeSHA1Hash(password);

		Citizen ciudadano = citizenService.getParticipant(email, encryptedPassword);		

		if (ciudadano != null) 
		{					
			GetParticipantInfoResponse citizen = new GetParticipantInfoResponse(ciudadano);
			return ResponseEntity.ok(citizen);
		}
		else throw new ParticipantNotFound();		
	}	

	@ExceptionHandler(ErrorInterface.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	
	public String handleErrorResponses(ErrorInterface error) {
		return error.getJSONError();
	}
}
