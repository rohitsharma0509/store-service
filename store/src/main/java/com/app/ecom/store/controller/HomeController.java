package com.app.ecom.store.controller;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.supportservice.SupportTicketReportByStatus;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.enums.SupportTicketStatus;
import com.app.ecom.store.service.OrderService;
import com.app.ecom.store.service.SupportTicketService;
import com.app.ecom.store.util.ChartGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired
	private SupportTicketService supportTicketService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ChartGenerator chartGenerator;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value = { RequestUrls.DEFAULT, RequestUrls.HOME })
	public String home(Model model) {
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		model.addAttribute(FieldNames.TODAY_ORDER, orderService.getNumberOfOrdersByDateAndUserId(ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS), userDto.getId()));
		SupportTicketReportByStatus supportTicketReportByStatus = supportTicketService.getSupportTicketReportByStatus(userDto.isAdmin() ? null : userDto.getUsername());
		Map<SupportTicketStatus, Long> supportTicketStatusMap = supportTicketReportByStatus.getReport();
		for(SupportTicketStatus supportTicketStatus : SupportTicketStatus.values()) {
			supportTicketStatusMap.put(supportTicketStatus, supportTicketStatusMap.get(supportTicketStatus) == null ? 0 : supportTicketStatusMap.get(supportTicketStatus));
		}
		model.addAttribute("isAdmin", userDto.isAdmin());
		model.addAttribute("supportTicketStatusCounterMap", supportTicketStatusMap);
		model.addAttribute(FieldNames.SUPPORT_TICKET_STATUS_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.getSupportTicketStatusPieChart(supportTicketReportByStatus)));
		
		if(userDto.isAdmin()) {
			model.addAttribute(FieldNames.SALES_COMPARISION_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.getCurrentAndPrevYearSalesComparisionCategoryChart())); 
			model.addAttribute(FieldNames.LAST_5_DAYS_SALES_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.getLast5DaysSalesLineChart()));
			model.addAttribute(FieldNames.MONTHLY_SALES_GRAPH, Base64.getEncoder().encodeToString(chartGenerator.getCurrentYearMonthlySalesBarChart()));	
		}
		return View.HOME;
	}
}
