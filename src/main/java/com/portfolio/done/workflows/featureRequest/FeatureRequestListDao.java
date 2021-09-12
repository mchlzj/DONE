package com.portfolio.done.workflows.featureRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.done.appuser.AppUser;

@Repository
@Transactional(readOnly = true)
public interface FeatureRequestListDao extends JpaRepository<FeatureRequestList, Long> {

}
