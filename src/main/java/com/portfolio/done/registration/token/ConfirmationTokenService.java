package com.portfolio.done.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final ConfirmationTokenDao confirmationTokenDao;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenDao.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
		return confirmationTokenDao.findByToken(token);
	}
	
	public int setConfirmedAt(String token) {
		return confirmationTokenDao.updateConfirmedAt(token, LocalDateTime.now());
	}
}
