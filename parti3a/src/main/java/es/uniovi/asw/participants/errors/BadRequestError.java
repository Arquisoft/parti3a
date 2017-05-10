package es.uniovi.asw.participants.errors;

public class BadRequestError extends ErrorInterface {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"400: Bad Request\"}";
	}

	@Override
	public String getStringError() {
		return "400: Bad request";
	}
}
