package com.portfolio.done.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
		
		@GetMapping("login")
		public String getLogin() {

			return "login";
		}

		@GetMapping("courses")
		public String getCourses() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth.getCredentials());
			System.out.println(auth.getName());
			System.out.println(auth.getAuthorities());
			System.out.println(auth.getPrincipal());
			return "courses";
		}
}
