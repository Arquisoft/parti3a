package es.uniovi.asw.participants.rest.change_info;

/**
 * Representa la información que irá en el JSON cuando se 
 * confirme el cambio de email al participante
 * 
 * @author UO246008
 * 
 */
public class ChangeEmailResponse {
	
	private String email;
	private String message;

	public ChangeEmailResponse(String email, String message) 
	{		
		this.email = email;
		this.message = message;
	}
	
	public ChangeEmailResponse() { }

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
		ChangeEmailResponse other = (ChangeEmailResponse) obj;
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
