package com.david.raspberrypi.irrigation.rest;

import javax.servlet.http.PushBuilder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * Maps the root context to return index.jsp.  Attempts to server-push some rest calls,
	 * but I don't know if it works this way.  Either way
	 * @param model
	 * @param pushBuilder
	 * @return
	 */
	@GetMapping
	public String home(Model model, PushBuilder pushBuilder) {
		if (pushBuilder != null) {
			pushBuilder.path("rest/zone").addHeader("content-type", "application/json").push();
			pushBuilder.path("rest/program").addHeader("content-type", "application/json").push();
			
		}
		return "index.jsp";
	}
}
