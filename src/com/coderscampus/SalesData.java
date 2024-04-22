package com.coderscampus;

import java.time.LocalDate;

public class SalesData {

	private LocalDate date;
	private Integer sales;
	
	public SalesData(LocalDate date, Integer sales) {
		
		this.date = date;
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
