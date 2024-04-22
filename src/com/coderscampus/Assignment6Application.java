package com.coderscampus;

import java.util.List;


public class Assignment6Application {

    public static void main(String[] args) {
        FileService fileService = new FileService();

        List<SalesData> model3 = fileService.readSalesData("model3.csv");
        List<SalesData> modelS = fileService.readSalesData("modelS.csv");
        List<SalesData> modelX = fileService.readSalesData("modelX.csv");

        fileService.printYTS(model3, "Model 3");
        SalesData model3Ranking = FileService.analyzeMonths(model3, "Model 3");

        fileService.printYTS(modelS, "Model S");
        SalesData modelSRanking = FileService.analyzeMonths(modelS, "Model S");

        fileService.printYTS(modelX, "Model X");
        SalesData modelYRanking = FileService.analyzeMonths(modelX, "Model X");
    }
}