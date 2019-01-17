package net.slipp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.slipp.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	@Query("SELECT c FROM Chat c WHERE c.chatTitle LIKE %?1%")
	List<Chat> findByPattern(String pattern);
}