package com.portfolio.done.registration;



import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class RegistrationRequest {
	private final String firstName;
	private final String lastName;
	private final String password;
	private final String email;
	
}
