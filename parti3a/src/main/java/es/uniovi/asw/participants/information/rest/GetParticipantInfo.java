package es.uniovi.asw.participants.information.rest;

import org.springframework.http.ResponseEntity;

/**
 * Interfaz para obtener la información
 * del participante
 * 
 * @author UO247242
 *
 */
public interface GetParticipantInfo {

	public ResponseEntity<GetParticipantInfoResponse> getParticipantInfo(GetParticipantInfoRequest form);
}
