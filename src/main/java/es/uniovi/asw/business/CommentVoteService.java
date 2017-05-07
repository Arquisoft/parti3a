package es.uniovi.asw.business;

import es.uniovi.asw.model.*;
import es.uniovi.asw.model.types.VoteStatus;

public interface CommentVoteService {

	//CRUD
	public CommentVote addComentVote (Comment comment, User user, VoteStatus vote);
	
	//Otros
	
}