package com.app.ecom.store.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.supportservice.SupportTicketActivityHistoryDto;
import com.app.ecom.store.dto.supportservice.SupportTicketAssignmentStrategyDto;
import com.app.ecom.store.dto.supportservice.SupportTicketDto;
import com.app.ecom.store.enums.Priority;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.app.ecom.store.service.RoleService;
import com.app.ecom.store.service.SupportTicketService;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.SupportTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SupportTicketController {
	
	@Autowired
	private SupportTicketService supportTicketService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SupportTicketValidator supportTicketValidator;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@GetMapping(value = RequestUrls.CREATE_SUPPORT_TICKET)
	public String addProduct(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		SupportTicketDto supportTicketDto;
		if(id != null){
			supportTicketDto = supportTicketService.getSupportTicketById(id);
		}else {
			supportTicketDto = new SupportTicketDto();
		}
		model.addAttribute(FieldNames.PRIORITIES, Priority.values());
		model.addAttribute(FieldNames.SUPPORT_TICKET_DTO, supportTicketDto);
		return View.CREATE_SUPPORT_TICKET;
	}
	
	@PostMapping(value = RequestUrls.SUPPORT_TICKETS)
	public String createSupportTicket(Model model, @ModelAttribute(FieldNames.SUPPORT_TICKET_DTO)  @Valid SupportTicketDto supportTicketDto, BindingResult bindingResult) {
		supportTicketValidator.validate(supportTicketDto, bindingResult);
		if (bindingResult.hasErrors()) {
			return View.CREATE_SUPPORT_TICKET;
		}
		
		SupportTicketDto createdSupportTicketDto = supportTicketService.createSupportTicket(supportTicketDto);
		return "redirect:"+RequestUrls.VIEW_SUPPORT_TICKET + "?"+ FieldNames.ID + "=" +createdSupportTicketDto.getId();
	}
	
	@GetMapping(value = RequestUrls.VIEW_SUPPORT_TICKET)
	public String viewProduct(Model model, @RequestParam(value = FieldNames.ID, required=true) Long id) {
		SupportTicketDto supportTicketDto = supportTicketService.getSupportTicketById(id);
		model.addAttribute(FieldNames.SUPPORT_TICKET_DTO, supportTicketDto);
		return View.VIEW_SUPPORT_TICKET;
	}
	
	@GetMapping(value =RequestUrls.SUPPORT_TICKETS)
	public String getSupportTickets(Model model, 
			@RequestParam(required = false) String ticketNumber,
			@RequestParam(required = false) String orderNumber,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String createdTs,
			@RequestParam(required = false) String unclosedFor,
			@PageableDefault(page=1, size=10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.TICKET_NUMBER, ticketNumber);
		params.put(FieldNames.ORDER_NUMBER, orderNumber);
		params.put(FieldNames.STATUS, status);
		params.put(FieldNames.CREATED_TS, createdTs);
		params.put(FieldNames.UNCLOSED_FOR, unclosedFor);
		CustomPage<SupportTicketDto> page = supportTicketService.searchSupportTickets(pageable, params);
		model.addAttribute(FieldNames.STATUSES, SupportTicketStatus.values());
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.SUPPORT_TICKETS, page.getPageNumber()+1, page.getTotalPages(), params));
	    model.addAttribute(FieldNames.PAGE, page);
		return View.SUPPORT_TICKETS;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.DELETE_SUPPORT_TICKETS)
	public Response deleteSupportTicketById(Model model, @PathVariable(FieldNames.ID) Long id) {
		Response response = supportTicketValidator.validateSupportTicketAssociation(Arrays.asList(id));
		
		if(HttpStatus.OK.value() == response.getCode()) {
			IdsDto idsDto = new IdsDto();
			idsDto.setIds(Arrays.asList(id));
			supportTicketService.deleteSupportTickets(idsDto);
		}
		return response;
	}
	
	@GetMapping(value = RequestUrls.SUPPORT_TICKET_ASSIGNMENT_STRATEGY)
	public String getSupportTicketAssignmentStrategy(Model model) {
		model.addAttribute(FieldNames.USER_DTOS, userService.getAllUsers());
		model.addAttribute(FieldNames.ROLE_DTOS, roleService.getAllRoles());
		model.addAttribute(FieldNames.SUPPORT_TICKET_ASSIGNMENT_STRATEGY_DTO, supportTicketService.getSupportTicketAssignmentStrategy());
		return View.SUPPORT_TICKET_ASSIGNMENT_STRATEGY;
	}
	
	@PostMapping(value = RequestUrls.SUPPORT_TICKET_ASSIGNMENT_STRATEGY)
	public String addEmailAccount(@ModelAttribute(FieldNames.SUPPORT_TICKET_ASSIGNMENT_STRATEGY_DTO) @Valid 
			SupportTicketAssignmentStrategyDto supportTicketAssignmentStrategyDto, BindingResult bindingResult) {
		supportTicketService.configureSupportTicketAssignmentStrategy(supportTicketAssignmentStrategyDto);
		return "redirect:" + RequestUrls.SUPPORT_TICKET_ASSIGNMENT_STRATEGY;
	}
	
	@GetMapping(value = RequestUrls.POST_SUPPORT_TICKET_ACTIVITY)
	public String manageSupportTicketActivity(Model model, @RequestParam(name=FieldNames.TICKET_ID, required=true) Long ticketId) {
		SupportTicketActivityHistoryDto supportTicketActivityHistoryDto = new SupportTicketActivityHistoryDto();
		supportTicketActivityHistoryDto.setTicketId(ticketId);
		model.addAttribute(FieldNames.SUPPORT_TICKET_ACTIVITY_HISTORY_DTO, supportTicketActivityHistoryDto);
		return View.POST_SUPPORT_TICKET_ACTIVITY;
	}
	
	@ResponseBody
	@PostMapping(value = RequestUrls.SUPPORT_TICKET_ACTIVITY_HISTORY)
	public void postSupportTicketActivity(@ModelAttribute(FieldNames.SUPPORT_TICKET_ACTIVITY_HISTORY_DTO) 
		@Valid SupportTicketActivityHistoryDto supportTicketActivityHistoryDto) {
		supportTicketService.postSupportTicketActivity(supportTicketActivityHistoryDto);
	}
}
