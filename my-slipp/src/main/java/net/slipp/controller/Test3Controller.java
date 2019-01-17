package net.slipp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test3Controller {

	@RequestMapping("/test3")
	public String index(Model model) {
		model.addAttribute("name", "testchat3");
		return "test3";
	}
}