package com.portfolio.done.workflows;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.portfolio.done.appuser.AppUser;
import com.portfolio.done.team.Team;
import com.portfolio.done.workflows.featureRequest.FeatureRequestList;
import com.portfolio.done.workflows.issue.IssueList;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="List")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class List {

	@SequenceGenerator(
			name = "list_sequence",
			sequenceName = "list_sequence",
			allocationSize = 1)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "list_sequence")
	private Long id;
	private String name;
	private String description;
	
	@ManyToOne
    @JoinColumn(name="team_id", nullable=false)
	private Team team;
	
	@ManyToMany(mappedBy = "lists")
	private java.util.List<AppUser> users;
	@ManyToMany(mappedBy = "lists")
	private java.util.List<AppUser> submitters;
	@ManyToMany(mappedBy = "lists")
	private java.util.List<AppUser> developers;
	@ManyToMany(mappedBy = "lists")
	private java.util.List<AppUser> managers;
	
	
}
