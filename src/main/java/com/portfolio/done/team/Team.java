package com.portfolio.done.team;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.appuser.AppUserRole;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name ="Team")
public class Team {

	@SequenceGenerator(
			name = "teamr_sequence",
			sequenceName = "team_sequence",
			allocationSize = 1)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "team_sequence")
	private long id;
	private String title;
	private String description;
	
	@ManyToMany(mappedBy = "teams")
	private List<AppUser> teamUsers;
	
	public Team(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
}
