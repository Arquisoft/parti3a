package es.uniovi.asw.participants.information.rest;

import org.springframework.http.ResponseEntity;

/**
 * Interfaz para cambiar la información del
 * participante
 * 
 * @author UO246008
 *
 */
public interface ChangeInfo {

	public ResponseEntity<ChangeEmailResponse> changeEmail(ChangeEmailRequest form);
	
	public ResponseEntity<ChangePasswordResponse> changePassword(ChangePasswordRequest form);
}
