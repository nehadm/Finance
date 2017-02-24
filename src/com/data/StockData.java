package com.data;

public class StockData {

	private Double atr;
	private Double stockPrice;
	private Double prevClose;
	private Double shortFloat;
	private String ticker;
	private Double dividend=0.0;
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

	public StockData(String companyName, String ticker) {
		super();
		this.companyName = companyName;
		this.ticker = ticker;
	}

	public StockData(Double atr, Double stockPrice, Double prevClose, Double shortFloat, String ticker, Double dividend,
			String companyName, String companyBriefing, String industry, String sector, Double targetPrice,
			String stockExchange, DividendData divData, String earningsCall, String earningsStats,
			FundamentalData fundaData, PerformanceData perfData) {
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

	public void setAtr(Double atr) {
		this.atr = atr;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(Double prevClose) {
		this.prevClose = prevClose;
	}

	public Double getShortFloat() {
		return shortFloat;
	}

	public void setShortFloat(Double shortFloat) {
		this.shortFloat = shortFloat;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getDividend() {
		return dividend;
	}

	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyBriefing() {
		return companyBriefing;
	}

	public void setCompanyBriefing(String companyBriefing) {
		this.companyBriefing = companyBriefing;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Double getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(Double targetPrice) {
		this.targetPrice = targetPrice;
	}

	public DividendData getDivData() {
		return divData;
	}

	public void setDivData(DividendData divData) {
		this.divData = divData;
	}
	
	public String getEarningsCall() {
		return earningsCall;
	}

	public void setEarningsCall(String earningsCall) {
		this.earningsCall = earningsCall;
	}
	
	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getEarningsStats() {
		return earningsStats;
	}

	public void setEarningsStats(String earningsStats) {
		this.earningsStats = earningsStats;
	}

	public FundamentalData getFundaData() {
		return fundaData;
	}

	public void setFundaData(FundamentalData fundaData) {
		this.fundaData = fundaData;
	}
	
	public PerformanceData getPerfData() {
		return perfData;
	}

	public void setPerfData(PerformanceData perfData) {
		this.perfData = perfData;
	}

	public String toString() {
		
		return ticker+" | "
		+atr.toString()+" | "
		+shortFloat == null? "" : shortFloat.toString()+" | "
		+dividend == null ? "" : dividend.toString()+" | "
		+companyName.toString()+" | "
		+targetPrice == null ? "" : targetPrice.toString()+" | ";
		
	}
}
