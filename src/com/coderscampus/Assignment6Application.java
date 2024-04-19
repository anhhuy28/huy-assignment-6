package com.coderscampus;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assignment6Application {

	public static void main(String[] args) {

		FileService fileService = new FileService();
		List<SalesData> model3 = fileService.readSalesData("model3.csv");
		List<SalesData> modelS = fileService.readSalesData("modelS.csv");
		List<SalesData> modelX = fileService.readSalesData("modelX.csv");

		Map<YearMonth, List<SalesData>> model3YearlySales = model3.stream()
				.collect(Collectors.groupingBy(salesData -> YearMonth.parse(salesData.getDate())));

		fileService.printYTS("model3.csv", "Model 3");
		SalesData model3Ranking = FileService.analyzeMonths(model3, "Model 3");

		fileService.printYTS("modelS.csv", "Model S");
		SalesData modelSRanking = FileService.analyzeMonths(modelS, "Model S");

		fileService.printYTS("modelX.csv", "Model X");
		SalesData modelXRanking = FileService.analyzeMonths(modelX, "Model X");

	}

}
