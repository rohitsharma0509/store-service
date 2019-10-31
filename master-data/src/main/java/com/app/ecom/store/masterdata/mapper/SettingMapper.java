package com.app.ecom.store.masterdata.mapper;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.masterdata.dto.setting.SettingDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDtos;
import com.app.ecom.store.masterdata.model.Setting;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class SettingMapper {

	public Setting settingDtoToSetting(SettingDto settingDto) {
		if (settingDto == null) {
			return null;
		}
		Setting setting = new Setting();
		setting.setId(settingDto.getId());
		setting.setName(settingDto.getName());
		setting.setDescription(settingDto.getDescription());
		setting.setValue(settingDto.getValue());
		return setting;
	}

	public SettingDtos settingsToSettingDtos(List<Setting> settings) {
		SettingDtos settingDtos = new SettingDtos();

		List<SettingDto> listOfSettingDto = new ArrayList<>();
		if (CollectionUtils.isEmpty(settings)) {
			settingDtos.setSettings(listOfSettingDto);
		} else {
			settings.stream().forEach(setting -> listOfSettingDto.add(settingToSettingDto(setting)));
		}
		return settingDtos;
	}

	public SettingDto settingToSettingDto(Setting setting) {
		if (setting == null) {
			return null;
		}
		SettingDto settingDto = new SettingDto();
		settingDto.setId(setting.getId());
		settingDto.setName(setting.getName());
		settingDto.setDescription(setting.getDescription());
		settingDto.setValue(setting.getValue());
		return settingDto;
	}
}
