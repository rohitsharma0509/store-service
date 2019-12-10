package com.app.ecom.store.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.masterdata.FieldType;
import com.app.ecom.store.dto.masterdata.SettingDto;
import com.app.ecom.store.service.SettingService;
import com.app.ecom.store.validator.SettingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingController {
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private SettingValidator settingValidator;

    @GetMapping(value = RequestUrls.SETTINGS)
    public String redirectToSettings(Model model) {
    	List<SettingDto> settingDtos = settingService.getAllSettings();
    	CustomPage<SettingDto> page = new CustomPage<>();
    	page.setContent(settingDtos);
	    model.addAttribute(FieldNames.PAGE, page);
	    List<FieldType> fieldTypes = new LinkedList<>();
	    fieldTypes.add(new FieldType("checkbox", "Checkbox"));
	    fieldTypes.add(new FieldType("dropdown", "Dropdown"));
	    fieldTypes.add(new FieldType("number", "Number"));
	    fieldTypes.add(new FieldType("radio", "Radio"));
	    fieldTypes.add(new FieldType("text", "Text"));
	    fieldTypes.add(new FieldType("textarea", "Text Area"));
	    model.addAttribute("fieldTypes", fieldTypes);
        return View.SETTINGS;
    }
    
    @GetMapping(value = RequestUrls.ADD_SETTING)
	public String addSetting(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		SettingDto settingDto;
		if(id != null){
			settingDto = settingService.getSettingById(id);
		}else {
			settingDto = new SettingDto();
		}
		model.addAttribute(FieldNames.SETTING_DTO, settingDto);
		return View.ADD_SETTING;
	}
    
    @PostMapping(value = RequestUrls.SETTINGS)
	public String addCategory(Model model, @Valid SettingDto settingDto, BindingResult bindingResult) {
		settingValidator.validate(settingDto, bindingResult);
		if (bindingResult.hasErrors()) {
			return View.ADD_SETTING;
		}
		
		settingService.addSetting(settingDto);
		return "redirect:"+RequestUrls.SETTINGS;
	}
    
    @ResponseBody
	@PostMapping(value = RequestUrls.DELETE_SETTING)
	public Response deleteSetting(Model model, @PathVariable(FieldNames.ID) Long id) {
		Response response = settingValidator.validateSettingAssociation(Arrays.asList(id));

		if(HttpStatus.OK.value() == response.getCode()) {
			IdsDto idsDto = new IdsDto();
			idsDto.setIds(Arrays.asList(id));
			settingService.deleteSettings(idsDto);
		}
		return response;
	}
}
