package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.RestringedWords;

@Repository
public interface RestringedWordsRepository extends JpaRepository<RestringedWords, Long>{

}