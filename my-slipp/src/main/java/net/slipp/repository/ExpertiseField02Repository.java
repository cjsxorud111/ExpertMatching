package net.slipp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.slipp.model.ExpertiseField02;

public interface ExpertiseField02Repository extends JpaRepository<ExpertiseField02, Long> {

	@Query("SELECT e.expertiseField02Num FROM ExpertiseField02 e WHERE e.expert.expertNum=?1")
	List<Long> findByExpertNum(Long expertNum);
}
