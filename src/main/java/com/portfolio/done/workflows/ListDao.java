package com.portfolio.done.workflows;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.done.workflows.issue.IssueList;

public interface ListDao extends JpaRepository<List, Long>{
//TODO von IssueList und FeatureRequestList erweitern lassen.
}
