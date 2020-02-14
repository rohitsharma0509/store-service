package com.app.ecom.store.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketAssignmentStrategyRepository extends JpaRepository<SupportTicketAssignmentStrategy, Long> {

}
