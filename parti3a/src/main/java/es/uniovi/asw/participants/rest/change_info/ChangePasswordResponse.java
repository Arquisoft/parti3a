package es.uniovi.asw.participants.rest.change_info;

/**
 * Representa la información que irá en el JSON cuando se 
 * envíen sus datos al usuario
 * 
 * @author UO247242
 * 
 */
public class ChangePasswordResponse {
	
	private String email;
	private String message;
	
	public ChangePasswordResponse(String email, String message) 
	{		
		this.email = email;
		this.message = message;
	}
	
	public ChangePasswordResponse() { }

	public String getEmail() {
		return email;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		ChangePasswordResponse other = (ChangePasswordResponse) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
