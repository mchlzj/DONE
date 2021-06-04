package com.portfolio.done.security.roles;

import static com.portfolio.done.security.roles.AppUserPermission.COURSE_READ;
import static com.portfolio.done.security.roles.AppUserPermission.COURSE_WRITE;
import static com.portfolio.done.security.roles.AppUserPermission.STUDENT_READ;
import static com.portfolio.done.security.roles.AppUserPermission.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum AppUserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(
			COURSE_READ, 
			COURSE_WRITE, 
			STUDENT_READ, 
			STUDENT_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(
			STUDENT_READ, 
			COURSE_READ));
	
	private final Set<AppUserPermission> permissions;
	
	AppUserRole(Set<AppUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<AppUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
