package com.app.ecom.store.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.app.ecom.store.client.MasterDataClient;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.SettingDto;
import com.app.ecom.store.dto.masterdata.SettingDtos;
import com.app.ecom.store.dto.masterdata.SettingSearchRequest;
import com.app.ecom.store.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private MasterDataClient masterDataClient;

	@Override
	public SettingDto getSettingById(Long id) {
		SettingSearchRequest settingSearchRequest = new SettingSearchRequest();
		settingSearchRequest.setId(id);
		SettingDtos settingDtos = masterDataClient.getSettings(settingSearchRequest);
		if (null != settingDtos && !CollectionUtils.isEmpty(settingDtos.getSettingDtos())) {
			Optional<SettingDto> optional = settingDtos.getSettingDtos().stream().filter(Objects::nonNull).findFirst();
			return optional.isPresent() ? optional.get() : null;
		} else {
			return null;
		}
	}

	@Override
	public SettingDto addSetting(SettingDto settingDto) {
		return masterDataClient.addUpdateSetting(settingDto);
	}

	@Override
	public List<SettingDto> getAllSettings() {
		SettingDtos settingDtos = masterDataClient.getSettings(new SettingSearchRequest());
		if(null != settingDtos && !CollectionUtils.isEmpty(settingDtos.getSettingDtos())) {
			return settingDtos.getSettingDtos();
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public void deleteSettings(IdsDto idsDto) {
		masterDataClient.deleteSettings(idsDto);
	}
}
