package es.uniovi.asw.participants.rest.get_info;

import es.uniovi.asw.model.Citizen;


/**
 * Representa la información que irá en el JSON cuando se 
 * envíen sus datos al usuario
 * 
 * @author UO247242
 * 
 */
public class GetParticipantInfoResponse {
	
	private Long id;	
	
	private String firstName;
	private String lastName;
	private int age;
	private String email;

	public GetParticipantInfoResponse(Citizen ciudadano) 
	{		
		this.id = ciudadano.getId();
		this.firstName = ciudadano.getName();
		this.lastName = ciudadano.getSurname();
		this.age = ciudadano.getAge();
		this.email = ciudadano.getEmail();
	}
	
	public GetParticipantInfoResponse() { }

	//Getters y Setters
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Metodos

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GetParticipantInfoResponse other = (GetParticipantInfoResponse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		
}
