package com.app.ecom.store.template.service.impl;

import java.util.List;
import java.util.Optional;

import com.app.ecom.store.template.dto.IdsDto;
import com.app.ecom.store.template.dto.QueryRequest;
import com.app.ecom.store.template.dto.TemplateDto;
import com.app.ecom.store.template.dto.TemplateDtos;
import com.app.ecom.store.template.dto.TemplateSearchRequest;
import com.app.ecom.store.template.handler.QueryHandler;
import com.app.ecom.store.template.mapper.TemplateMapper;
import com.app.ecom.store.template.model.Template;
import com.app.ecom.store.template.repository.TemplateRepository;
import com.app.ecom.store.template.service.TemplateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class TemplateServiceImpl implements TemplateService {
	
	private static final Logger logger = LogManager.getLogger(TemplateServiceImpl.class);
	
	@Autowired
	private TemplateRepository templateRepository;
	
	@Autowired
	private TemplateMapper templateMapper;
	
	@Autowired
	private QueryHandler<Template> queryHandler;

	@Override
	@Transactional
	public TemplateDto createUpdateTemplate(TemplateDto templateDto) {
		return templateMapper.templateToTemplateDto(templateRepository.save(templateMapper.templateDtoToTemplate(templateDto)));
	}
	
	@Override
	public TemplateDto getTemplateById(Long id) {
		Optional<Template> optionalTemplate = templateRepository.findById(id);
		return optionalTemplate.isPresent() ? templateMapper.templateToTemplateDto(optionalTemplate.get()) : null;
	}

	@Override
	public TemplateDtos getTemplates(TemplateSearchRequest templateSearchRequest) {
		queryHandler.setType(Template.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(templateMapper.templateSearchRequestToWhereClauses(templateSearchRequest));
		queryRequest.setOrderByClauses(templateSearchRequest.getOrderByClauses());
		queryRequest.setOffset(templateSearchRequest.getOffset());
		queryRequest.setLimit(templateSearchRequest.getLimit());
		List<Template> templatees = queryHandler.findByQueryRequest(queryRequest);
		return templateMapper.templatesToTemplateDtos(templatees);
	}

	@Override
	public Long countTemplates(TemplateSearchRequest templateSearchRequest) {
			queryHandler.setType(Template.class);
			QueryRequest queryRequest = new QueryRequest();
			queryRequest.setWhereClauses(templateMapper.templateSearchRequestToWhereClauses(templateSearchRequest));
			return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	@Transactional
	public void deleteTemplates(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			templateRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			templateRepository.deleteById(idsDto.getIds().get(0));
		} else {
			templateRepository.deleteByIdIn(idsDto.getIds());
		}
	}
}
