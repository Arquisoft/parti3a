
package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.CommentService;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.persistence.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private JpaContext jpaContext;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		jpaContext.getEntityManagerByManagedType(Comment.class).remove(comment);
	}

	@Override
	public void updateComment(Comment comment) {
		commentRepository.save(comment);
	}

	@Override
	public Comment findComment(Long commentId) {
		return commentRepository.findOne(commentId);
	}

	@Override
	public void deleteByIdComment(Long commentId) {
		commentRepository.delete(commentId);
	}

	@Override
	public List<Comment> orderByPopularity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findBySuggestionOrderByCreationDateDesc(Suggestion suggestion) {
		return commentRepository.findBySuggestionOrderByCreationDateDesc(suggestion);
	}

	@Override
	public List<Comment> findBySuggestionOrderByCreationDateAsc(Suggestion suggestion) {
		return commentRepository.findBySuggestionOrderByCreationDateAsc(suggestion);
	}

		
}