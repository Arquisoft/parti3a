package es.uniovi.asw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findBySuggestionOrderByCreationDateDesc(Suggestion suggestion);
	List<Comment> findBySuggestionOrderByCreationDateAsc(Suggestion suggestion);

}