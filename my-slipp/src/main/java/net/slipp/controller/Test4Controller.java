package net.slipp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test4Controller {

	@RequestMapping("/test4")
	public String index(Model model) {
		model.addAttribute("name", "testchat4");
		return "test4";
	}
}