package com.portfolio.done.appuser;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppUserDaoTests {

	@Autowired
	private AppUserDao appUserDao;
	static AppUser appUserMustermann;
	static Optional<AppUser> appUser;

	
	@BeforeAll
	static void setup() {
		appUserMustermann = new AppUser(
				"Max",
				"Mustermann",
				"Mustermann@gmail.com",
				"supersicher",
				AppUserRole.USER
				);
		appUser = Optional.of(appUserMustermann);
	}
	
	@Test
	void findByEmail() {
		appUserDao.save(appUserMustermann);
		Optional<AppUser> expectedUser = appUserDao.findByEmail("Mustermann@gmail.com");
		assertThat(appUser).isEqualTo(expectedUser);
	}
}
