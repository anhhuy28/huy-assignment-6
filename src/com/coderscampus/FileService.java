package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileService {

	public List<SalesData> readSalesData(String fileName) {
	    List<SalesData> salesDataList = new ArrayList<>();
	    
	    String line;
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	        reader.readLine();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            YearMonth yearMonth = YearMonth.parse(parts[0], formatter);
	            int sales = Integer.parseInt(parts[1]);
	            LocalDate date = yearMonth.atDay(1);
	            salesDataList.add(new SalesData(date, sales));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return salesDataList;
	}

  
	public static SalesData analyzeMonths(List<SalesData> salesDataList, String modelName) {
	    DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy-MM");
	    DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy-MMM");

	    SalesData bestMonth = salesDataList.stream()
	            .max(Comparator.comparingInt(SalesData::getSales))
	            .orElse(null);
	    SalesData worstMonth = salesDataList.stream()
	            .min(Comparator.comparingInt(SalesData::getSales))
	            .orElse(null);

	    LocalDate bestDate = bestMonth.getDate();
	    LocalDate worstDate = worstMonth.getDate();

	    String formattedBestDate = bestDate.format(newFormat);
	    String formattedWorstDate = worstDate.format(newFormat);

	    System.out.println("\nThe best month for " + modelName + " was: " + formattedBestDate);
	    System.out.println("The worst month for " + modelName + " was: " + formattedWorstDate + "\n");

	    return bestMonth;
	}
	
	public void printYTS(List<SalesData> salesDataList, String modelName) {
	    Map<LocalDate, List<SalesData>> modelYearlySales = salesDataList.stream()
	            .collect(Collectors.groupingBy(SalesData::getDate));

	    System.out.println("\n" + modelName + " Yearly Sales Report\n-------------------");

	    Map<Integer, Integer> yearlyTotalSales = new HashMap<>();
	    modelYearlySales.forEach((date, salesDataList1) -> {
	        int totalSales = salesDataList1.stream()
	                .mapToInt(SalesData::getSales)
	                .sum();
	        yearlyTotalSales.put(date.getYear(), totalSales);
	    });

	    yearlyTotalSales.forEach((year, totalSales) -> System.out.println(year + " -> " + totalSales));
	}
}