package net.slipp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Chat {

	@Id
	@GeneratedValue
	Long chatNum;

	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;

	@ManyToOne
	@JoinColumn(name = "expert_num")
	private Expert expert;

	private String chatTitle;
	private String chatContents;
	private LocalDateTime chatCreateDate;
	private Integer chatPoint;
	private String chatReview;
	private Integer chatGrade;

	public Chat() {
	}

	public Chat(User newUser, Expert expert) {
		this.user = newUser;
		this.expert = expert;
		this.chatTitle = "";
		this.chatContents = "";
		this.chatReview = "";
		this.chatGrade = 0;
	}

	public void evaluateChat(String chatReview, Integer chatGrade) {
		this.chatReview = chatReview;
		this.chatGrade = chatGrade;
	}

	public void saveChat(String chatTitle, String chatContents) {
		this.chatTitle = chatTitle;
		this.chatContents = chatContents;
		this.chatCreateDate = LocalDateTime.now();
	}

	public void chatDate() {
		this.chatCreateDate = LocalDateTime.now();
	}

	public Long getChatNum() {
		return chatNum;
	}

	public void setChatNum(Long chatNum) {
		this.chatNum = chatNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public String getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(String chatTitle) {
		this.chatTitle = chatTitle;
	}

	public String getChatContents() {
		return chatContents;
	}

	public void setChatContents(String chatContents) {
		this.chatContents = chatContents;
	}

	public LocalDateTime getChatCreateDate() {
		return chatCreateDate;
	}

	public void setChatCreateDate(LocalDateTime chatCreateDate) {
		this.chatCreateDate = chatCreateDate;
	}

	public Integer getChatPoint() {
		return chatPoint;
	}

	public void setChatPoint(Integer chatPoint) {
		this.chatPoint = chatPoint;
	}

	public Integer getChatGrade() {
		return chatGrade;
	}

	public void setChatGrade(Integer chatGrade) {
		this.chatGrade = chatGrade;
	}

	public String getChatReview() {
		return chatReview;
	}

	public void setChatReview(String chatReview) {
		this.chatReview = chatReview;
	}

	public String getFormattedCreateDate() {
		if (chatCreateDate == null) {
			return "";
		}
		return chatCreateDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}

	@Override
	public String toString() {
		return "Chat [chatNum=" + chatNum + ", user=" + user + ", expert=" + expert + ", chatTitle=" + chatTitle
				+ ", chatContents=" + chatContents + ", chatCreateDate=" + chatCreateDate + ", chatPoint=" + chatPoint
				+ ", chatReview=" + chatReview + ", chatGrade=" + chatGrade + "]";
	}

}
