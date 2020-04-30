package com.app.ecom.store.template.resource;

import com.app.ecom.store.template.constants.Endpoint;
import com.app.ecom.store.template.dto.IdsDto;
import com.app.ecom.store.template.dto.TemplateDto;
import com.app.ecom.store.template.dto.TemplateDtos;
import com.app.ecom.store.template.dto.TemplateSearchRequest;
import com.app.ecom.store.template.service.TemplateService;
import com.app.ecom.store.template.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateResource {
	
	private static final Logger logger = LogManager.getLogger(TemplateResource.class);
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PutMapping(value = Endpoint.TEMPLATE)
	public ResponseEntity<TemplateDto> createUpdateTemplate(@RequestBody TemplateDto templateDto) {
		try {
			TemplateDto createdTemplateDto = templateService.createUpdateTemplate(templateDto);
			return new ResponseEntity<>(createdTemplateDto, templateDto.getId() == null ? HttpStatus.CREATED : HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while adding template: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = Endpoint.TEMPLATE_WITH_ID)
	public ResponseEntity<TemplateDto> getTemplateById(@PathVariable Long id) {
		try {
			TemplateDto templateDto = templateService.getTemplateById(id);
			return new ResponseEntity<>(templateDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting template: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = Endpoint.TEMPLATE)
	public ResponseEntity<TemplateDtos> getTemplates(@RequestBody TemplateSearchRequest templateSearchRequest) {
		try {
			TemplateDtos templateDtos = templateService.getTemplates(templateSearchRequest);
			return new ResponseEntity<>(templateDtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while getting template: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = Endpoint.COUNT_TEMPLATE)
	public ResponseEntity<Long> countTemplates(@RequestBody TemplateSearchRequest templateSearchRequest) {
		try {
			Long noOfTemplatees = templateService.countTemplates(templateSearchRequest);
			return new ResponseEntity<>(noOfTemplatees, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while counting template: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = Endpoint.TEMPLATE)
	public ResponseEntity<Void> deleteTemplates(@RequestBody IdsDto idsDto) {
		try {
			templateService.deleteTemplates(idsDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while deleting template: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}