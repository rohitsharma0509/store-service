package com.app.ecom.store.masterdata.resource;

import com.app.ecom.store.masterdata.constants.Endpoint;
import com.app.ecom.store.masterdata.dto.SettingDto;
import com.app.ecom.store.masterdata.dto.SettingDtos;
import com.app.ecom.store.masterdata.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingResource {
	
	@Autowired
	private SettingService settingService;

	@PutMapping(value = Endpoint.SETTING)
	public ResponseEntity<SettingDto> addUpdateSetting(@RequestBody SettingDto settingDto) {
		try {
			SettingDto createdSettingDto = settingService.addUpdateSetting(settingDto);
			if(settingDto.getId() == null) {
				return new ResponseEntity<>(createdSettingDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(createdSettingDto, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = Endpoint.SETTING)
	public ResponseEntity<SettingDtos> getAllSettings() {
		try {
			SettingDtos settingDtos = settingService.getAllSettings();
			return new ResponseEntity<>(settingDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
