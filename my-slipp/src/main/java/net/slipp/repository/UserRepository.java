package net.slipp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.slipp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}