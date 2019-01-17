package net.slipp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InterestField01 {

	@Id
	@GeneratedValue
	private Long interestField01Num;

	@Column(nullable = false)
	private String interestField01Name;

	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;

	public InterestField01() {
	}

	public InterestField01(User user, String interestField01) {
		this.user = user;
		this.interestField01Name = interestField01;
	}

	public String getInterestField01_Name() {
		return interestField01Name;
	}

	public void setInterestField01_Name(String interestField01Name) {
		this.interestField01Name = interestField01Name;
	}

	public Long getInterestField01Num() {
		return interestField01Num;
	}

}