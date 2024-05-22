package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("/")
	public String greet(
			@RequestParam(name="paramName", defaultValue = "Tymeleaf") String name,
			Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
}
