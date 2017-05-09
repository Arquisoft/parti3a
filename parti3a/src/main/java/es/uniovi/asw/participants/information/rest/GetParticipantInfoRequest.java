package es.uniovi.asw.participants.information.rest;

/**
 * Representa la información que irá en el JSON cuando se 
 * piden los datos del usuario
 * 
 * @author UO247242
 *  
 */
public class GetParticipantInfoRequest {
	
	private String login;
	private String password;
	
	public GetParticipantInfoRequest(String login, String password) {	

		this.login = login;
		this.password = password;
	}
	
	public GetParticipantInfoRequest() {}

	//Getters y Setters
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	
}
