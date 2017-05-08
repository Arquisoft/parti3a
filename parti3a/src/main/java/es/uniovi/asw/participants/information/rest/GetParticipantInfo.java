package es.uniovi.asw.participants.information.rest;

import org.springframework.http.ResponseEntity;

public interface GetParticipantInfo {

	public ResponseEntity<GetParticipantInfoResponse> getParticipantInfo(GetParticipantInfoRequest form);
}
