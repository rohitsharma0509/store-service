package com.app.ecom.store.masterdata.service.impl;

import com.app.ecom.store.masterdata.dto.SettingDto;
import com.app.ecom.store.masterdata.dto.SettingDtos;
import com.app.ecom.store.masterdata.mapper.SettingMapper;
import com.app.ecom.store.masterdata.repository.SettingRepository;
import com.app.ecom.store.masterdata.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private SettingMapper settingMapper;
	
	@Autowired
	private SettingRepository settingRepository;

	@Override
	public SettingDto addUpdateSetting(SettingDto settingDto) {
		return settingMapper.settingToSettingDto(settingRepository.save(settingMapper.settingDtoToSetting(settingDto)));
	}

	@Override
	public SettingDtos getAllSettings() {
		return settingMapper.settingsToSettingDtos(settingRepository.findAll());
	}

}
