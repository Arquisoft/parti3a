package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uniovi.asw.business.CommentVoteService;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.CommentVote;
import es.uniovi.asw.model.User;
import es.uniovi.asw.model.types.VoteStatus;
import es.uniovi.asw.persistence.CommentVoteRepository;

@Service
@Transactional
public class CommentVoteImpl implements CommentVoteService {

	@Autowired
	private CommentVoteRepository commentVoteRepository;
	
	@Autowired
	private JpaContext jpaContext;
	
	@Override
	public CommentVote addComentVote(Comment comment, User user, VoteStatus vote) {
		Comment auxC = jpaContext.getEntityManagerByManagedType(Comment.class).merge(comment);
		User auxU = jpaContext.getEntityManagerByManagedType(User.class).merge(user);
		CommentVote commentVote = new CommentVote(auxC, auxU, vote);
		return commentVoteRepository.save(commentVote);
	}

}