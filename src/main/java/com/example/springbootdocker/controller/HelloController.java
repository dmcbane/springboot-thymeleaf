package com.example.springbootdocker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class HelloController {

	@GetMapping({"/", "/hello", "/hello/"})
	public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		model.addAttribute("name", "Only RequestParam");
		model.addAttribute("pathName", "Not used");
		model.addAttribute("reqName", name);
		return "hello";
	}

	@GetMapping({"/greetings", "/greetings/{pathName}"})
	public String greetings(Model model, @PathVariable(required = false) String pathName) {
		model.addAttribute("name", "Only PathVariable");
		model.addAttribute("pathName", Objects.requireNonNullElse(pathName, "Not set"));
		model.addAttribute("reqName", "Not used");
		return "hello";
	}

	@GetMapping({"/welcome{pathName}", "/welcome/{pathName}"})
	public String welcome(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name, @PathVariable(required = false) String pathName) {
		model.addAttribute("name", "PathVariable & RequestParam");
		model.addAttribute("pathName", Objects.requireNonNullElse(pathName, "Not set"));
		model.addAttribute("reqName", name);
		return "hello";
	}

}