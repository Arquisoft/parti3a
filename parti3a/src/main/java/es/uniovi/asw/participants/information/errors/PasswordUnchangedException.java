package es.uniovi.asw.participants.information.errors;

public class PasswordUnchangedException extends BadRequestError {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"La nueva contraseña debe ser diferente\"}";
	}

	@Override
	public String getStringError() {
		return "La nueva contraseña debe ser diferente";
	}
}
