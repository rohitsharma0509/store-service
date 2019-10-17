package com.app.ecom.store.masterdata.service;

import com.app.ecom.store.masterdata.dto.SettingDto;
import com.app.ecom.store.masterdata.dto.SettingDtos;

public interface SettingService {

	SettingDto addUpdateSetting(SettingDto settingDto);

	SettingDtos getAllSettings();

}
