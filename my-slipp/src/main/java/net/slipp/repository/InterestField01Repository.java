package net.slipp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.slipp.model.InterestField01;

public interface InterestField01Repository extends JpaRepository<InterestField01, Long> {

	@Query("SELECT i.interestField01Num FROM InterestField01 i WHERE i.user.userNum=?1")
	List<Long> findByUserNum(Long userNum);
}