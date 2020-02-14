package com.app.ecom.store.support.repository;

import java.util.List;

import com.app.ecom.store.support.model.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
	
	List<SupportTicket> findByAssignedToAndCreatedByNotAndStatusNot(String assignedTo, String createdBy, String status);
	
	Long countByAssignedToAndCreatedByNotAndStatusNot(String assignedTo, String createdBy, String status);
	
	SupportTicket findByNumber(String number);

	void deleteByIdIn(List<Long> ids);
	
	@Query(value = "update support_tickets set status=:status where ticket_id=:ticketId", nativeQuery = true)
	void updateSupportTicketStatus(Long ticketId, String status);

	@Query(value = "update support_tickets set priority=:priority where ticket_id=:ticketId", nativeQuery = true)
	void updateSupportTicketPriority(Long ticketId, String priority);

	@Modifying
	@Query(value = "update support_tickets set status=:status, assigned_to=:username where ticket_id=:ticketId", nativeQuery = true)
	void updateSupportTicketStatusAndAssignedTo(@Param("status") String status, @Param("username") String assignedTo, @Param("ticketId") Long ticketId);
}
