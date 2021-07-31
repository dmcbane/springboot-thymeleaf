package com.example.springbootdocker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping({"/", "/hello", "/hello/"})
	public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		model.addAttribute("name", name);
		return "hello";
	}

	@GetMapping({"/{name}", "/hello/{name}"})
	public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String reqName, @PathVariable(required = false) String pathName) {
		model.addAttribute("name", "both");
		model.addAttribute("pathName", pathName);
		model.addAttribute("reqname", reqName);
		return "hello";
	}

}