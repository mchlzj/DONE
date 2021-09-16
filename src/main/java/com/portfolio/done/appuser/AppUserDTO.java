package com.portfolio.done.appuser;

import java.util.ArrayList;
import java.util.List;

import com.portfolio.done.team.Team;
import com.portfolio.done.team.TeamDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO {
	
    private String email;
    private List<TeamDTO> teams = new ArrayList<>();   

    public AppUserDTO() { 
    }
     
    public AppUserDTO(AppUser appuser) {
         this.email = appuser.getEmail();
         for(Team team : appuser.getTeams()) {
              TeamDTO teamDTO = new TeamDTO();
              teamDTO.setTitle(team.getTitle());
              teams.add(teamDTO);
         }
     }
}
