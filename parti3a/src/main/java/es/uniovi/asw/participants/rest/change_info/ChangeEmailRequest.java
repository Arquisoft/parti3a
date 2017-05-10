package es.uniovi.asw.participants.rest.change_info;

/**
 * Representa la información que irá en el JSON cuando se 
 * cambie el email del usuario
 * 
 * @author UO246008
 *  
 */
public class ChangeEmailRequest {
	
	private String login;
	private String password;
	private String newEmail;
	
	public ChangeEmailRequest(String email, String password, String newEmail) {	
		this.login = email;
		this.password = password;
		this.newEmail = newEmail;
	}
	
	public ChangeEmailRequest() {}

	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getNewEmail() {
		return newEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((newEmail == null) ? 0 : newEmail.hashCode());
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
		ChangeEmailRequest other = (ChangeEmailRequest) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (newEmail == null) {
			if (other.newEmail != null)
				return false;
		} else if (!newEmail.equals(other.newEmail))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
