package com.data;

public class StockData {

	private Double atr;
	private Double stockPrice;
	private Double shortFloat;
	private String ticker;
	private Double dividend=0.0;
	private String companyName;
	private Double targetPrice;
	private DividendData divData;	
	private String earningsCall;
	
	public StockData() {
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

	public String toString() {
		return ticker+" | "+atr.toString()+" | "+shortFloat.toString()+" | "+dividend.toString()+" | "+companyName.toString()+" | "+targetPrice.toString()+" | "+dividend.toString();
		
	}
}
