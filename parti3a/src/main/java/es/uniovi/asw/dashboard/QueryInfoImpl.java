package es.uniovi.asw.dashboard;

import org.springframework.beans.factory.annotation.Autowired;

import es.uniovi.asw.persistence.CommentRepository;
import es.uniovi.asw.persistence.SuggestionRepository;

public class QueryInfoImpl implements QueryInfo {

	@Autowired
	private SuggestionRepository suggestionRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public DashboardData queryInfo() {
		return new DashboardData(suggestionRepository.findAll(), commentRepository.findAll());
	}
}
