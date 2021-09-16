package com.portfolio.done.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.done.appuser.AppUser;

@Repository
public interface TeamDao extends JpaRepository<Team, Long> {

	Optional<Team> findByTitle(String title);
	Optional<Team> findById(Long id);
	@Transactional
    @Modifying
//	@Query("SELECT app_user.id, app_user.first_name, app_user.last_name, app_user.email\r\n" + 
//			"    		FROM app_user \r\n" + 
//			"    		INNER JOIN appuser_team ON app_user.id=appuser_team.appuser_id \r\n" + 
//			"    		WHERE appuser_team.team_id=?1")
	@Query("from AppUser as appuser inner join appuser.teams as teams with teams.id =?1")
	List<AppUser> getAllTeamUsers(Long id);
}
