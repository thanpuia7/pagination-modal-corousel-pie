package com.puipuia.paginationmodals.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	// create a mapping for "/hello"
	
	@GetMapping("/hello")
	public String sayHello() {
		
		
		
		return "helloworld";
	}
	
}
	








