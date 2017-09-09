package com.tradeanalyzer.main.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "perfData")
public class PerformanceData {

	private String tradingRange52Week;
	private Double perfYear;
	private Double perfQuarter;
	private Double perfWeek;
	private Double perfYTD;
	private Double perfMonthly;

	public PerformanceData() {

	}

	public String getTradingRange52Week() {
		return tradingRange52Week;
	}

	@XmlElement
	public void setTradingRange52Week(String tradingRange52Week) {
		this.tradingRange52Week = tradingRange52Week;
	}

	public Double getPerfYear() {
		return perfYear;
	}

	@XmlElement
	public void setPerfYear(Double perfYear) {
		this.perfYear = perfYear;
	}

	public Double getPerfQuarter() {
		return perfQuarter;
	}

	@XmlElement
	public void setPerfQuarter(Double perfQuarter) {
		this.perfQuarter = perfQuarter;
	}

	public Double getPerfWeek() {
		return perfWeek;
	}

	@XmlElement
	public void setPerfWeek(Double perfWeek) {
		this.perfWeek = perfWeek;
	}

	public Double getPerfYTD() {
		return perfYTD;
	}

	@XmlElement
	public void setPerfYTD(Double perfYTD) {
		this.perfYTD = perfYTD;
	}

	public Double getPerfMonthly() {
		return perfMonthly;
	}

	@XmlElement
	public void setPerfMonthly(Double perfMonthly) {
		this.perfMonthly = perfMonthly;
	}

}
