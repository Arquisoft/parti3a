package es.uniovi.asw.participants.information.errors;

public class PasswordEmptyException extends BadRequestError {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Nueva contraseña en blanco\"}";
	}

	@Override
	public String getStringError() {
		return "Nueva contraseña en blanco";
	}
}
