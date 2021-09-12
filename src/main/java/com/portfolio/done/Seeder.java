package com.portfolio.done;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserService;
import com.portfolio.done.security.roles.AppUserRole;


@Component
public class Seeder {
	
	AppUserService appUserService;
	
	@Autowired
	public Seeder(AppUserService appUserService) {
		this.appUserService = appUserService;
		
		//Nutzer zum Testen erstellen und gleichzeitig registrieren und aktivieren
		AppUser AdminAMeineOrgaA = new AppUser("AdminA", "MeineOrgaA", "AdminA@MeineOrgaA.de", "passwort", AppUserRole.ADMIN.getGrantedAuthorities());
		appUserService.signUpUser(AdminAMeineOrgaA);
		appUserService.enableAppUser(AdminAMeineOrgaA.getEmail());
		
		AppUser TesterAMeineOrgaA = new AppUser("TesterA", "MeineOrgaA", "TesterA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(TesterAMeineOrgaA);
		appUserService.enableAppUser(TesterAMeineOrgaA.getEmail());
		
		AppUser EntwicklerAMeineOrgaA = new AppUser("EntwicklerA", "MeineOrgaA", "EntwicklerA@MeineOrgaA.de", "passwort", AppUserRole.ADMINTRAINEE.getGrantedAuthorities());
		appUserService.signUpUser(EntwicklerAMeineOrgaA);
		appUserService.enableAppUser(EntwicklerAMeineOrgaA.getEmail());
		
		AppUser SpezifiziererAMeineOrgaA = new AppUser("SpezifiziererA", "MeineOrgaA", "SpezifiziererA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(SpezifiziererAMeineOrgaA);
		appUserService.enableAppUser(SpezifiziererAMeineOrgaA.getEmail());
		
		AppUser AnforderungsmanagerAMeineOrgaA = new AppUser("AnforderungsmanagerA", "MeineOrgaA", "AnforderungsmanagerA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(AnforderungsmanagerAMeineOrgaA);
		appUserService.enableAppUser(AnforderungsmanagerAMeineOrgaA.getEmail());
		
		AppUser UserAMeineOrgaA = new AppUser("UserA", "MeineOrgaA", "UserA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(UserAMeineOrgaA);
		appUserService.enableAppUser(UserAMeineOrgaA.getEmail());
		
		AppUser HelpdeskAMeineOrgaA = new AppUser("HelpdeskA", "MeineOrgaA", "HelpdeskA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(HelpdeskAMeineOrgaA);
		appUserService.enableAppUser(HelpdeskAMeineOrgaA.getEmail());
		
		AppUser SecondlevelAAMeineOrgaA = new AppUser("SecondlevelA", "MeineOrgaA", "SecondlevelA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(SecondlevelAAMeineOrgaA);
		appUserService.enableAppUser(SecondlevelAAMeineOrgaA.getEmail());
		
		AppUser KeinZugriffAMeineOrgaA = new AppUser("KeinZugriffA", "MeineOrgaA", "KeinZugriffA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(KeinZugriffAMeineOrgaA);
		appUserService.enableAppUser(KeinZugriffAMeineOrgaA.getEmail());
		
		AppUser DisabledAMeineOrgaA = new AppUser("DisabledA", "MeineOrgaA", "DisabledA@MeineOrgaA.de", "passwort", AppUserRole.STUDENT.getGrantedAuthorities());
		appUserService.signUpUser(DisabledAMeineOrgaA);
		
		System.out.println("ACHTUNG-------------------------------------ACHTUNG");
		System.out.println(AppUserRole.ADMIN.name());
		System.out.println(AppUserRole.ADMIN);
		

	}		
}
