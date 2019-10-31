package com.app.ecom.store.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.persistence.Tuple;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.SearchCriteria;
import com.app.ecom.store.querybuilder.QueryBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChartGenerator {
	
	@Autowired
	private QueryBuilder queryBuilder;
	
	@Autowired
	private CommonUtil commonUtil;

	public byte[] createPieChart(Long alertProducts, Long availableProducts, Long outOfStockProduct) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("Non-Alert", availableProducts);
			dataset.setValue("Alert", alertProducts);
			dataset.setValue("Out Of Stock", outOfStockProduct);

			JFreeChart chart = ChartFactory.createPieChart("Stock Status", dataset, true, true, false);
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
			plot.setBackgroundPaint(Color.WHITE);
			plot.setSectionPaint("Non-Alert", Color.GREEN);
			plot.setSectionPaint("Alert", Color.YELLOW);
			plot.setSectionPaint("Out Of Stock", Color.RED);
			plot.setOutlineVisible(false);

			BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
			ImageIO.write(bufferedImage, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	public byte[] createLineChart() {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			List<String> dates = new LinkedList<>();
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(5L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(4L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(3L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(2L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(1L), Constants.YYYY_MM_DD));
			
			List<SearchCriteria> criterias = new ArrayList<>();
			criterias.add(new SearchCriteria("dates", dates, Constants.IN));
			
			StringBuilder query = new StringBuilder("select date(o.created_ts) date, sum(od.quantity) soldQty, sum(p.per_product_price*od.quantity) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profit from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where date(o.created_ts) in (:dates) group by date(o.created_ts) order by o.created_ts");
			List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), criterias);
			
			Map<String, Double> salesMap = new HashMap<>();
			for(Tuple tuple: tuples){
				salesMap.put(String.valueOf(tuple.get("date")), Double.parseDouble(String.valueOf(tuple.get("amountReceived"))));
			}
			
			DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
			for(String date: dates){
				if(salesMap.containsKey(date)){
					dataset1.addValue(salesMap.get(date), "Sales", date);
				}else {
					dataset1.addValue(0, "Sales", date);
				}
			}
			
			JFreeChart chart = ChartFactory.createLineChart("Last 5 Days Sales", "Dates", "Sales", dataset1, PlotOrientation.VERTICAL, true, true, false);
			Plot plot = chart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setOutlineVisible(false);
			
			BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
			ImageIO.write(bufferedImage, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	public byte[] createBarChart() {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			Map<Integer, Double> salesMap = getSalesMapByYear(Calendar.getInstance().get(Calendar.YEAR));
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			for(Month month : Month.values()){
				if(salesMap.containsKey(month.getValue())){
					dataset2.addValue(salesMap.get(month.getValue()), "Sales", month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
				}else {
					dataset2.addValue(0, "Sales", month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
				}
			}
			JFreeChart chart = ChartFactory.createBarChart("Monthly Sales", "Month", "Sales", dataset2, PlotOrientation.VERTICAL, true, true, false);
			Plot plot = chart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setOutlineVisible(false);
			
			BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
			ImageIO.write(bufferedImage, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}
	
	public Map<Integer, Double> getSalesMapByYear(int year) {
	    List<SearchCriteria> criterias = new ArrayList<>();
        criterias.add(new SearchCriteria("year", year, Constants.EQUALS));
        Map<Integer, Double> salesMap = new HashMap<>();
        List<Tuple> tuples = queryBuilder.getTupleByQuery("select month(o.created_ts) month, sum(od.quantity) soldQty, sum(p.per_product_price*od.quantity) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profit from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where year(o.created_ts)= :year group by month(o.created_ts), year(o.created_ts)", criterias);
        for(Tuple tuple : tuples){ 
            salesMap.put(Integer.parseInt(String.valueOf(tuple.get("month"))), Double.parseDouble(String.valueOf(tuple.get("amountReceived"))));
        }
        return salesMap;
	}
	
	public byte[] createCategoryChart() {
	       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	        try {
	            Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
	            Integer lastYear = currentYear-1;
	            String series1 = currentYear.toString();
	            String series2 = lastYear.toString();
	            
	            Map<Integer, Double> currentYearSalesMap = getSalesMapByYear(currentYear);
	            Map<Integer, Double> lastYearSalesMap = getSalesMapByYear(lastYear);
	            
	            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
	            
	            for(Month month : Month.values()){
	                if(currentYearSalesMap.containsKey(month.getValue())){
	                    dataset1.addValue(currentYearSalesMap.get(month.getValue()), series1, month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
	                }else {
	                    dataset1.addValue(0, series1, month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
	                }
	                
                    if(lastYearSalesMap.containsKey(month.getValue())){
                        dataset1.addValue(lastYearSalesMap.get(month.getValue()), series2, month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
                    }else {
                        dataset1.addValue(0, series2, month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
                    }
	            }
                
	            NumberAxis rangeAxis1 = new NumberAxis("Sales");
	            rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	            LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
	            renderer1.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
	            CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
	            subplot1.setDomainGridlinesVisible(true);

	            NumberAxis rangeAxis2 = new NumberAxis("Sales");
	            rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	            BarRenderer renderer2 = new BarRenderer();
	            renderer2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
	            CategoryPlot subplot2 = new CategoryPlot(dataset1, null, rangeAxis2, renderer2);
	            subplot2.setDomainGridlinesVisible(true);

	            CategoryAxis domainAxis = new CategoryAxis("Year");
	            CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
	            plot.add(subplot1, 2);
	            plot.add(subplot2, 1);
	            
	            JFreeChart chart = new JFreeChart("Sales Comparision between "+lastYear+ " and "+currentYear, new Font("SansSerif", Font.BOLD, 12), plot, true);
	            chart.setBackgroundPaint(Color.WHITE);
	            
	            BufferedImage bufferedImage = chart.createBufferedImage(1200, 500);
	            ImageIO.write(bufferedImage, "png", buffer);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return buffer.toByteArray();
	}
}
