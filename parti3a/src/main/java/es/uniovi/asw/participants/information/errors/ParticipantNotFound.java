package es.uniovi.asw.participants.information.errors;

public class ParticipantNotFound extends ErrorInterface {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"404: Not found\"}";
	}

	@Override
	public String getStringError() {
		return "404: Not found";
	}
}
