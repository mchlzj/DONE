package com.portfolio.done.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.done.registration.token.ConfirmationToken;
import com.portfolio.done.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

	private final static String USER_NOT_FOUND = "user with email %s not found";
	private final AppUserDao appUserDao;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
		
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		return appUserDao.findByEmail(email)
				.orElseThrow(() -> 
				new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}
	
	public String signUpUser(AppUser appUser) {
		boolean userExists = appUserDao
				.findByEmail(appUser.getEmail())
				.isPresent();
		
		if(userExists) {
			throw new IllegalStateException("email already taken");
			//TODO wenn Nutzer bereits angelegt und NICHT bestätigt, erneute Email senden
		}
		
		String encodedPassword = bCryptPasswordEncoder
				.encode(appUser.getPassword());
		
		appUser.setPassword(encodedPassword);
		
		appUserDao.save(appUser);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), //TODO set Minutes from COnfigurationFile
				appUser				
		);
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		//TODO Send EmailService
		
		return token;
	}
	
	public int enableAppUser(String email) {
		return appUserDao.enableAppUser(email);
	}

}
