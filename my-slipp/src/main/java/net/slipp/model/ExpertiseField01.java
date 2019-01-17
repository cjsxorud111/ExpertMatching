package net.slipp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExpertiseField01 {

	@Id
	@GeneratedValue
	private Long expertiseField01Num;

	@Column(nullable = false)
	private String expertiseField01Name;

	@ManyToOne
	@JoinColumn(name = "expert_num")
	private Expert expert;

	public ExpertiseField01() {
	}

	public ExpertiseField01(Expert expert, String expertiseField01) {
		this.expert = expert;
		this.expertiseField01Name = expertiseField01;
	}

	public Long getExpertiseField01Num() {
		return expertiseField01Num;
	}

	public void setExpertiseField01Num(Long expertiseField01Num) {
		this.expertiseField01Num = expertiseField01Num;
	}

	public String getExpertiseField01Name() {
		return expertiseField01Name;
	}

	public void setExpertiseField01Name(String expertiseField01Name) {
		this.expertiseField01Name = expertiseField01Name;
	}

}
