package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

}