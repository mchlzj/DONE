package com.portfolio.done.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.security.roles.AppUserRole;
import com.portfolio.done.workflows.featureRequest.FeatureRequestList;
import com.portfolio.done.workflows.issue.IssueList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="Team")
public class Team implements Serializable{

	@SequenceGenerator(
			name = "team_sequence",
			sequenceName = "team_sequence",
			allocationSize = 1)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "team_sequence")
	private long id;
	private String title;
	private String description;
	
	@ManyToMany(mappedBy = "teams", fetch = FetchType.LAZY)
	private List<AppUser> teamUsers;
	
//	@OneToMany(mappedBy = "team")
//	private List<com.portfolio.done.workflows.List> lists;
	
	public Team(String title, String description) {
		this.title = title;
		this.description = description;
		this.teamUsers = new ArrayList<AppUser>();
	}
	
}
