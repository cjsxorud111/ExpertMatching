package net.slipp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.slipp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	
}
