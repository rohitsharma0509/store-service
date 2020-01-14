package com.app.ecom.store.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.helpservice.SupportTicketDto;
import com.app.ecom.store.enums.Priority;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.app.ecom.store.service.SupportTicketService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.SupportTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupportTicketController {
	
	@Autowired
	private SupportTicketService supportTicketService;
	
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
			@RequestParam(required = false, name=FieldNames.CREATED_TS) String ticketCreationDate,
			@PageableDefault(page=1, size=10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.TICKET_NUMBER, ticketNumber);
		params.put(FieldNames.ORDER_NUMBER, orderNumber);
		params.put(FieldNames.STATUS, status);
		params.put(FieldNames.CREATED_TS, ticketCreationDate);
		CustomPage<SupportTicketDto> page = supportTicketService.searchSupportTickets(pageable, params);
		model.addAttribute(FieldNames.STATUSES, SupportTicketStatus.values());
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.SUPPORT_TICKETS, page.getPageNumber()+1, page.getTotalPages(), params));
	    model.addAttribute(FieldNames.PAGE, page);
		return View.SUPPORT_TICKETS;
	}
}
