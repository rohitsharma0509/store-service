package com.app.ecom.store.controller;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.ProfitLossDto;
import com.app.ecom.store.service.ProfitLossService;
import com.app.ecom.store.util.ComboGenerator;
import com.app.ecom.store.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfitLossController {
	@Autowired
	private ProfitLossService profitLossService;

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	private ComboGenerator comboGenerator;

	@GetMapping(value = RequestUrls.PROFIT_LOSS)
	public String redirectToProfitLoss(Model model) {
		return "profitLoss";
	}

	@GetMapping(value = RequestUrls.DAILY_PROFIT_LOSS)
	public String getProfitLoss(Model model, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchDailyProfitLoss(pageable);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("dailyProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.PAGE, page);
		return "dailyProfitLoss";
	}

	@GetMapping(value = RequestUrls.MONTHLY_PROFIT_LOSS)
	public String getMonthlyProfitLoss(Model model, @RequestParam(required = false) Integer month,
			@RequestParam(required = false) Integer year, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchMonthlyProfitLoss(pageable, month, year);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("monthlyProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.MONTHS, comboGenerator.getMonthDropDown(null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return "monthlyProfitLoss";
	}

	@GetMapping(value = RequestUrls.QUARTERLY_PROFIT_LOSS)
	public String getQuarterlyProfitLoss(Model model, @RequestParam(required = false) Integer quarter,
			@RequestParam(required = false) Integer year, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchQuarterlyProfitLoss(pageable, quarter, year);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("quarterProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.QUARTERS, comboGenerator.getQuarterDropDown(null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return "quarterlyProfitLoss";
	}

	@GetMapping(value = RequestUrls.YEARLY_PROFIT_LOSS)
	public String getQuarterlyProfitLoss(Model model, @RequestParam(required = false) Integer year,
			@PageableDefault(page = 1, size = 10) Pageable pageable) {
		CustomPage<ProfitLossDto> page = profitLossService.searchYearlyProfitLoss(pageable, year);
		model.addAttribute(FieldNames.PAGGING,
				commonUtil.getPagging("yearlyProfitLoss", page.getPageNumber() + 1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(null));
		model.addAttribute(FieldNames.PAGE, page);
		return "yearlyProfitLoss";
	}
}
