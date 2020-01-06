package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.client.MasterDataClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.masterdata.SettingDto;
import com.app.ecom.store.dto.masterdata.SettingDtos;
import com.app.ecom.store.dto.masterdata.SettingSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private MasterDataClient masterDataClient;
	
	@Autowired
	private HttpSession httpSession;

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
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
    	if(settingDto.getId() != null) {
    		SettingDto existingSettingDto = getSettingById(settingDto.getId());
    		settingDto.setCreatedBy(existingSettingDto.getCreatedBy());
    		settingDto.setCreatedTs(existingSettingDto.getCreatedTs());
    		settingDto.setLastModifiedBy(userDto.getUsername());
    		settingDto.setLastModifiedTs(ZonedDateTime.now());
		} else {
			settingDto.setCreatedBy(userDto.getUsername());
			settingDto.setCreatedTs(ZonedDateTime.now());
			settingDto.setLastModifiedBy(userDto.getUsername());
			settingDto.setLastModifiedTs(ZonedDateTime.now());
		}
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
