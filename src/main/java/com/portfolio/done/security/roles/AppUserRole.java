package com.portfolio.done.security.roles;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum AppUserRole {
	STUDENT(
			Sets.newHashSet()
			),
	ADMIN(
			Sets.newHashSet(
			new AppUserPermission("course","read"),
			new AppUserPermission("course","write"),
			new AppUserPermission("student","read"),
			new AppUserPermission("student","write")
			)
		),
	ADMINTRAINEE(
			Sets.newHashSet(
			new AppUserPermission("student","read"),
			new AppUserPermission("course","read")
			)
		);
	
	private final Set<AppUserPermission> permissions;
	
	AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<AppUserPermission> getPermissions() {
		return permissions;
	}
	
	public void addPermissions(AppUserPermission appUserPermission) {
		permissions.add(appUserPermission);
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
