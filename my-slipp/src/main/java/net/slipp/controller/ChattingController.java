package net.slipp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChattingController {

	@RequestMapping("/chatting")
	public String index(Model model) {
		model.addAttribute("name", "chattingchat");
		return "chatting";
	}
}