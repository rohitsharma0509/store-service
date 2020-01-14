package com.app.ecom.store.support.repository;

import java.util.List;

import com.app.ecom.store.support.model.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
	SupportTicket findByNumber(String number);

	void deleteByIdIn(List<Long> ids);
}
