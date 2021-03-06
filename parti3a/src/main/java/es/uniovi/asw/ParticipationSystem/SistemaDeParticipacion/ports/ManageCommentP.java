package es.uniovi.asw.ParticipationSystem.SistemaDeParticipacion.ports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.ParticipationSystem.DBManagement.ManageCommentDB;
import es.uniovi.asw.ParticipationSystem.SistemaDeParticipacion.ManageComment;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

@Service
public class ManageCommentP implements ManageComment {
	
	@Autowired
	private ManageCommentDB manageCommentDB;

	@Override
	public Comment addComment(Comment comentario) {
		return manageCommentDB.addComment(comentario);
	}

	@Override
	public void deleteComment(Long id) {
		manageCommentDB.deleteComment(id);
	}

	@Override
	public List<Comment> getCommentsByDate(Suggestion suggestion) {
		return manageCommentDB.getCommentsByDate(suggestion);
	}

	@Override
	public List<Comment> getCommentsByPopularity(Suggestion suggestion) {
		return manageCommentDB.getCommentsByPopularity(suggestion);
	}
}