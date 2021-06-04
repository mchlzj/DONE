package com.portfolio.done;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserService;
import com.portfolio.done.security.roles.AppUserRole;

@SpringBootApplication
public class DoneApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DoneApplication.class, args);
		Seeder seeder;
		
	}

}
