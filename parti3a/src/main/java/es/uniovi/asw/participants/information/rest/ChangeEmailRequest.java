package es.uniovi.asw.participants.information.rest;

/**
 * Representa la información que irá en el JSON cuando se 
 * cambie el email del usuario
 * 
 * @author UO246008
 *  
 */
public class ChangeEmailRequest {
	
	private String email;
	private String password;
	private String newEmail;
	
	public ChangeEmailRequest(String email, String password, String newEmail) {	
		this.email = email;
		this.password = password;
		this.newEmail = newEmail;
	}
	
	public ChangeEmailRequest() {}

	public String getEmail() {
		return email;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
