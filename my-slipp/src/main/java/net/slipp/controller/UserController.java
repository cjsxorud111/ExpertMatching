package net.slipp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.model.Chat;
import net.slipp.model.InterestField01;
import net.slipp.model.InterestField02;
import net.slipp.model.User;
import net.slipp.repository.InterestField01Repository;
import net.slipp.repository.InterestField02Repository;
import net.slipp.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterestField01Repository interestField01Repository;

	@Autowired
	private InterestField02Repository interestField02Repository;

	// 회원가입 페이지
	@GetMapping("/joinForm")
	public String form() {
		return "/user/joinForm";
	}

	// 회원가입 신청
	@PostMapping("/join")
	public String create(User user, String[] interestField01, String[] interestField02) {
		userRepository.save(user);

		for (String str1 : interestField01) {
			System.out.println(str1);
			InterestField01 intf01 = new InterestField01(user, str1);
			interestField01Repository.save(intf01);
			user.addInterestField01(intf01);
		}
		for (String str2 : interestField02) {
			System.out.println(str2);
			InterestField02 intf02 = new InterestField02(user, str2);
			interestField02Repository.save(intf02);
			user.addInterestField02(intf02);
		}
		userRepository.save(user);
		System.out.println(user.getInf01().size());
		System.out.println(user.getInf02().size());
		return "redirect:/";
	}

	// 회원목록
	@GetMapping("/memberList")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/memberList";
	}

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}

	// 로그인 확인
	@PostMapping("/login")
	public String login(String userId, String userPassword, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		if (!userPassword.equals(user.getUserPassword())) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		System.out.println("Login Success!");
		session.setAttribute("sessionedUser", user);

		return "redirect:/index";
	}

	// 회원정보 수정페이지 이동
	@GetMapping("/{userNum}/updateForm")
	public String updateform(@PathVariable Long userNum, Model model, HttpSession session) {
		// 로그인여부
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}
		// 외부접속방지
		User sessionedUser = (User) tempUser;
		if (!userNum.equals(sessionedUser.getUserNum())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}

		User user = userRepository.findById(userNum).get();
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	// 회원정보 수정 적용
	@PostMapping("/{userNum}/update")
	public String update(@PathVariable Long userNum, User updatedUser, String[] interestField01,
			String[] interestField02, HttpSession session) {
		// 로그인여부
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}
		// 외부접속방지
		User sessionedUser = (User) tempUser;
		if (!userNum.equals(sessionedUser.getUserNum())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}

		User user = userRepository.findById(userNum).get();
		user.update(updatedUser);

		// 관심분야01 초기화
		user.getInf01().clear();
		// 관심분야02 초기화
		user.getInf02().clear();

		List<Long> intList01 = interestField01Repository.findByUserNum(userNum);
		for (int i = 0; i < intList01.size(); i++) {
			interestField01Repository.deleteById(intList01.get(i));
		}
		List<Long> intList02 = interestField02Repository.findByUserNum(userNum);
		for (int i = 0; i < intList02.size(); i++) {
			interestField02Repository.deleteById(intList02.get(i));
		}

		for (String str1 : interestField01) {
			System.out.println(str1);
			InterestField01 intf01 = new InterestField01(user, str1);
			interestField01Repository.save(intf01);
			user.addInterestField01(intf01);
		}
		for (String str2 : interestField02) {
			System.out.println(str2);
			InterestField02 intf02 = new InterestField02(user, str2);
			interestField02Repository.save(intf02);
			user.addInterestField02(intf02);
		}
		userRepository.save(user);
		System.out.println(user.getInf01().size());
		System.out.println(user.getInf02().size());
		return "redirect:/index";
	}

	// 채팅리스트
	@GetMapping("/{userNum}/chatList")
	public String chatList(@PathVariable Long userNum, HttpSession session, Model model) {
		// 로그인여부
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}
		// 외부접속방지
		User sessionedUser = (User) tempUser;
		if (!userNum.equals(sessionedUser.getUserNum())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}

		User user = userRepository.findById(userNum).get();
		List<Chat> chats = user.getChats();
		model.addAttribute("chats", chats);
		return "/user/userChatList";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}

}
