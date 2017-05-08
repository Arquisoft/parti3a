package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.model.CommentVote;
import es.uniovi.asw.model.CommentVoteKey;

public interface CommentVoteRepository  extends JpaRepository<CommentVote, CommentVoteKey>{

}