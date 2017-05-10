package es.uniovi.asw.participants.rest.get_info;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetParticipantInfoRequest other = (GetParticipantInfoRequest) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
