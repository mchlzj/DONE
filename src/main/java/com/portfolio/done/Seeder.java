package com.portfolio.done;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserRole;
import com.portfolio.done.appuser.AppUserService;


@Component
public class Seeder {
	
	AppUserService appUserService;
	
	@Autowired
	public Seeder(AppUserService appUserService) {
		this.appUserService = appUserService;
		
		//Nutzer zum Testen erstellen und gleichzeitig registrieren und aktivieren
		AppUser adminMeineOrga = new AppUser("Admin", "MeineOrga", "Admin@MeineOrgan.de", "passwort", AppUserRole.ADMIN);
		appUserService.signUpUser(adminMeineOrga);
		appUserService.enableAppUser(adminMeineOrga.getEmail());
		
		AppUser schreibenMeineOrga = new AppUser("Schreiber", "MeineOrga", "Schreiber@MeineOrgan.de", "passwort", AppUserRole.USER);
		appUserService.signUpUser(schreibenMeineOrga);
		appUserService.enableAppUser(schreibenMeineOrga.getEmail());
		
		AppUser lesenMeineOrga = new AppUser("Leser", "MeineOrga", "Leser@MeineOrgan.de", "passwort", AppUserRole.USER);
		appUserService.signUpUser(lesenMeineOrga);
		appUserService.enableAppUser(lesenMeineOrga.getEmail());
		
		AppUser nichtsMeineOrga = new AppUser("Nichts", "MeineOrga", "Nichts@MeineOrgan.de", "passwort", AppUserRole.USER);
		appUserService.signUpUser(nichtsMeineOrga);
		appUserService.enableAppUser(nichtsMeineOrga.getEmail());
		System.out.println("ACHTUNG-------------------------------------ACHTUNG");
		System.out.println(AppUserRole.ADMIN.name());
		System.out.println(AppUserRole.ADMIN);
		

	}		
}
