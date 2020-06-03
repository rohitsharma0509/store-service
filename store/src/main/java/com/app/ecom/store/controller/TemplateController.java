package com.app.ecom.store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.TemplateType;
import com.app.ecom.store.dto.templateservice.TemplateDto;
import com.app.ecom.store.service.TemplateService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.TemplateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TemplateController {
    
    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private TemplateService templateService;
    
    @Autowired
    private TemplateValidator templateValidator;
    
    @GetMapping(value = RequestUrls.ADD_TEMPLATE)
    public String redirectToAddTemplate(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
        TemplateDto templateDto;
        if(id != null){
            templateDto = templateService.getTemplateById(id);
        }else {
        	templateDto = new TemplateDto();
        }
        model.addAttribute(FieldNames.TEMPLATE_DTO, templateDto);
        List<TemplateType> templateTypes = new ArrayList<>();
        TemplateType templateType = new TemplateType();
        templateType.setId(1L);
        templateType.setType("Password Reset Template");
        templateTypes.add(templateType);
        
        templateType = new TemplateType();
        templateType.setId(2L);
        templateType.setType("Password Changed Template");
        templateTypes.add(templateType);
        
        model.addAttribute("types", templateTypes);
        return View.ADD_TEMPLATE;
    }
    
    @PostMapping(value = RequestUrls.TEMPLATES)
    public String addTemplate(Model model, @Valid TemplateDto templateDto, BindingResult bindingResult) {
    	templateValidator.validate(templateDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return View.ADD_TEMPLATE;
        }
        
        templateService.addTemplate(templateDto);
        return "redirect:"+RequestUrls.TEMPLATES;
    }
    
    @GetMapping(value = RequestUrls.TEMPLATES)
    public String getsTemplates(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
        CustomPage<TemplateDto> page = templateService.getTemplates(pageable);
        model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.TEMPLATES, page.getPageNumber()+1, page.getTotalPages(), null));
        model.addAttribute(FieldNames.PAGE, page);
        return View.TEMPLATES;
    }
    
    @ResponseBody
	@PostMapping(value = RequestUrls.DELETE_TEMPLATE)
	public Response deleteTemplateById(Model model, @PathVariable(FieldNames.ID) Long id) {
		Response response = templateValidator.validateTemplateAssociation(Arrays.asList(id));
		if(HttpStatus.OK.value() == response.getCode()) {
			IdsDto idsDto = new IdsDto();
			idsDto.setIds(Arrays.asList(id));
			templateService.deleteTemplates(idsDto);
		}
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_BULK_TEMPLATES)
	public Response deleteTemplates(@RequestBody IdsDto idsDto) {
		Response response = templateValidator.validateTemplateAssociation(idsDto.getIds());
		if(HttpStatus.OK.value() == response.getCode()) {
			templateService.deleteTemplates(idsDto);
		}
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_ALL_TEMPLATES)
	public Response deleteAllTemplates() {
		Response response = templateValidator.validateTemplateAssociation(null);
		if(HttpStatus.OK.value() == response.getCode()) {
			templateService.deleteTemplates(null);
		}
		return response;
	}
}