package es.uniovi.asw.ParticipationSystem.SistemaDeParticipacion;

import java.util.List;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

public interface ManageComment {
	
	public Comment addComment(Comment comentario);
	public void deleteComment(Long id);
	public List<Comment> getCommentsByDate(Suggestion suggestion);
	public List<Comment> getCommentsByPopularity(Suggestion suggestion);
}
