package net.slipp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExpertiseField02 {

	@Id
	@GeneratedValue
	private Long expertiseField02Num;

	@Column(nullable = false)
	private String expertiseField02Name;

	@ManyToOne
	@JoinColumn(name = "expert_num")
	private Expert expert;

	public ExpertiseField02() {
	}

	public ExpertiseField02(Expert expert, String expertiseField02) {
		this.expert = expert;
		this.expertiseField02Name = expertiseField02;
	}

	public Long getExpertiseField02Num() {
		return expertiseField02Num;
	}

	public void setExpertiseField02Num(Long expertiseField02Num) {
		this.expertiseField02Num = expertiseField02Num;
	}

	public String getExpertiseField02Name() {
		return expertiseField02Name;
	}

	public void setExpertiseField02Name(String expertiseField02Name) {
		this.expertiseField02Name = expertiseField02Name;
	}

}
