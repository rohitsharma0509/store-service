package com.app.ecom.store.masterdata.service.impl;

import java.util.List;
import java.util.Optional;

import com.app.ecom.store.masterdata.dto.IdsDto;
import com.app.ecom.store.masterdata.dto.QueryRequest;
import com.app.ecom.store.masterdata.dto.setting.SettingDto;
import com.app.ecom.store.masterdata.dto.setting.SettingDtos;
import com.app.ecom.store.masterdata.dto.setting.SettingSearchRequest;
import com.app.ecom.store.masterdata.handler.QueryHandler;
import com.app.ecom.store.masterdata.mapper.SettingMapper;
import com.app.ecom.store.masterdata.model.Setting;
import com.app.ecom.store.masterdata.repository.SettingRepository;
import com.app.ecom.store.masterdata.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private SettingMapper settingMapper;
	
	@Autowired
	private SettingRepository settingRepository;
	
	@Autowired
	private QueryHandler<Setting> queryHandler;

	@Override
	@Transactional
	public SettingDto addUpdateSetting(SettingDto settingDto) {
		Setting setting = settingRepository.save(settingMapper.settingDtoToSetting(settingDto));
		return settingMapper.settingToSettingDto(setting);
	}
	
	@Override
	public SettingDto getSettingById(Long id) {
		Optional<Setting> optionalSetting = settingRepository.findById(id);
		return optionalSetting.isPresent() ? settingMapper.settingToSettingDto(optionalSetting.get()) : null;
	}

	@Override
	public SettingDtos getSettings(SettingSearchRequest settingSearchRequest) {
		queryHandler.setType(Setting.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(settingMapper.settingSearchRequestToWhereClauses(settingSearchRequest));
		queryRequest.setOrderByClauses(settingSearchRequest.getOrderByClauses());
		queryRequest.setOffset(settingSearchRequest.getOffset());
		queryRequest.setLimit(settingSearchRequest.getLimit());
		List<Setting> settings = queryHandler.findByQueryRequest(queryRequest);
		return settingMapper.settingsToSettingDtos(settings);
	}

	@Override
	@Transactional
	public void deleteSettings(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			settingRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			settingRepository.deleteById(idsDto.getIds().get(0));
		} else {
			settingRepository.deleteByIdIn(idsDto.getIds());
		}
	}
}
