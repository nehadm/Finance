package com.tradeanalyzer.main.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "stockData")
public class StockData {

	private Double atr;
	private Double stockPrice;
	private Double prevClose;
	private Double shortFloat;
	private String ticker;
	private Double dividend = 0.0;
	private String companyName;
	private String companyBriefing;
	private String industry;
	private String sector;
	private Double targetPrice;
	private String stockExchange;
	private DividendData divData;
	private String earningsCall;
	private String earningsStats;
	private FundamentalData fundaData;
	private PerformanceData perfData;

	public StockData() {
	}

	@JsonCreator
	public StockData(String companyName, String ticker) {
		super();
		this.companyName = companyName;
		this.ticker = ticker;
	}

	@JsonCreator
	public StockData(@JsonProperty Double atr, @JsonProperty Double stockPrice, @JsonProperty Double prevClose,
			@JsonProperty Double shortFloat, @JsonProperty String ticker, Double dividend, String companyName,
			@JsonProperty String companyBriefing, @JsonProperty String industry, @JsonProperty String sector,
			@JsonProperty Double targetPrice, @JsonProperty String stockExchange, @JsonProperty DividendData divData,
			@JsonProperty String earningsCall, @JsonProperty String earningsStats,
			@JsonProperty FundamentalData fundaData, @JsonProperty PerformanceData perfData) {
		super();
		this.atr = atr;
		this.stockPrice = stockPrice;
		this.prevClose = prevClose;
		this.shortFloat = shortFloat;
		this.ticker = ticker;
		this.dividend = dividend;
		this.companyName = companyName;
		this.companyBriefing = companyBriefing;
		this.industry = industry;
		this.sector = sector;
		this.targetPrice = targetPrice;
		this.stockExchange = stockExchange;
		this.divData = divData;
		this.earningsCall = earningsCall;
		this.earningsStats = earningsStats;
		this.fundaData = fundaData;
		this.perfData = perfData;
	}

	public Double getAtr() {
		return atr;
	}

	@XmlElement
	public void setAtr(Double atr) {
		this.atr = atr;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	@XmlElement
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getPrevClose() {
		return prevClose;
	}

	@XmlElement
	public void setPrevClose(Double prevClose) {
		this.prevClose = prevClose;
	}

	public Double getShortFloat() {
		return shortFloat;
	}

	@XmlElement
	public void setShortFloat(Double shortFloat) {
		this.shortFloat = shortFloat;
	}

	public String getTicker() {
		return ticker;
	}

	@XmlElement
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getDividend() {
		return dividend;
	}

	@XmlElement
	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}

	public String getCompanyName() {
		return companyName;
	}

	@XmlElement
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyBriefing() {
		return companyBriefing;
	}

	@XmlElement
	public void setCompanyBriefing(String companyBriefing) {
		this.companyBriefing = companyBriefing;
	}

	public String getIndustry() {
		return industry;
	}

	@XmlElement
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSector() {
		return sector;
	}

	@XmlElement
	public void setSector(String sector) {
		this.sector = sector;
	}

	public Double getTargetPrice() {
		return targetPrice;
	}

	@XmlElement
	public void setTargetPrice(Double targetPrice) {
		this.targetPrice = targetPrice;
	}

	public DividendData getDivData() {
		return divData;
	}

	@XmlElement
	public void setDivData(DividendData divData) {
		this.divData = divData;
	}

	public String getEarningsCall() {
		return earningsCall;
	}

	@XmlElement
	public void setEarningsCall(String earningsCall) {
		this.earningsCall = earningsCall;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	@XmlElement
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getEarningsStats() {
		return earningsStats;
	}

	@XmlElement
	public void setEarningsStats(String earningsStats) {
		this.earningsStats = earningsStats;
	}

	public FundamentalData getFundaData() {
		return fundaData;
	}

	@XmlElement
	public void setFundaData(FundamentalData fundaData) {
		this.fundaData = fundaData;
	}

	public PerformanceData getPerfData() {
		return perfData;
	}

	@XmlElement
	public void setPerfData(PerformanceData perfData) {
		this.perfData = perfData;
	}

	public String toString() {

		return ticker + " | " + atr.toString() + " | " + shortFloat == null ? ""
				: shortFloat.toString() + " | " + dividend == null ? ""
						: dividend.toString() + " | " + companyName.toString() + " | " + targetPrice == null ? ""
								: targetPrice.toString() + " | ";

	}
}
