package com.app.ecom.store.masterdata.service;

import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDtos;
import com.app.ecom.store.masterdata.dto.setting.SettingSearchRequest;

public interface SettingService {

	SettingDto addUpdateSetting(SettingDto settingDto);

	SettingDtos getSettings(SettingSearchRequest settingSearchRequest);

	void deleteSettings(IdsDto idsDto);

}
