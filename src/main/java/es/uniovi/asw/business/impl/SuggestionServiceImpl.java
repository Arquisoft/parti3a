package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.SuggestionService;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.SuggestionVote;
import es.uniovi.asw.model.User;
import es.uniovi.asw.model.types.VoteStatus;
import es.uniovi.asw.persistence.SuggestionRepository;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	
	@Autowired
	private SuggestionRepository suggestionRepository;

	@Override
	public Suggestion addSuggestion(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	@Override
	public void deleteSuggestion(Suggestion suggestion) {
		suggestionRepository.delete(suggestion);
	}

	@Override
	public void updateSuggestion(Suggestion suggestion) {
		suggestionRepository.save(suggestion);
	}

	@Override
	public Suggestion findSuggestion(Long suggestionId) {
		return suggestionRepository.findOne(suggestionId);
	}
	
	@Override
	public void addVote(Suggestion suggestion, User user, VoteStatus vote) {
		new SuggestionVote(suggestion, user, vote);
		suggestionRepository.save(suggestion);		
	}

	@Override
	public void deleteByIdSuggestion(Long suggestionId) {
		suggestionRepository.delete(suggestionId);
	}

	@Override
	public List<Suggestion> findAllSuggestions() {
		return suggestionRepository.findAll();
	}

	
	
}