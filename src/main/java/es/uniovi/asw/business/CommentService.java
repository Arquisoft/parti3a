package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

public interface CommentService {
	
	//CRUD
	public Comment addComment (Comment comment);
	public void deleteComment (Comment comment);
	public void updateComment (Comment comment);
	public Comment findComment (Long commentId);
	
	//Otros
	public void deleteByIdComment (Long commentId);
	public List<Comment> orderByPopularity();
	public List<Comment> findBySuggestionOrderByCreationDateDesc(Suggestion suggestion);
	public List<Comment> findBySuggestionOrderByCreationDateAsc(Suggestion suggestion);
	
}