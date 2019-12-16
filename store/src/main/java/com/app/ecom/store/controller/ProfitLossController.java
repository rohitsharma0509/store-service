package com.app.ecom.store.controller;

import java.util.HashMap;
import java.util.Map;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
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
		return View.PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.DAILY_PROFIT_LOSS)
	public String getProfitLoss(Model model, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.FROM_DATE, fromDate);
		params.put(FieldNames.TO_DATE, toDate);
		CustomPage<ProfitLossDto> page = profitLossService.searchDailyProfitLoss(pageable);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.DAILY_PROFIT_LOSS, page.getPageNumber() + 1, page.getTotalPages(), params, true, "dailyProfitLossTab"));
		model.addAttribute(FieldNames.PAGE, page);
		return View.DAILY_PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.MONTHLY_PROFIT_LOSS)
	public String getMonthlyProfitLoss(Model model, @RequestParam(required = false) Integer month,
			@RequestParam(required = false) Integer year, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.MONTH, month == null ? null : String.valueOf(month));
		params.put(FieldNames.YEAR, year == null ? null : String.valueOf(year));
		CustomPage<ProfitLossDto> page = profitLossService.searchMonthlyProfitLoss(pageable, month, year);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.MONTHLY_PROFIT_LOSS, page.getPageNumber() + 1, page.getTotalPages(), params, true, "monthlyProfitLossTab"));
		model.addAttribute(FieldNames.MONTHS, comboGenerator.getMonthDropDown(month == null ? null : String.valueOf(month)));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(year == null ? null : String.valueOf(year)));
		model.addAttribute(FieldNames.PAGE, page);
		return View.MONTHLY_PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.QUARTERLY_PROFIT_LOSS)
	public String getQuarterlyProfitLoss(Model model, @RequestParam(required = false) Integer quarter,
			@RequestParam(required = false) Integer year, @PageableDefault(page = 1, size = 10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.QUARTER, quarter == null ? null : String.valueOf(quarter));
		params.put(FieldNames.YEAR, year == null ? null : String.valueOf(year));
		CustomPage<ProfitLossDto> page = profitLossService.searchQuarterlyProfitLoss(pageable, quarter, year);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.QUARTERLY_PROFIT_LOSS, page.getPageNumber() + 1, page.getTotalPages(), params, true, "quarterlyProfitLossTab"));
		model.addAttribute(FieldNames.QUARTERS, comboGenerator.getQuarterDropDown(quarter == null ? null : String.valueOf(quarter)));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(year == null ? null : String.valueOf(year)));
		model.addAttribute(FieldNames.PAGE, page);
		return View.QUARTERLY_PROFIT_LOSS;
	}

	@GetMapping(value = RequestUrls.YEARLY_PROFIT_LOSS)
	public String getQuarterlyProfitLoss(Model model, @RequestParam(required = false) Integer year,
			@PageableDefault(page = 1, size = 10) Pageable pageable) {
		Map<String, String> params = new HashMap<>();
		params.put(FieldNames.YEAR, year == null ? null : String.valueOf(year));
		CustomPage<ProfitLossDto> page = profitLossService.searchYearlyProfitLoss(pageable, year);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.YEARLY_PROFIT_LOSS, page.getPageNumber() + 1, page.getTotalPages(), params, true, "yearlyProfitLossTab"));
		model.addAttribute(FieldNames.YEARS, comboGenerator.getYearDropDown(year == null ? null : String.valueOf(year)));
		model.addAttribute(FieldNames.PAGE, page);
		return View.YEARLY_PROFIT_LOSS;
	}
}
