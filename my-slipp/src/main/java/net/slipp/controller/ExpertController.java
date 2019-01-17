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
import net.slipp.model.Expert;
import net.slipp.model.ExpertiseField01;
import net.slipp.model.ExpertiseField02;
import net.slipp.repository.ExpertRepository;
import net.slipp.repository.ExpertiseField01Repository;
import net.slipp.repository.ExpertiseField02Repository;

@Controller
@RequestMapping("/experts")
public class ExpertController {

	@Autowired
	private ExpertRepository expertRespository;

	@Autowired
	private ExpertiseField01Repository expertiseField01Repository;

	@Autowired
	private ExpertiseField02Repository expertiseField02Repository;

	// 회원가입 페이지
	@GetMapping("/joinForm")
	public String expertJoinForm() {
		return "/expert/expertJoinForm";
	}

	// 회원가입 신청
	@PostMapping("/join")
	public String create(Expert expert, String[] expertiseField01, String[] expertiseField02) {
		expertRespository.save(expert);
		for (String str1 : expertiseField01) {
			System.out.println(str1);
			ExpertiseField01 exfld01 = new ExpertiseField01(expert, str1);
			expertiseField01Repository.save(exfld01);
			expert.addExpertiseField01(exfld01);
		}
		for (String str2 : expertiseField02) {
			System.out.println(str2);
			ExpertiseField02 exfld02 = new ExpertiseField02(expert, str2);
			expertiseField02Repository.save(exfld02);
			expert.addExpertiseField02(exfld02);
		}
		expert.setProfile("http://i1083.photobucket.com/albums/j383/josephgarciadesigns/Research/Business%20Card%20Design%20Research/Real%20Estate%20Realtors%20and%20Brokers/BendBeyondRealEstateTereseKelley700x400.jpg");
		expert.setIntroduction("");
		expertRespository.save(expert);		
		return "redirect:/";
	}

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/expert/expertLoginForm";
	}

	// 로그인 확인
	@PostMapping("/login")
	public String login(String expertId, String expertPassword, HttpSession session) {
		Expert expert = expertRespository.findByExpertId(expertId);
		System.out.println("Expert : " + expert);
		if (expert == null) {
			System.out.println("Login Failure!");
			return "redirect:/experts/loginForm";
		}
		if (!expertPassword.equals(expert.getExpertPassword())) {
			System.out.println("Password Error!");
			return "redirect:/experts/loginForm";
		}
		System.out.println("Login Success!");
		session.setAttribute("sessionedExpert", expert);

		return "redirect:/index";
	}

	// 회원정보 수정페이지 이동
	@GetMapping("/{expertNum}/updateForm")
	public String updateform(@PathVariable Long expertNum, Model model, HttpSession session) {
		// 로그인여부
		Object tempExpert = session.getAttribute("sessionedExpert");
		if (tempExpert == null) {
			return "redirect:/experts/loginForm";
		}
		// 외부접속방지
		Expert sessionedExpert = (Expert) tempExpert;
		if (!expertNum.equals(sessionedExpert.getExpertNum())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}

		Expert expert = expertRespository.findById(expertNum).get();
		model.addAttribute("expert", expert);
		return "/expert/expertUpdateForm";
	}

	// 회원정보 수정 적용
	@PostMapping("/{expertNum}/update")
	public String update(@PathVariable Long expertNum, Expert updatedExpert, String[] expertiseField01,
			String[] expertiseField02, HttpSession session) {
		// 로그인여부
		Object tempExpert = session.getAttribute("sessionedExpert");
		if (tempExpert == null) {
			return "redirect:/experts/loginForm";
		}
		// 외부접속방지
		Expert sessionedExpert = (Expert) tempExpert;
		if (!expertNum.equals(sessionedExpert.getExpertNum())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}
		Expert expert = expertRespository.findById(expertNum).get();
		expert.update(updatedExpert);

		// 관심분야01 초기화
		expert.getExf01().clear();
		// 관심분야02 초기화
		expert.getExf02().clear();

		List<Long> exfList01 = expertiseField01Repository.findByExpertNum(expertNum);
		for (int i = 0; i < exfList01.size(); i++) {
			expertiseField01Repository.deleteById(exfList01.get(i));
		}
		List<Long> exfList02 = expertiseField02Repository.findByExpertNum(expertNum);
		for (int i = 0; i < exfList02.size(); i++) {
			expertiseField02Repository.deleteById(exfList02.get(i));
		}

		for (String str1 : expertiseField01) {
			System.out.println(str1);
			ExpertiseField01 exfld01 = new ExpertiseField01(expert, str1);
			expertiseField01Repository.save(exfld01);
			expert.addExpertiseField01(exfld01);
		}
		for (String str2 : expertiseField02) {
			System.out.println(str2);
			ExpertiseField02 exfld02 = new ExpertiseField02(expert, str2);
			expertiseField02Repository.save(exfld02);
			expert.addExpertiseField02(exfld02);
		}

		expertRespository.save(expert);
		return "redirect:/index";
	}

	// 채팅리스트
	@GetMapping("/{expertNum}/chatList")
	public String chatList(@PathVariable Long expertNum, HttpSession session, Model model) {
		// 로그인여부
		Object tempExpert = session.getAttribute("sessionedExpert");
		if (tempExpert == null) {
			return "redirect:/experts/loginForm";
		}
		// 외부접속방지
		Expert sessionedExpert = (Expert) tempExpert;
		if (!expertNum.equals(sessionedExpert.getExpertNum())) {
			throw new IllegalStateException("접근불가");
		}
		Expert expert = expertRespository.findById(expertNum).get();
		List<Chat> chats = expert.getChats();
		model.addAttribute("chats", chats);
		return "/expert/expertChatList";
	}

	// 자기소개 수정폼
	@GetMapping("/{expertNum}/expertInfoUpdateForm")
	public String expertInfoUpdate(@PathVariable Long expertNum, HttpSession session, Model model) {
		// 로그인여부
		Object tempExpert = session.getAttribute("sessionedExpert");
		if (tempExpert == null) {
			return "redirect:/experts/loginForm";
		}
		// 외부접속방지
		Expert sessionedExpert = (Expert) tempExpert;
		if (!expertNum.equals(sessionedExpert.getExpertNum())) {
			throw new IllegalStateException("접근불가");
		}
		Expert expert = expertRespository.findById(expertNum).get();
		model.addAttribute("expert", expert);
		return "/expert/expertInfoUpdate";
	}

	// 자기소개 수정 적용
	@PostMapping("/{expertNum}/infoUpdate")
	public String infoUpdate(@PathVariable Long expertNum, Expert updatedExpert, HttpSession session) {
		// 로그인여부
		Object tempExpert = session.getAttribute("sessionedExpert");
		if (tempExpert == null) {
			return "redirect:/experts/loginForm";
		}
		// 외부접속방지
		Expert sessionedExpert = (Expert) tempExpert;
		if (!expertNum.equals(sessionedExpert.getExpertNum())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}
		Expert expert = expertRespository.findById(expertNum).get();
		expert.infoUpdate(updatedExpert);
		expertRespository.save(expert);
		return "redirect:/search/{expertNum}/expertInfo";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedExpert");
		return "redirect:/";
	}

}
