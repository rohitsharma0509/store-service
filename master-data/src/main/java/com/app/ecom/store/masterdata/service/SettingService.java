package com.app.ecom.store.masterdata.service;

import com.app.ecom.store.masterdata.dto.setting.SettingDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDtos;

public interface SettingService {

	SettingDto addUpdateSetting(SettingDto settingDto);

	SettingDtos getAllSettings();

}
