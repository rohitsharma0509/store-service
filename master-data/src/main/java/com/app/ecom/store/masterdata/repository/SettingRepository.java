package com.app.ecom.store.masterdata.repository;

import com.app.ecom.store.masterdata.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

}
