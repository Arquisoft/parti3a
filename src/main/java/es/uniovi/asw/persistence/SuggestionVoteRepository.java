package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.SuggestionVote;
import es.uniovi.asw.model.SuggestionVoteKey;

@Repository
public interface SuggestionVoteRepository extends JpaRepository<SuggestionVote, SuggestionVoteKey> {

}