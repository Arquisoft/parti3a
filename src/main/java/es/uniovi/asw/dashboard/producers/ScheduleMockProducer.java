package es.uniovi.asw.dashboard.producers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.SuggestionVote;
import es.uniovi.asw.model.types.VoteStatus;
import es.uniovi.asw.persistence.CommentRepository;
import es.uniovi.asw.persistence.SuggestionRepository;
import es.uniovi.asw.persistence.SuggestionVoteRepository;


@Service
@Transactional
public class ScheduleMockProducer {

	@Autowired
	private MockKafkaProducer kafkaProducer;

	@Autowired
	private SuggestionRepository sugerenciaRepository;
	
	@Autowired
	private CommentRepository comentarioRepository;

	@Autowired
	private SuggestionVoteRepository voteRepository;

	@Autowired
	private JpaContext jpaContext;
	
	@Scheduled(fixedRate = 5000)
	public void trigger() {
		kafkaProducer.sendSugerencia();
		//kafkaProducer.sendComentario();

		List<Suggestion> sugerencias = sugerenciaRepository.findAll();

		if (!sugerencias.isEmpty()) {
			for (int i = 0; i < 5; i++) {
				int index = (int) (Math.random() * sugerencias.size());
				Suggestion sugerencia = saveSugerencia(sugerencias.get(index));
				
				sugerencia = jpaContext.getEntityManagerByManagedType(sugerencia.getClass())
										.merge(sugerencia);
				
				kafkaProducer.sendMessageJSON("sugerencias", sugerencia);

				Comment comentario = new Comment("contenido" + System.currentTimeMillis(),
						sugerencia, sugerencia.getUser());
				
				comentarioRepository.save(comentario);
				kafkaProducer.sendMessageJSON("comentarios", comentario);
			}
		}
	}
	
	private Suggestion saveSugerencia(Suggestion sugerencia) {
		sugerencia = jpaContext.getEntityManagerByManagedType(sugerencia.getClass())
								.merge(sugerencia);
		SuggestionVote suggestionVote;
		
		if (Math.round(Math.random()) == 1)
			suggestionVote = new SuggestionVote(sugerencia, sugerencia.getUser(), 
					VoteStatus.IN_FAVOUR);
		else
			suggestionVote = new SuggestionVote(sugerencia, sugerencia.getUser(),
					VoteStatus.AGAINST);

		voteRepository.save(suggestionVote);
		sugerencia.getVotes().add(suggestionVote);
		
		return sugerencia;
	}
}