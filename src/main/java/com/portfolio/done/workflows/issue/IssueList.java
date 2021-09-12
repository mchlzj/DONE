package com.portfolio.done.workflows.issue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.portfolio.done.team.Team;

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
@Entity(name ="Issue")
public class IssueList {

	@SequenceGenerator(
			name = "feature_sequence",
			sequenceName = "feature_sequence",
			allocationSize = 1)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "feature_sequence")
	private long id;
	private String title;
	private String description;
	
	@ManyToOne
    @JoinColumn(name="team_id", nullable=false)
	private Team team;
	
}
