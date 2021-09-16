package com.portfolio.done.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.portfolio.done.security.roles.AppUserPermission;

@Component("MethodSecurityService")
public class MethodSecurityService {
	
	public boolean hasPermissionTeam(Authentication authentication, String team, String role) {
		AppUserPermission appUserPermission = new AppUserPermission(team, role);
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(appUserPermission.getPermission());
		return authentication.getAuthorities().contains(simpleGrantedAuthority);
	}

}
