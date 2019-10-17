package com.app.ecom.store.repository;

import java.util.List;

import com.app.ecom.store.model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
	void deleteByIdIn(List<Long> ids);

	EmailTemplate findBySubject(String subject);
}
