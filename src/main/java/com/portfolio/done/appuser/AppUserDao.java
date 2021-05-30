package com.portfolio.done.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//TODO Was ist Transactional?
@Repository
@Transactional(readOnly = true)
public interface AppUserDao extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser> findByEmail(String email);

	@Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
	int enableAppUser(String email);
}
