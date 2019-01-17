package net.slipp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test2Controller {

	@RequestMapping("/test2")
	public String index(Model model) {
		model.addAttribute("name", "testchat2");
		return "test2";
	}
}