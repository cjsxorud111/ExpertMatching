package net.slipp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.slipp.model.ExpertiseField01;

public interface ExpertiseField01Repository extends JpaRepository<ExpertiseField01, Long> {

	@Query("SELECT e.expertiseField01Num FROM ExpertiseField01 e WHERE e.expert.expertNum=?1")
	List<Long> findByExpertNum(Long expertNum);
}
