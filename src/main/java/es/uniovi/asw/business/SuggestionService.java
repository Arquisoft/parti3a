package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.*;
import es.uniovi.asw.model.types.VoteStatus;

public interface SuggestionService {
	
	//CRUD
	public Suggestion addSuggestion (Suggestion suggestion);	
	public void deleteSuggestion (Suggestion suggestion);
	public void updateSuggestion (Suggestion suggestion);
	public Suggestion findSuggestion (Long suggestionId);
	
	//Otros
	public void addVote (Suggestion suggestion, User user, VoteStatus vote);
	public void deleteByIdSuggestion (Long suggestionId);
	public List<Suggestion> findAllSuggestions();
	
}