package com.app.ecom.store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.enums.RoleType;
import com.app.ecom.store.service.PrivilegeService;
import com.app.ecom.store.service.RoleService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.RoleValidator;
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
public class RolesController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private RoleValidator roleValidator;
	
	@GetMapping(value = RequestUrls.ROLES)
    public String getRoles(Model model, @PageableDefault(page=1, size=10) Pageable pageable, @RequestParam(required=false) String name, @RequestParam(required=false) String roleName) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.NAME, name);
		params.put(FieldNames.ROLE_NAME, roleName);
		CustomPage<RoleDto> page = roleService.getRoles(pageable, params);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.ROLES, page.getPageNumber()+1, page.getTotalPages(), params));
		model.addAttribute(FieldNames.PAGE, page);
        return View.ROLES;
    }
	
	@GetMapping(value = RequestUrls.ADD_ROLE)
	public String addRole(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		RoleDto roleDto;
		if(id != null){
			roleDto = roleService.getRoleById(id);
		}else {
			roleDto = new RoleDto();
		}
		
		model.addAttribute(FieldNames.ROLE_TYPES, RoleType.values());
		model.addAttribute(FieldNames.ROLE_DTO, roleDto);
		return View.ADD_ROLE;
	}
	
	@PostMapping(value = RequestUrls.ROLES)
	public String addRole(Model model, @Valid RoleDto roleDto, BindingResult bindingResult) {
		roleValidator.validate(roleDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute(FieldNames.ROLE_TYPES, RoleType.values());
			return View.ADD_ROLE;
		}
		
		List<Long> rolePrivileges = new ArrayList<>();
		if(roleDto.getId() != null){
			RoleDto roleDtoDb = roleService.getRoleById(roleDto.getId());
			rolePrivileges = roleDtoDb.getPrivilegeDtos().stream().map(PrivilegeDto::getId).collect(Collectors.toList());
		}
		
		List<PrivilegeDto> allPrivileges = privilegeService.getPrivilegesByType(roleDto.getType());
		Set<Long> childPrivilegeIds = new HashSet<>();
		
		for(PrivilegeDto privilegeDto : allPrivileges) {
			privilegeDto.setIsInRole(rolePrivileges.contains(privilegeDto.getId()));
			Set<PrivilegeDto> childPrivileges = allPrivileges.stream().filter(p-> null != p.getParentId() && p.getParentId().equals(privilegeDto.getId())).collect(Collectors.toSet());
			childPrivilegeIds.addAll(childPrivileges.stream().map(PrivilegeDto::getId).collect(Collectors.toSet()));
			privilegeDto.setChildPrivileges(childPrivileges);
		}
		List<PrivilegeDto> privilegesToDisplay = allPrivileges.stream().filter(p->!childPrivilegeIds.contains(p.getId())).collect(Collectors.toList());
		roleDto.setPrivilegeDtos(privilegesToDisplay);
		model.addAttribute(FieldNames.ROLE_DTO, roleDto);
		return View.ROLE_PRIVILEGES;
	}
	
	@PostMapping(value = RequestUrls.ROLE_PRIVILEGES)
	public String addRolePrivileges(Model model, @Valid RoleDto roleDto, BindingResult bindingResult) {
		roleValidator.validate(roleDto, bindingResult);
		if (bindingResult.hasErrors()) {
			return View.ROLE_PRIVILEGES;
		}
		roleService.addRole(roleDto);
		return "redirect:"+RequestUrls.ROLES;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_ROLE)
	public Response deleteRoleById(Model model, @PathVariable(FieldNames.ID) Long id) {
		Response response = roleValidator.validateRoleAssociation(Arrays.asList(id));
		if(HttpStatus.OK.value() == response.getCode()) {
			IdsDto idsDto = new IdsDto();
			idsDto.setIds(Arrays.asList(id));
			roleService.deleteRoles(idsDto);
		}
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_BULK_ROLES)
	public Response deleteRoles(@RequestBody IdsDto idsDto) {
		Response response = roleValidator.validateRoleAssociation(idsDto.getIds());
		if(HttpStatus.OK.value() == response.getCode()) {
			roleService.deleteRoles(idsDto);
		}
		return response;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_ALL_ROLES)
	public Response deleteAllRoles() {
		Response response = roleValidator.validateRoleAssociation(null);
		if(HttpStatus.OK.value() == response.getCode()) {
			roleService.deleteRoles(null);
		}
		return response;
	}
}
