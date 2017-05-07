package es.uniovi.asw.business;

import es.uniovi.asw.model.*;
import es.uniovi.asw.model.types.VoteStatus;

public interface SuggestionVoteService {

	//CRUD
	public SuggestionVote addSuggestionVote (Suggestion suggestion, User user, VoteStatus vote);
	
	//Otros	

}