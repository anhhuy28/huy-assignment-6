package com.coderscampus;

public class SalesData {

	private String date;
	private Integer sales;
	
	public SalesData(String date, Integer sales) {
		
		this.date = date;
		this.sales = sales;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
