package com.app.ecom.store.masterdata.mapper;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.masterdata.dto.WhereClause;
import com.app.ecom.store.masterdata.dto.setting.SettingDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDtos;
import com.app.ecom.store.masterdata.dto.setting.SettingSearchRequest;
import com.app.ecom.store.masterdata.enums.OperationType;
import com.app.ecom.store.masterdata.model.Setting;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
		setting.setCreatedBy(settingDto.getCreatedBy());
		setting.setCreatedTs(settingDto.getCreatedTs());
		setting.setLastModifiedBy(settingDto.getLastModifiedBy());
		setting.setLastModifiedTs(settingDto.getLastModifiedTs());
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
		settingDtos.setSettings(listOfSettingDto);
		return settingDtos;
	}

	public SettingDto settingToSettingDto(Setting setting) {
		if (setting == null) {
			return null;
		}
		SettingDto settingDto = new SettingDto();
		settingDto.setId(setting.getId());
		settingDto.setName(setting.getName());
		settingDto.setOldName(setting.getName());
		settingDto.setDescription(setting.getDescription());
		settingDto.setValue(setting.getValue());
		settingDto.setCreatedBy(setting.getCreatedBy());
		settingDto.setCreatedTs(setting.getCreatedTs());
		settingDto.setLastModifiedBy(setting.getLastModifiedBy());
		settingDto.setLastModifiedTs(setting.getLastModifiedTs());
		return settingDto;
	}

	public List<WhereClause> settingSearchRequestToWhereClauses(SettingSearchRequest settingSearchRequest) {
		return getWhereClauses(settingSearchRequest.getId(), settingSearchRequest.getName());
	}
	
	private List<WhereClause> getWhereClauses(Long id, String name) {
		List<WhereClause> whereClauses = new ArrayList<>();
		if (id != null) {
			WhereClause whereClause = new WhereClause("id", String.valueOf(id), OperationType.EQUALS);
			whereClauses.add(whereClause);
		} else if (!StringUtils.isEmpty(name)) {
			WhereClause whereClause = new WhereClause("name", name, OperationType.LIKE);
			whereClauses.add(whereClause);
		}
		return whereClauses;
	}
}
