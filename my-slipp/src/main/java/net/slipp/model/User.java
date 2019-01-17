package net.slipp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long userNum;

	@Column(nullable = false, unique = true)
	private String userId;

	private String userPassword;
	private String userName;
	private String userEmail;

	private String interestField1;
	private String interestField2;

	private String posX;
	private String posY;

	private Integer availablePoint;

	@OneToMany(mappedBy = "user")
	private List<Chat> chats = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<InterestField01> inf01 = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	private List<InterestField02> inf02 = new ArrayList<>();

	public void addInterestField01(InterestField01 interestField01) {
		inf01.add(interestField01);
	}

	public void addInterestField02(InterestField02 interestField02) {
		inf02.add(interestField02);
	}

	public List<InterestField01> getInf01() {
		return inf01;
	}

	public List<InterestField02> getInf02() {
		return inf02;
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

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getInterestField1() {
		return interestField1;
	}

	public void setInterestField1(String interestField1) {
		this.interestField1 = interestField1;
	}

	public String getInterestField2() {
		return interestField2;
	}

	public void setInterestField2(String interestField2) {
		this.interestField2 = interestField2;
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

	public void update(User newUser) {
		this.userPassword = newUser.userPassword;
		this.userName = newUser.userName;
		this.userEmail = newUser.userEmail;
		this.posX = newUser.posX;
		this.posY = newUser.posY;
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", userId=" + userId + ", userPassword=" + userPassword + ", userName="
				+ userName + ", userEmail=" + userEmail + ", interestField1=" + interestField1 + ", interestField2="
				+ interestField2 + ", posX=" + posX + ", posY=" + posY + ", availablePoint=" + availablePoint
				+ ", chats=" + chats + "]";
	}

}
