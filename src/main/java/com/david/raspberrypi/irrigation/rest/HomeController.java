package com.david.raspberrypi.irrigation.rest;

import javax.servlet.http.PushBuilder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home(Model model, PushBuilder pushBuilder) {
		if (pushBuilder != null) {
			pushBuilder.path("rest/zone").addHeader("content-type", "application/json").push();
			pushBuilder.path("rest/program").addHeader("content-type", "application/json").push();
			
		}
		return "index.jsp";
	}
}
