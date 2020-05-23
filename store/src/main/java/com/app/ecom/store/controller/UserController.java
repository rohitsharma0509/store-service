package com.app.ecom.store.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.service.RoleService;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.UserValidator;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private Environment environment;

	@GetMapping(value = RequestUrls.USERS)
	public String getUsers(Model model, @PageableDefault(page=1, size=10) Pageable pageable, @RequestParam(required = false) String name,
			@RequestParam(required = false) String email, String mobile) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.NAME, name);
		params.put(FieldNames.EMAIL, email);
		params.put(FieldNames.MOBILE, mobile);
		
		CustomPage<UserDto> page = userService.getUsers(pageable, params);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.USERS, page.getPageNumber()+1, page.getTotalPages(), params));
		model.addAttribute(FieldNames.PAGE, page);
		return View.USERS;
	}
	
	@GetMapping(value = RequestUrls.VIEW_USER)
	public String viewUser(Model model, @RequestParam(name = FieldNames.ID, required = true) Long id) {
		UserDto userDto = userService.findUserById(id);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.VIEW_USER;
	}
	
	@GetMapping(value = RequestUrls.ACCOUNT_SETTING)
	public String getAccountSetting(Model model, @RequestParam(name = FieldNames.ID, required = true) Long id) {
		UserDto userDto = userService.findUserById(id);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.ACCOUNT_SETTING;
	}
	
	@GetMapping(value = RequestUrls.PERSONAL_INFORMATION)
	public String getPersonalInformation(Model model, @RequestParam(name = FieldNames.ID, required = true) Long id) {
		UserDto userDto = userService.findUserById(id);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.PERSONAL_INFORMATION;
	}
	
	@GetMapping(value = RequestUrls.EDIT_USER)
	public String editUser(Model model, @RequestParam(name = FieldNames.ID, required = true) Long id) {
		UserDto userDto = userService.findUserById(id);
		java.util.Map<String, String> languages = new java.util.HashMap<>();
		languages.put("en", "English");
		languages.put("hi", "Hindi");
		languages.put("fr", "French");
		model.addAttribute("languages", languages);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.EDIT_USER;
	}
	
	@GetMapping(value = RequestUrls.USER_WITH_ID)
	public String redirectToModifyUserRoles(Model model, @PathVariable(name = FieldNames.ID) Long userId) {
		UserDto userDto = userService.findUserById(userId);
		List<Long> userRoleIds = new ArrayList<>();
		if(!CollectionUtils.isEmpty(userDto.getRoles())) {
			userRoleIds = userDto.getRoles().stream().map(RoleDto::getId).collect(Collectors.toList());
		}
		IdsDto idsDto = new IdsDto();
		idsDto.setIds(userRoleIds);
		
		model.addAttribute(FieldNames.USER_ID, userId);
		model.addAttribute(FieldNames.ROLE_DTOS, roleService.getAllRoles());
		model.addAttribute(FieldNames.IDS_DTO, idsDto);
		return View.MODIFY_USER_ROLES;
	}
	
	/*@PostMapping(value = RequestUrls.USER_WITH_ID)
	public String modifyUserRole(Model model, @PathVariable(FieldNames.ID) Long id, @ModelAttribute(FieldNames.IDS_DTO) IdsDto idsDto) {
		Response response = userValidator.validateRoleIds(idsDto);
		if(HttpStatus.OK.value() == response.getCode()) {
			boolean isSuccess = userService.modifyUserRoles(id, idsDto);
			if(isSuccess) {
				model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000030.getCode()));
			} else {
				model.addAttribute(FieldNames.ERROR, environment.getProperty(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));
			}
		} else {
			model.addAttribute(FieldNames.ERROR, response.getDescription());
		}
		UserDto userDto = userService.findUserById(id);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.VIEW_USER;
	}*/
	
	@ResponseBody
	@PostMapping(value = RequestUrls.USER_WITH_ID)
	public Response modifyUserRole(Model model, @PathVariable(FieldNames.ID) Long id, @RequestBody IdsDto idsDto) {
		Response response = userValidator.validateRoleIds(idsDto);
		if(HttpStatus.OK.value() == response.getCode()) {
			boolean isSuccess = userService.modifyUserRoles(id, idsDto);
			if(isSuccess) {
				response.setDescription(environment.getProperty(ErrorCode.ERR000030.getCode()));
			} else {
				response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
				response.setDescription(environment.getProperty(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));
			}
		}
		return response;
	}
	
	@PostMapping(value = RequestUrls.USERS)
	public String editUser(@ModelAttribute(FieldNames.USER_DTO) UserDto userDto) {
		userService.updateUser(userDto);
		return "redirect:"+RequestUrls.USERS;
	}
	
	@Transactional
	@PostMapping(value = RequestUrls.EDIT_USERS)
	public String editLoggedInUser(@ModelAttribute(FieldNames.USER_DTO) UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
		userService.updateUser(userDto);
		userService.updateLocale(request, response, userDto.getLanguage());
		return View.MY_ACCOUNT;
	}
	
	@GetMapping(value = RequestUrls.EDIT_PROFILE)
	public String editLoggedInUserProfile(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDto userDto = userService.findUserByUsername(username);
		java.util.Map<String, String> languages = new java.util.HashMap<>();
		languages.put("en", "English");
		languages.put("hi", "Hindi");
		languages.put("fr", "French");
		model.addAttribute("languages", languages);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.EDIT_PROFILE;
	}
	
	@GetMapping(value = RequestUrls.MY_ACCOUNT)
	public String myAccount(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDto userDto = userService.findUserByUsername(username);
		model.addAttribute(FieldNames.USER_DTO, userDto);
		return View.MY_ACCOUNT;
	}
	
	@GetMapping(value = RequestUrls.CHANGE_PSWRD)
	public String changePassword(Model model, @RequestParam(name = FieldNames.IS_VALIDATION_REQUIRED, required = true) boolean isValidationRequired, @RequestParam(name = FieldNames.PSWRD, required = false) String pswrd, 
			@RequestParam(name = FieldNames.CONFIRM_PSWRD, required = false) String confirmPswrd, @RequestParam(name = FieldNames.ID, required = false) Long userId) {
		if(isValidationRequired) {
			Response response = userValidator.validatePassword(pswrd, confirmPswrd);
			if(HttpStatus.OK.value() == response.getCode()) {
				boolean flag = userService.changePassword(userId, pswrd);
				if(flag) {
					model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000029.getCode()));
				} else {
					model.addAttribute(FieldNames.ERROR, environment.getProperty(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));
				}
			} else {
				model.addAttribute(FieldNames.ERROR, response.getDescription());
			}
		}
		return View.CHANGE_PSWRD;
	}
	
	@GetMapping(value = RequestUrls.FAILURE+"/{code}")
	public String showError(Model model, @PathVariable(FieldNames.CODE) Integer code) {
		model.addAttribute(FieldNames.CODE, code);
		model.addAttribute(FieldNames.MESSAGE, environment.getProperty(String.valueOf(code)));
		return View.FAILURE;
	}
	
	@GetMapping(value = RequestUrls.USER_SEARCH)
	@ResponseBody
	public List<UserDto> searchCustomer(@RequestParam(required = true) String mobileOrName) {
		return userService.getUserByMobileOrName(mobileOrName);
	}
}
