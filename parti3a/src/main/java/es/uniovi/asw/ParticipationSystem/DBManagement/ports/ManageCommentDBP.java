package es.uniovi.asw.participationSystem.DBManagement.ports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.CommentService;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.participationSystem.DBManagement.ManageCommentDB;
import es.uniovi.asw.participationSystem.Mensajeria.KafkaProducer;

@Service
public class ManageCommentDBP implements ManageCommentDB {
	
	@Autowired
	private KafkaProducer producer;
	
	@Autowired
	private CommentService commentService;

	@Override
	public Comment addComment(Comment comentario) {
		Comment comment = commentService.addComment(comentario);
		if(comment!=null){
			producer.sendComentario(comment);
		}
		return comment;
	}

	@Override
	public void deleteComment(Long id) {
		commentService.deleteByIdComment(id);
	}

	@Override
	public List<Comment> getCommentsByDate(Suggestion suggestion) {
		return commentService.findBySuggestionOrderByCreationDateDesc(suggestion);
	}

	@Override
	public List<Comment> getCommentsByPopularity(Suggestion suggestion) {
		return commentService.orderByPopularity();
	}

}
