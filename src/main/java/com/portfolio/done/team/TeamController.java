package com.portfolio.done.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.done.appuser.AppUser;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path ="api/v1/team")
@AllArgsConstructor
public class TeamController {
	
	@Autowired
	private final TeamService teamService;
	
//	@CrossOrigin(origins = "http://localhost:3000")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PostMapping
	public String create(@RequestBody TeamRequest request) {
		return teamService.create(request);
//		return "Team works";
	}

//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public String get() {
//		return teamService.create(request);
		System.out.println("...na toll");
		return "Team works";
	}
}
