package es.uniovi.asw.participants.information.rest;

/**
 * Representa la información que irá en el JSON cuando se 
 * cambia la contraseña del participante
 * 
 * @author UO246008
 *  
 */
public class ChangePasswordRequest {
	
	private String login;
	private String password;
	private String newPassword;
	
	public ChangePasswordRequest(String login, String password, String newPassword) {	
		this.login = login;
		this.password = password;
		this.newPassword = newPassword;
	}
	
	public ChangePasswordRequest() {}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
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
		ChangePasswordRequest other = (ChangePasswordRequest) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
