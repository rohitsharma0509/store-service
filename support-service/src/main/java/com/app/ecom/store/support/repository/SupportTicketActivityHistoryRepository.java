package com.app.ecom.store.support.repository;

import com.app.ecom.store.support.model.SupportTicketActivityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketActivityHistoryRepository extends JpaRepository<SupportTicketActivityHistory, Long> {

}
