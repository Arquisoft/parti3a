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
import es.uniovi.asw.participants.information.errors.BadRequestError;
import es.uniovi.asw.participants.information.utils.Check;
import es.uniovi.asw.util.Encrypter;

/**
 * Controlador REST para cambiar la contraseña
 * 
 * @author UO246008
 *
 */
@RestController
public class ChangeInfoController implements ChangeInfo {

	@Autowired 
	CitizenService citizenService;

	/**
	 * Petición POST para cambiar el email
	 * 
	 * @param form argumentos de la pertición
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeEmail",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public ResponseEntity<ChangeEmailResponse> changeEmail(@RequestBody ChangeEmailRequest form) {
		String email = form.getEmail();
		String password = form.getPassword();
		String newEmail = form.getNewEmail();
		
		password = Encrypter.getInstance().makeSHA1Hash(password);
		
		Check.participantExists(email, password, citizenService);
		Check.validEmailFormat(newEmail);
		Check.differentEmail(email, newEmail);
		
		//Si todo está bien, cambiamos su email y respondemos
		citizenService.changeEmail(email, password, newEmail);
		
		return ResponseEntity.ok(new ChangeEmailResponse(email, "E-mail cambiado a " + newEmail));
	}

	/**
	 * Petición POST para cambiar la contraseña
	 * 
	 * @param form argumentos de la petición
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changePassword",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest form) {
		String email = form.getLogin();
		String password = form.getPassword();
		String newPassword = form.getNewPassword();
		
		password = Encrypter.getInstance().makeSHA1Hash(password);
		newPassword = Encrypter.getInstance().makeSHA1Hash(newPassword);
		
		Check.participantExists(email, password, citizenService);
		Check.passwordNotEmpty(newPassword);
		Check.differentPassword(password, newPassword);
		
		//Si todo es correcto, cambiamos la clave y retornamos la respuesta
		citizenService.changePassword(email, password, newPassword);
		
		return ResponseEntity.ok(new ChangePasswordResponse(email, "Contraseña cambiada correctamente"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestError.class)
	public String handleBadRequest(BadRequestError error) {
	    return error.getJSONError();
	} 
}
