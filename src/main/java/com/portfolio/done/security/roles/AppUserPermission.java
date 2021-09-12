package com.portfolio.done.security.roles;

//public enum AppUserPermission {
//	STUDENT_READ("student:read"),
//	STUDENT_WRITE("student:write"),
//	COURSE_READ("course:read"),
//	COURSE_WRITE("course:write");
//	
//	private final String permission;
//	
//	AppUserPermission(String permission) {
//		this.permission = permission;
//	}
//	
//	public String getPermission() {
//		return permission;
//	}
//}

public class AppUserPermission{

	private final String permission;
	
	public AppUserPermission(String team, String rolle) {
		this.permission = team + ":" + rolle;
	}
	
	public String getPermission() {
		return this.permission;
	}
}