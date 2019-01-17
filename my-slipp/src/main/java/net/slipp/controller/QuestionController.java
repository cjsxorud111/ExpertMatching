package net.slipp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.model.Question;
import net.slipp.model.User;
import net.slipp.repository.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/form")
	public String form() {
		return "/qna/form";
	}

	@PostMapping("")
	public String create(HttpSession session, String title, String contents) {
		User sessionUser = (User) session.getAttribute("sessionedUser");
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
}
