package es.uniovi.asw.participationSystem.DBManagement;

import java.util.List;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.RestringedWords;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.SuggestionVote;
import es.uniovi.asw.model.User;
import es.uniovi.asw.model.types.VoteStatus;


public interface ManageSuggestionDB {
	
	public Suggestion addSuggestion(Suggestion suggestion);
	public void updateSuggestion(Suggestion sugerencia);
	public void deleteSuggestion(Long id);
	public Suggestion getSuggestion(Long id);
	public List<Suggestion> getSuggestions();
	public List<Category> findSuggestionCategories();
	public List<RestringedWords> findSuggestionRestringedWords();
	public SuggestionVote voteSuggestion(Suggestion suggestion, User user, VoteStatus vote);
	public Long inFavourVotes(Suggestion suggestion);
	public RestringedWords addRestringedWord(RestringedWords word);
	
}
