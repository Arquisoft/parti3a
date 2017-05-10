package es.uniovi.asw.participationSystem.SistemaDeParticipacion.ports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.participationSystem.DBManagement.ManageCommentDB;
import es.uniovi.asw.participationSystem.SistemaDeParticipacion.ManageComment;

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