package com.portfolio.done.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.done.appuser.AppUser;

import lombok.AllArgsConstructor;

//@CrossOrigin(origins = "http://localhost:3000")

//@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(path ="api/v1/team")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class TeamController {
	
//	@Autowired
	private final TeamService teamService;


//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public String get() {
//		return teamService.create(request);
		System.out.println("...na toll");
		return "Team works";
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public String create(@RequestBody TeamRequest request) {
		System.out.println("post works");
		return teamService.create(request);
		
	}
	
	@GetMapping(path = "{id}")
//	@PreAuthorize("hasAuthority('{id}:read')")
	@PreAuthorize("hasAuthority('student:write')")
	public String getTeamDescription(@PathVariable("id") Long id) {
		Team team = teamService.getTeamById(id);
		System.out.println(team.getDescription());
		return team.getDescription();
	}
}
