package com.portfolio.done.team;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserDao;
import com.portfolio.done.util.CredentialUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamService {

	private final TeamDao teamDao;
	private final AppUserDao appUserDao;
	
	public String create(TeamRequest request) {
		Team team = new Team(
				request.getTitle(),
				request.getDescription()
				);
//		team.getTeamUsers().add(appUser);
//		long id = CredentialUtils.getUserId();
//		Optional<AppUser> appUser = appUserDao.findById(id);
//		team.getTeamUsers().add(appUser.get());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getCredentials());
		System.out.println(auth.getName());
		System.out.println(auth.getAuthorities());
		System.out.println(auth.getPrincipal());
		
		team.getTeamUsers().add(appUserDao.findByEmail(auth.getName()).get());
//		CredentialUtils.getUserId();
		teamDao.save(team);
		
		return "Team created!";
	}
	
}
