package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                salesDataList.add(new SalesData(yearMonth.toString(), sales));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salesDataList;
    }
  
	public static SalesData analyzeMonths(List<SalesData> salesDataList, String modelName) {
		SalesData bestMonth = salesDataList.stream()
	    		.max(Comparator.comparingInt(SalesData::getSales))
	    		.orElse(null);
	    SalesData worstMonth = salesDataList.stream()
	    		.min(Comparator.comparingInt(SalesData::getSales))
	    		.orElse(null);
		System.out.println("\nThe best month for " + modelName + " was: " + bestMonth.getDate());
        System.out.println("The worst month for " + modelName + " was: " + worstMonth.getDate() + "\n");
		return bestMonth;
	}
	
	public void printYTS(String fileName, String modelName) {
		List<SalesData> salesDataList = readSalesData(fileName);
	    Map<YearMonth, List<SalesData>> modelYearlySales = salesDataList.stream()
	            .collect(Collectors.groupingBy(salesData ->
	                    YearMonth.parse(salesData.getDate())));

	    System.out.println(modelName + " Yearly Sales Report\n-------------------");

	    Map<Integer, Integer> yearlyTotalSales = new HashMap<>();
	    modelYearlySales.forEach((yearMonth, salesDataList1) -> {
	        int totalSales = salesDataList1.stream()
	                .mapToInt(SalesData::getSales)
	                .sum();
	        yearlyTotalSales.put(yearMonth.getYear(), totalSales);
	    });

	    yearlyTotalSales.forEach((year, totalSales) -> System.out.println(year + " -> " + totalSales));
    }
}