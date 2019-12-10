package com.app.ecom.store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.EmailTemplateDto;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.TemplateType;
import com.app.ecom.store.model.EmailTemplate;
import com.app.ecom.store.service.EmailTemplateService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.EmailTemplateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class EmailTemplateController {
    
    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private EmailTemplateService emailTemplateService;
    
    @Autowired
    private EmailTemplateValidator emailTemplateValidator;
    
    @GetMapping(value = RequestUrls.ADD_EMAIL_TEMPLATES)
    public String redirectToAddEmailTemplate(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
        EmailTemplateDto emailTemplateDto;
        if(id != null){
            emailTemplateDto = emailTemplateService.getEmailTemplateById(id);
        }else {
            emailTemplateDto = new EmailTemplateDto();
        }
        model.addAttribute(FieldNames.EMAIL_TEMPLATE_DTO, emailTemplateDto);
        List<TemplateType> templateTypes = new ArrayList<>();
        TemplateType templateType = new TemplateType();
        templateType.setId(1L);
        templateType.setType("User registration mail");
        templateTypes.add(templateType);
        model.addAttribute("types", templateTypes);
        return View.ADD_EMAIL_TEMPLATES;
    }
    
    @PostMapping(value = RequestUrls.EMAIL_TEMPLATES)
    public String addEmailTemplate(Model model, @Valid EmailTemplateDto emailTemplateDto, BindingResult bindingResult) {
        emailTemplateValidator.validate(emailTemplateDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return RequestUrls.ADD_EMAIL_TEMPLATES;
        }
        
        emailTemplateService.addEmailTemplate(emailTemplateDto);
        return "redirect:"+RequestUrls.EMAIL_TEMPLATES;
    }
    
    @GetMapping(value = RequestUrls.EMAIL_TEMPLATES)
    public String getEmailTemplates(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
        Page<EmailTemplate> page = emailTemplateService.getEmailTemplates(pageable);
        model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.EMAIL_TEMPLATES, page.getNumber()+1, page.getTotalPages(), null));
        model.addAttribute(FieldNames.PAGE, page);
        return View.EMAIL_TEMPLATES;
    }
    
    @ResponseBody
	@PostMapping(value = RequestUrls.DELETE_EMAIL_TEMPLATES)
	public Response deleteEmailTemplateById(@PathVariable(FieldNames.ID) Long id) {
    	Response response = emailTemplateValidator.validateTemplateAssociation(Arrays.asList(id));
    	
    	if(HttpStatus.OK.value() == response.getCode()) {
    		emailTemplateService.deleteEmailTemplateById(id);
    	}
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_BULK_EMAIL_TEMPLATES)
	public Response deleteEmailTemplates(@RequestBody IdsDto idsDto) {
		Response response = emailTemplateValidator.validateTemplateAssociation(idsDto.getIds());
    	
    	if(HttpStatus.OK.value() == response.getCode()) {
    		emailTemplateService.deleteEmailTemplates(idsDto.getIds());
    	}
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_ALL_EMAIL_TEMPLATES)
	public Response deleteAllEmailTemplates() {
		Response response = emailTemplateValidator.validateTemplateAssociation(null);
    	
    	if(HttpStatus.OK.value() == response.getCode()) {
    		emailTemplateService.deleteAllEmailTemplates();
    	}
		return response;
	}
}