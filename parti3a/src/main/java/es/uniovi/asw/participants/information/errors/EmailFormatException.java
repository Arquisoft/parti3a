package es.uniovi.asw.participants.information.errors;

public class EmailFormatException extends BadRequestError {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Formato de e-mail incorrecto\"}";
	}

	@Override
	public String getStringError() {
		return "Formato de e-mail incorrecto";
	}
}
