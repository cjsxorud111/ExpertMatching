package net.slipp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.slipp.model.InterestField02;

public interface InterestField02Repository extends JpaRepository<InterestField02, Long> {
	/*
	@Transactional
	@Modifying
	@Query("Delete FROM InterestField01 i WHERE i.userNum=?1")
	Void intfClear(Long userNum);
	*/
	@Query("SELECT i.interestField02Num FROM InterestField02 i WHERE i.user.userNum=?1")
	List<Long> findByUserNum(Long userNum);
}