package net.slipp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Expert {

	@Id
	@GeneratedValue
	private Long expertNum;

	@Column(nullable = false, unique = true)
	private String expertId;

	private String expertPassword;
	private String expertName;
	private String expertEmail;

	private String expertiseField1;
	private String expertiseField2;
	private String expertCareer;
	private String license;
	private String licenseType;
	private String matchedNum;
	private Integer availablePoint;
	private String posX;
	private String posY;
	private String introduction;
	private Long expertGrade;
	private String profile;

	@OneToMany(mappedBy = "expert")
	private List<Chat> chats = new ArrayList<>();

	@OneToMany(mappedBy = "expert")
	private List<ExpertiseField01> exf01 = new ArrayList<>();
	@OneToMany(mappedBy = "expert")
	private List<ExpertiseField02> exf02 = new ArrayList<>();

	public void addExpertiseField01(ExpertiseField01 expertiseField01) {
		exf01.add(expertiseField01);
	}

	public void addExpertiseField02(ExpertiseField02 expertiseField02) {
		exf02.add(expertiseField02);
	}

	public List<ExpertiseField01> getExf01() {
		return exf01;
	}

	public void setExf01(List<ExpertiseField01> exf01) {
		this.exf01 = exf01;
	}

	public List<ExpertiseField02> getExf02() {
		return exf02;
	}

	public void setExf02(List<ExpertiseField02> exf02) {
		this.exf02 = exf02;
	}

	public List<Chat> getChats() {
		return chats;
	}

	public void addChat(Chat chat) {
		chats.add(chat);
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	public Long getExpertNum() {
		return expertNum;
	}

	public String getExpertId() {
		return expertId;
	}

	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}

	public String getExpertPassword() {
		return expertPassword;
	}

	public void setExpertPassword(String expertPassword) {
		this.expertPassword = expertPassword;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getExpertEmail() {
		return expertEmail;
	}

	public void setExpertEmail(String expertEmail) {
		this.expertEmail = expertEmail;
	}

	public String getExpertiseField1() {
		return expertiseField1;
	}

	public void setExpertiseField1(String expertiseField1) {
		this.expertiseField1 = expertiseField1;
	}

	public String getExpertiseField2() {
		return expertiseField2;
	}

	public void setExpertiseField2(String expertiseField2) {
		this.expertiseField2 = expertiseField2;
	}

	public String getExpertCareer() {
		return expertCareer;
	}

	public void setExpertCareer(String expertCareer) {
		this.expertCareer = expertCareer;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getMatchedNum() {
		return matchedNum;
	}

	public void setMatchedNum(String matchedNum) {
		this.matchedNum = matchedNum;
	}

	public Integer getAvailablePoint() {
		return availablePoint;
	}

	public void setAvailablePoint(Integer availablePoint) {
		this.availablePoint = availablePoint;
	}

	public String getPosX() {
		return posX;
	}

	public void setPosX(String posX) {
		this.posX = posX;
	}

	public String getPosY() {
		return posY;
	}

	public void setPosY(String posY) {
		this.posY = posY;
	}

	public String getContent() {
		return introduction;
	}

	public void setContent(String introduction) {
		this.introduction = introduction;
	}

	public void setExpertNum(Long expertNum) {
		this.expertNum = expertNum;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Long getExpertGrade() {
		return expertGrade;
	}

	public void setExpertGrade(Long expertGrade) {
		this.expertGrade = expertGrade;
	}

	public void update(Expert newExpert) {
		this.expertPassword = newExpert.expertPassword;
		this.expertName = newExpert.expertName;
		this.expertEmail = newExpert.expertEmail;
		this.posX = newExpert.posX;
		this.posY = newExpert.posY;
	}

	public void infoUpdate(Expert updatedExpert) {
		this.introduction = updatedExpert.introduction;
		this.profile = updatedExpert.profile;
		this.expertEmail = updatedExpert.expertEmail;
		this.posX = updatedExpert.posX;
		this.posY = updatedExpert.posY;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
