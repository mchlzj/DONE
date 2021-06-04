package com.portfolio.done.security.config;


import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.portfolio.done.appuser.AppUserService;
import com.portfolio.done.security.jwt.JwtConfig;
import com.portfolio.done.security.jwt.JwtTokenVerifyFilter;
import com.portfolio.done.security.jwt.JwtUsernameAndPasswordAuthentivationFilter;
import com.portfolio.done.security.roles.AppUserRole;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public final AppUserService appUserService;
	public final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
    		AppUserService appUserService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.appUserService = appUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.and()
			.csrf()
				.disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(new JwtUsernameAndPasswordAuthentivationFilter(authenticationManager(), jwtConfig, secretKey))
			.addFilterAfter(new JwtTokenVerifyFilter(secretKey, jwtConfig), JwtUsernameAndPasswordAuthentivationFilter.class)
			.authorizeRequests()
			.antMatchers("/")
				.permitAll()
			.antMatchers("/api/**")
			.hasRole(AppUserRole.ADMIN.name())
//							.permitAll()
			.anyRequest()
			.authenticated();
//			.and()
//				.formLogin()
//					.loginPage("/login")
//					.permitAll()
//					.defaultSuccessUrl("/courses",true)
//					.passwordParameter("password")
//					.usernameParameter("username")
//			.and()
//			.rememberMe()
//				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//				.key("somethingverysecured")
//				//Bezieht sich auf das name attribute im Form. Muss übereinstimmen
//				.rememberMeParameter("remember-me")
//			.and()
//			//sobald CSRF angeschaltet ist, muss ein POST beim Logout genutzt werden...
//			//generell, bei allem was State ändert
//			//Dokumentation zur Hilfe rufen. Die Methode kann über den Form Tag unter dem Attribute method="POST" geändert werden...
//			.logout()
//				.logoutUrl("/logout")
//				.clearAuthentication(true)
////				.invalidateHttpSession(true)
////				.deleteCookies("JSESSIONID", "remember-me")
//				.logoutSuccessUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(appUserService);
		return provider;
	}
}
