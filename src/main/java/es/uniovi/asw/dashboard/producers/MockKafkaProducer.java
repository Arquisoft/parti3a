package es.uniovi.asw.dashboard.producers;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.User;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.SuggestionRepository;
import es.uniovi.asw.persistence.UserRepository;

/**
 * Created by herminio on 26/12/16.
 */
@ManagedBean
@Transactional
public class MockKafkaProducer {

	private static final Logger logger = Logger.getLogger(MockKafkaProducer.class);
	public static final AtomicInteger counterComentario = new AtomicInteger(0);
	public static final AtomicInteger counterSugerencia = new AtomicInteger(0);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private SuggestionRepository sugerenciaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private JpaContext jpaContext;

	public void sendSugerencia() {
		int counter = MockKafkaProducer.counterSugerencia.incrementAndGet();
		Citizen citizen = new Citizen("Nuevo usuario " + counter, 
				"Nuevo apellido", 
				"nuevo" + counter + "@me.com", 
				new Date(), 
				"Residencia", 
				"Nacionalidad", 
				"00000000A");
		User user = new User("Nuevo usuario " + counter, 
				citizen);
		Suggestion sugerencia = new Suggestion("Nueva sugerencia " + counter, 
				new Category("Nueva categoría " + counter), 
				user);
		
		userRepository.save(user);
		citizenRepository.save(citizen);
		
		sugerencia.setCreationDate(new Date());
		
		sugerenciaRepository.save(sugerencia);
		
		sugerencia = jpaContext.getEntityManagerByManagedType(sugerencia.getClass())
								.merge(sugerencia);
		sendMessageJSON("sugerencias", sugerencia);
	}

	public void sendMessage(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Success on sending message \"" + data + "\" to topic " + topic);
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}
	
	public void sendMessageJSON(String topic, Object data) {
		try {
			sendMessage(topic, new ObjectMapper().writeValueAsString(data));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
