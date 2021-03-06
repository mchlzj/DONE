package com.portfolio.done.appuser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.done.team.Team;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name ="AppUser")
public class AppUser implements UserDetails {

	// TODO Attribute überprüfen und ggf. überarbeiten
	// TODO Was ist dieser SequenceGenerator?
	
	@SequenceGenerator(
			name = "appuser_sequence",
			sequenceName = "appuser_sequence",
			allocationSize = 1)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "appuser_sequence")
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
//	private final Set<? extends GrantedAuthority> grantedAuthorities;
	private Boolean locked = false;
	private Boolean enabled = false;
	
	@ManyToMany
	@JoinTable(
			  name = "appuser_team", 
			  joinColumns = @JoinColumn(name = "team_id"), 
			  inverseJoinColumns = @JoinColumn(name = "appuser_id"))
	private List<Team> teams;
	
	public AppUser(
			String firstName, 
			String lastName, 
			String email, 
			String password,
			AppUserRole appUserRole
			) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}
	
//	public String getFirstName() {
//		return firstName;
//	}
//	
//	public String getLastName() {
//		return lastName;
//	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Methode füllen
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Methode füllen
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
