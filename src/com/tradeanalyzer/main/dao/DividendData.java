package com.tradeanalyzer.main.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "divData")
public class DividendData {

	private String exDivDate;
	private String payDate;
	private Double divYield;
	private Double annualDividend;
	private int yearsPaying;

	public DividendData() {

	}

	public DividendData(String exDivDate, String payDate, Double divYield, Double annualDividend, int yearsPaying) {
		this.exDivDate = exDivDate;
		this.payDate = payDate;
		this.divYield = divYield;
		this.annualDividend = annualDividend;
		this.yearsPaying = yearsPaying;
	}

	public String getExDivDate() {
		return exDivDate;
	}

	@XmlElement
	public void setExDivDate(String exDivDate) {
		this.exDivDate = exDivDate;
	}

	public String getPayDate() {
		return payDate;
	}

	@XmlElement
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public Double getDivYield() {
		return divYield;
	}

	@XmlElement
	public void setDivYield(Double divYield) {
		this.divYield = divYield;
	}

	public Double getAnnualDividend() {
		return annualDividend;
	}

	@XmlElement
	public void setAnnualDividend(Double annualDividend) {
		this.annualDividend = annualDividend;
	}

	public int getYearsPaying() {
		return yearsPaying;
	}

	@XmlElement
	public void setYearsPaying(int yearsPaying) {
		this.yearsPaying = yearsPaying;
	}

	public String toString() {
		return exDivDate.toString() + " | " + payDate.toString() + " | " + divYield.toString() + " | "
				+ annualDividend.toString() + " | " + yearsPaying;
	}

}
