package net.slipp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InterestField02 {

	@Id
	@GeneratedValue
	private Long interestField02Num;

	@Column(nullable = false)
	private String interestField02Name;

	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;

	public InterestField02() {
	}

	public InterestField02(User user, String interestField02) {
		this.user = user;
		this.interestField02Name = interestField02;
	}

	public String getInterestField02Name() {
		return interestField02Name;
	}

	public void setInterestField02Name(String interestField02Name) {
		this.interestField02Name = interestField02Name;
	}

	public Long getInterestField02Num() {
		return interestField02Num;
	}
}
