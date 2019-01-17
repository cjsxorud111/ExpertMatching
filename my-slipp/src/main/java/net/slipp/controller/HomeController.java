package net.slipp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/*
	 * @Autowired private QuestionRepository questionRepository;
	 */
	@GetMapping("")
	public String home(Model model) {
		// model.addAttribute("questions", questionRepository.findAll());
		return "main";
	}

	@GetMapping("/interestGuide")
	public String home1() {
		return "interestGuide";
	}
	
	@GetMapping("/index")
	public String home2() {
		return "index";
	}
	@GetMapping("/select01")
	public String home3() {
		return "select01";
	}
}
