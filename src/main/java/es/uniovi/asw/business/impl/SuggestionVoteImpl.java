package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uniovi.asw.business.SuggestionVoteService;
import es.uniovi.asw.model.*;
import es.uniovi.asw.model.types.VoteStatus;
import es.uniovi.asw.persistence.SuggestionVoteRepository;

@Service
@Transactional
public class SuggestionVoteImpl implements SuggestionVoteService {

	@Autowired
	private SuggestionVoteRepository suggestionVoteRepository;
	
	@Autowired
	private JpaContext jpaContext;
	
	@Override
	public SuggestionVote addSuggestionVote(Suggestion suggestion, User user, VoteStatus vote) {
		Suggestion auxS = jpaContext.getEntityManagerByManagedType(Suggestion.class).merge(suggestion);
		User auxU = jpaContext.getEntityManagerByManagedType(User.class).merge(user);
		SuggestionVote suggestionVote = new SuggestionVote(auxS, auxU, vote);
		return suggestionVoteRepository.save(suggestionVote);
	}
	
}