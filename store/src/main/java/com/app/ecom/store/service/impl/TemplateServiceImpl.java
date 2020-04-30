package com.app.ecom.store.service.impl;

import java.time.ZonedDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.client.TemplateServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.templateservice.TemplateDto;
import com.app.ecom.store.dto.templateservice.TemplateDtos;
import com.app.ecom.store.dto.templateservice.TemplateSearchRequest;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private TemplateServiceClient templateServiceClient;

    @Override
    public TemplateDto getTemplateById(Long id) {
        return templateServiceClient.getTemplate(id);
    }
    
    @Override
    public TemplateDto addTemplate(TemplateDto templateDto) {
    	UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		if(templateDto.getId() != null) {
			TemplateDto existingTemplateDto = getTemplateById(templateDto.getId());
			templateDto.setCreatedBy(existingTemplateDto.getCreatedBy());
			templateDto.setCreatedTs(existingTemplateDto.getCreatedTs());
			templateDto.setLastModifiedBy(userDto.getUsername());
			templateDto.setLastModifiedTs(ZonedDateTime.now());
		} else {
			templateDto.setCreatedBy(userDto.getUsername());
			templateDto.setCreatedTs(ZonedDateTime.now());
			templateDto.setLastModifiedBy(userDto.getUsername());
			templateDto.setLastModifiedTs(ZonedDateTime.now());
		}
		return templateServiceClient.createUpdateTemplate(templateDto);
    }

    @Override
    public CustomPage<TemplateDto> getTemplates(Pageable pageable) {
    	int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = pageable.getPageSize();
		
		TemplateSearchRequest templateSearchRequest = new TemplateSearchRequest();
		templateSearchRequest.setOffset(offset);
		templateSearchRequest.setLimit(limit);
		TemplateDtos templateDtos = templateServiceClient.getTemplates(templateSearchRequest);
		Long totalRecords = templateServiceClient.countTemplates(templateSearchRequest);
		CustomPage<TemplateDto> page = new CustomPage<>();
		page.setContent(templateDtos == null ? null : templateDtos.getTemplates());
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
    }

    @Override
	public void deleteTemplates(IdsDto idsDto) {
    	templateServiceClient.deleteTemplates(idsDto);
	}

	@Override
	public List<TemplateDto> getAllTemplates() {
		TemplateDtos templateDtos = templateServiceClient.getTemplates(new TemplateSearchRequest());
		return templateDtos == null ? null : templateDtos.getTemplates();
	}
}
