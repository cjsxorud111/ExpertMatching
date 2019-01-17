package net.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.model.Chat;
import net.slipp.model.Expert;
import net.slipp.model.User;
import net.slipp.repository.ChatRepository;
import net.slipp.repository.ExpertRepository;
import net.slipp.repository.UserRepository;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExpertRepository expertRepository;

	@Autowired
	private ChatRepository chatRepository;

	// 대화 검색
	@GetMapping("/searchChat")
	public String search(String searchWord, Model model) {
		String pattern = searchWord;
		model.addAttribute("chat", chatRepository.findByPattern(pattern));
		return "/chat/searchChat";
	}

	// 사용자-채팅평가페이지 이동
	@GetMapping("/{expertNum}/chatEvaluationForm")
	public String userSaveForm(@PathVariable Long expertNum, Model model, HttpSession session) {
		// 로그인여부
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}
		User sessionedUser = (User) tempUser;
		User user = userRepository.findById(sessionedUser.getUserNum()).get();
		Expert expert = expertRepository.findById(expertNum).get();
		Chat newChat = new Chat(user, expert);
		chatRepository.save(newChat);
		user.addChat(newChat);
		expert.addChat(newChat);
		model.addAttribute("chat", newChat);
		return "/user/userChatEvaluation";
	}

	// 사용자-채팅평가
	@GetMapping("/{chatNum}/evaluation")
	public String chatEvaluation(@PathVariable Long chatNum, String chatReview, Integer chatGrade) {
		Chat chat = chatRepository.findById(chatNum).get();
		chat.evaluateChat(chatReview, chatGrade);
		chatRepository.save(chat);
		return "redirect:/index";
	}

	// 전문가-채팅저장페이지 이동
	@GetMapping("/{chatNum}/expertSaveForm")
	public String expertSaveForm(@PathVariable Long chatNum, Model model) {

		Chat chat = chatRepository.findById(chatNum).get();
		chat.chatDate();
		chatRepository.save(chat);
		model.addAttribute("chat", chat);
		return "/expert/expertChatSave";
	}

	// 전문가-채팅저장
	@GetMapping("/{chatNum}/save")
	public String chatSave(@PathVariable Long chatNum, String chatTitle, String chatContents) {
		Chat chat = chatRepository.findById(chatNum).get();
		chat.saveChat(chatTitle, chatContents);
		chatRepository.save(chat);
		return "redirect:/index";
	}

	// 채팅내용
	@GetMapping("/{chatNum}/info")
	public String chatInfo(@PathVariable Long chatNum, Model model) {
		Chat chat = chatRepository.findById(chatNum).get();
		model.addAttribute("chat", chat);
		return "/chat/chatInfo";
	}
}
