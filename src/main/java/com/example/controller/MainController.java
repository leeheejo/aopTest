package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class MainController {

	@GetMapping("/test1")
	public String test1() {
		return "hello world";
	}

	@GetMapping("/test2")
	public void test2() {
		throw new IllegalArgumentException("testException");
	}
}
