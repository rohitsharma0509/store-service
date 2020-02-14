package com.app.ecom.store.support.repository;

import com.app.ecom.store.support.model.SupportTicketStatusChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketStatusChangeHistoryRepository extends JpaRepository<SupportTicketStatusChangeHistory, Long> {

}
