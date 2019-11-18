package com.app.ecom.store.dto.masterdata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class SettingDtos {

	@JsonProperty("settings")
	private List<SettingDto> settingDtos;

	public List<SettingDto> getSettingDtos() {
		return settingDtos;
	}

	public void setSettingDtos(List<SettingDto> settingDtos) {
		this.settingDtos = settingDtos;
	}
}