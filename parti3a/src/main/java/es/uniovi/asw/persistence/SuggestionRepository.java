package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.*;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {	
	
	Suggestion findByContents(String contents);
	
}