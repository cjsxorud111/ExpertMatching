package net.slipp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.model.Chat;
import net.slipp.model.Expert;
import net.slipp.model.InterestField01;
import net.slipp.model.InterestField02;
import net.slipp.model.User;
import net.slipp.repository.ExpertRepository;
import net.slipp.repository.InterestField01Repository;
import net.slipp.repository.InterestField02Repository;
import net.slipp.repository.UserRepository;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InterestField01Repository interestField01Repository;

	@Autowired
	private InterestField02Repository interestField02Repository;

	@Autowired
	private ExpertRepository expertRepository;

	// 전문가 매칭하기 - 관심분야옵션페이지
	@GetMapping("/{userNum}/interestGuide")
	public String interestGuide(@PathVariable Long userNum, Model model, HttpSession session) {
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
		return "/user/interestGuide";
	}

	// 관심분야 재설정 후 전문가 매칭
	@GetMapping("/{userNum}/matchingList2")
	public String matchingList2(@PathVariable Long userNum, Model model, HttpSession session, String[] interestField01,
			String[] interestField02) {
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
		return "redirect:/search/matchingList";
	}

	// 전문가매칭
	@GetMapping("/matchingList")
	public String matchingList(Model model, HttpSession session) {
		Object tempUser = session.getAttribute("sessionedUser");
		User sessionedUser = (User) tempUser;

		model.addAttribute("experts", expertRepository.findByinterest2(sessionedUser));
		return "/user/matchingList";
	}

	// 매칭된횟수
	@GetMapping("/matchedNum")
	public String pointmatching(Model model, HttpSession session) {

		// 로그인여부
		Object tempUser = session.getAttribute("sessionedUser");
		if (tempUser == null) {
			return "redirect:/users/loginForm";
		}
		model.addAttribute("experts", expertRepository.findBymatchedNum());
		return "/user/matchingList";
	}

	// 전문가 정보
	@GetMapping("/{expertNum}/expertInfo")
	public String map(@PathVariable Long expertNum, Model model) {
		Expert expert = expertRepository.findById(expertNum).get();
		model.addAttribute("expert", expert);

		List<Chat> chatList = expert.getChats();
		model.addAttribute("chatList", chatList);

		return "/expert/expertInfo";
	}

}