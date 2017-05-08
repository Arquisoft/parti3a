package es.uniovi.asw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}