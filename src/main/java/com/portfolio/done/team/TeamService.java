package com.portfolio.done.team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserDao;
import com.portfolio.done.security.jwt.JwtConfig;
import com.portfolio.done.security.roles.AppUserPermission;
import com.portfolio.done.util.CredentialUtils;
import java.time.LocalDate;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamService {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
	private final TeamDao teamDao;
	private final AppUserDao appUserDao;
	
	public String create(TeamRequest request) {
		Team team = new Team(
				request.getTitle(),
				request.getDescription()
				);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		
		String email = auth.getName();
		System.out.println(email);
		
		AppUser appUser = appUserDao.findByEmail(email).get();
		System.out.println(appUser);
		
		team.getTeamUsers().add(appUser);
		teamDao.save(team);
		appUser.getTeams().add(team);
		appUserDao.save(appUser);
		//
		System.out.println(auth.getAuthorities());
		AppUserPermission appUserPermission = new AppUserPermission(request.getTitle(),"admin");
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(appUserPermission.getPermission());
		
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserPermission.getPermission());
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		updatedAuthorities.add(authority);
		updatedAuthorities.addAll(oldAuthorities);
		
		SecurityContextHolder.getContext().setAuthentication(
		        new UsernamePasswordAuthenticationToken(
		                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
		                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
		                updatedAuthorities)
		);

		appUserDao.save(appUser);
        String token = Jwts.builder()
                .setSubject(SecurityContextHolder.getContext().getAuthentication().getName())
                .claim("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        System.out.println(token);
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		//TODO Saving Token to User!

		
		return "Team created!";
	}
	
	public Team findById(Long id) {
		return teamDao.findById(id).get();
	}
	public Team findByTitle(String title) {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
		return teamDao.findByTitle(title).get();
	}
	
	public List<AppUser> getAllTeamUsers(Long id) {
		return teamDao.getAllTeamUsers(id);
	}
}
