package es.uniovi.asw.ParticipationSystem.DBManagement.ports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.ParticipationSystem.DBManagement.ManageSuggestionDB;
import es.uniovi.asw.ParticipationSystem.Mensajeria.KafkaProducer;
import es.uniovi.asw.business.CategoryService;
import es.uniovi.asw.business.RestringedWordsService;
import es.uniovi.asw.business.SuggestionService;
import es.uniovi.asw.business.SuggestionVoteService;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.RestringedWords;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.SuggestionVote;
import es.uniovi.asw.model.User;
import es.uniovi.asw.model.types.VoteStatus;


@Service
public class ManageSuggestionDBP implements ManageSuggestionDB {
	
	@Autowired
	private KafkaProducer producer;
	
	@Autowired
	private SuggestionService suggestionService;
	
	@Autowired
	private SuggestionVoteService voteService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RestringedWordsService wordService;

	@Override
	public Suggestion addSuggestion(Suggestion suggestion) {
		Suggestion sugerencia = suggestionService.addSuggestion(suggestion);
		if(sugerencia!=null){
			producer.sendSugerencia(sugerencia);
		}
		return sugerencia;
	}

	@Override
	public void updateSuggestion(Suggestion sugerencia) {
		suggestionService.updateSuggestion(sugerencia);
	}

	@Override
	public void deleteSuggestion(Long id) {
		suggestionService.deleteByIdSuggestion(id);
	}

	@Override
	public Suggestion getSuggestion(Long id) {
		return suggestionService.findSuggestion(id);
	}

	@Override
	public List<Suggestion> getSuggestions() {
		return suggestionService.findAllSuggestions();
	}

	@Override
	public List<Category> findSuggestionCategories() {
		return categoryService.findAllCategories();
	}

	@Override
	public List<RestringedWords> findSuggestionRestringedWords() {
		return wordService.findAllWords();
	}

	@Override
	public SuggestionVote voteSuggestion(Suggestion suggestion, User user, VoteStatus vote) {
		return voteService.addSuggestionVote(suggestion, user, vote);
	}

	@Override
	public Long inFavourVotes(Suggestion suggestion) {
		return null;
	}

	@Override
	public RestringedWords addRestringedWord(RestringedWords word) {
		return wordService.addRestringedWord(word);
	}

	@Override
	public Suggestion addSuggestionRest(Suggestion suggestion) {
		Suggestion sugerencia = suggestionService.addSuggestionRest(suggestion);
		if(sugerencia!=null){
			producer.sendSugerencia(sugerencia);
		}
		return sugerencia;
	}
}
