package com.portfolio.done.team;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.security.roles.AppUserPermission;

import lombok.AllArgsConstructor;
import lombok.var;

//@CrossOrigin(origins = "http://localhost:3000")

//@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(path ="api/v1/team")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class TeamController {
	
//	@Autowired
	private final TeamService teamService;
	
	//
//	Nur zum Testen
//	!!!!!!!1
//	 @Autowired
//	 private EntityManager entityManager;


//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public String get() {
//		return teamService.create(request);
		System.out.println("...na toll");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "Team works";
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public String create(@RequestBody TeamRequest request) {
		System.out.println("post works");
		return teamService.create(request);
		
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	
	@GetMapping(path = "{title}")
	@PreAuthorize("@MethodSecurityService.hasPermissionTeam(authentication, #title, 'admin')")
	public String getTeamDescription(@PathVariable("title") String title) {
		Team team = teamService.findByTitle(title);
		System.out.println("Access granted");
		return team.getDescription();
	}
	

	@GetMapping(path= "/test")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String test() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("OrgaA:admin"));
		AppUserPermission appUserPermission = new AppUserPermission("OrgaA","admin");
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(appUserPermission.getPermission());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(appUserPermission));
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(simpleGrantedAuthority));
		System.out.println("Test finished");
		return "test finished";
	}
	
//	@GetMapping(path = "{id}/users")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public List<AppUser> getTeamUsers(@PathVariable("id") Long id) {
//		List<AppUser> allTeamUsers = teamService.getAllTeamUsers(id);
//		for(AppUser teamUser: allTeamUsers) {
//			System.out.println(teamUser.getEmail());
//		}
//		return allTeamUsers;
//	}
	@GetMapping(path = "{id}/users")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<TeamDTO> getTeamUsersList(@PathVariable("id") Long id) {
		final var team = teamService.findById(id);
		TeamDTO teamDTO = new TeamDTO(team);
		return ResponseEntity.ok(teamDTO);
	}
}
    
	
	

