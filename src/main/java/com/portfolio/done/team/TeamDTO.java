package com.portfolio.done.team;

import java.util.ArrayList;
import java.util.List;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {
	private String title;
    private List<AppUserDTO> appusers = new ArrayList<>();

    public TeamDTO() {
    }

    
    public TeamDTO(Team team) {
    	this.title = team.getTitle();
         for(AppUser appuser : team.getTeamUsers()) {
              AppUserDTO appuserDTO = new AppUserDTO();
              appuserDTO.setEmail(appuser.getEmail());
              appusers.add(appuserDTO);
         }
     }
     
}
