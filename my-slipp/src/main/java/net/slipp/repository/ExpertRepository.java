package net.slipp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.slipp.model.Expert;
import net.slipp.model.User;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
	Expert findByExpertId(String expertId);

	// 전문가매칭리스트 첫 화면
	@Query("select e from Expert e where e.expertiseField1 = ?1 and e.expertiseField2 =?2")
	List<Expert> findByinterest(String interestField1, String interestField2);
	
	/*
	// 전문가매칭리스트2 
	@Query("select distinct ef01.expert from ExpertiseField01 ef01, ExpertiseField02 ef02 where ef01.expertiseField01Name in (select if01.interestField01Name from InterestField01 if01 where if01.user=?1) and ef02.expertiseField02Name in (select if02.interestField02Name from InterestField02 if02 where if02.user=?1)")
	List<Expert> findByinterest2(User user);
	*/
	
	/*
	// 전문가매칭리스트2 
	@Query("select distinct ef01.expert from ExpertiseField01 ef01, ExpertiseField02 ef02 where ef01.expertiseField01Name in (select if01.interestField01Name from InterestField01 if01 where if01.user=?1) and ef02.expertiseField02Name in (select if02.interestField02Name from InterestField02 if02 where if02.user=?1)")
	List<Expert> findByinterest2(User user);
	*/
	
	/*
	// 전문가매칭리스트2 
	@Query("select distinct ef01.expert from ExpertiseField01 ef01 where ef01.expertiseField01Name in (select if01.interestField01Name from InterestField01 if01 where if01.user=?1)")
	List<Expert> findByinterest2(User user);
	*/
	
	// 전문가매칭리스트2 
	@Query("select ef01.expert from ExpertiseField01 ef01, InterestField01 if01 WHERE if01.interestField01Name = ef01.expertiseField01Name AND if01.user=?1 GROUP BY ef01.expert ORDER BY COUNT(ef01.expert) DESC")
	List<Expert> findByinterest2(User user);
	
	
	// 매칭순 정렬
	@Query("select e from Expert e order by matched_num")
	List<Expert> findBymatchedNum();
}