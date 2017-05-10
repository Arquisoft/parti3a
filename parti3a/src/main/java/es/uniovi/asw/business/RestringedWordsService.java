package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.RestringedWords;

public interface RestringedWordsService {
	
	public RestringedWords addRestringedWord(RestringedWords word);
	public List<RestringedWords> findAllWords();
}
