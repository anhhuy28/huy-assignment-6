package com.coderscampus;

import java.time.LocalDate;
import java.time.YearMonth;

public class SalesData {

	private LocalDate date;
	private Integer sales;
	
	public SalesData(LocalDate yearMonth, Integer sales) {
		
		this.date = yearMonth;
		this.sales = sales;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "SalesData [date=" + date + ", sales=" + sales + "]";
	}
	
	
	
}
