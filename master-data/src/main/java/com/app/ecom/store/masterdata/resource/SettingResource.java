package com.app.ecom.store.masterdata.resource;

import com.app.ecom.store.masterdata.constants.Endpoint;
import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDtos;
import com.app.ecom.store.masterdata.dto.setting.SettingSearchRequest;
import com.app.ecom.store.masterdata.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value = Endpoint.SETTING)
	public ResponseEntity<SettingDtos> getAllSettings(@RequestBody SettingSearchRequest settingSearchRequest) {
		try {
			SettingDtos settingDtos = settingService.getSettings(settingSearchRequest);
			return new ResponseEntity<>(settingDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.SETTING)
	public ResponseEntity<Void> deleteSettings(@RequestBody IdsDto idsDto) {
		try {
			settingService.deleteSettings(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
