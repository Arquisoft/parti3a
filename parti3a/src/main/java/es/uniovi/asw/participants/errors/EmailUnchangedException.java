package es.uniovi.asw.participants.errors;

public class EmailUnchangedException extends BadRequestError {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"El nuevo e-mail debe ser diferente\"}";
	}

	@Override
	public String getStringError() {
		return "El nuevo e-mail debe ser diferente";
	}
}
