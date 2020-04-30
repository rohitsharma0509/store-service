package com.app.ecom.store.template.repository;

import java.util.List;

import com.app.ecom.store.template.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
	
	void deleteByIdIn(List<Long> ids);
}