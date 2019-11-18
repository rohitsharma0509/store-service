package com.app.ecom.store.service;

import java.util.List;

import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.SettingDto;

public interface SettingService {

	SettingDto getSettingById(Long id);

	SettingDto addSetting(SettingDto settingDto);

	List<SettingDto> getAllSettings();

	void deleteSettings(IdsDto idsDto);
}
