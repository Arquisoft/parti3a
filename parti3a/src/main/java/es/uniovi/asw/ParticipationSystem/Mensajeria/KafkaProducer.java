package es.uniovi.asw.ParticipationSystem.Mensajeria;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

public interface KafkaProducer {
	
	public void sendComentario(Comment comment);
	public void sendSugerencia(Suggestion suggestion);
}
