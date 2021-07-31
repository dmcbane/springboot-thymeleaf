package com.example.springbootdocker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping({"/", "/hello", "/hello/"})
	public String hello(Model model, @RequestParam(value="reqName", required=false, defaultValue="World") String reqName) {
		model.addAttribute("name", "Only RequestParam");
		model.addAttribute("reqName", reqName);
		return "hello";
	}

	@GetMapping({"/{pathName}", "/hello/{pathName}"})
	public String hello(Model model, @RequestParam(value="reqName", required=false, defaultValue="World") String reqName, @PathVariable(required = false) String pathName) {
		model.addAttribute("name", "both");
		model.addAttribute("pathName", pathName);
		model.addAttribute("reqName", reqName);
		return "hello";
	}

	@GetMapping({"/greetings/{pathName}"})
	public String greetings(Model model, @PathVariable(required = false) String pathName) {
		model.addAttribute("name", "Only PathVariable");
		if (pathName == null) {
			model.addAttribute("pathName", "null");
		} else {
			model.addAttribute("pathName", pathName);
		}
		return "hello";
	}

}