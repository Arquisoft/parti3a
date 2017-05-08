package es.uniovi.asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("es.uniovi.asw.persistence")
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}