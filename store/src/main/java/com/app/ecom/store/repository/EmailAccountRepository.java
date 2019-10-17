package com.app.ecom.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecom.store.model.EmailAccount;

@Repository
public interface EmailAccountRepository extends JpaRepository<EmailAccount, Long> {

}
