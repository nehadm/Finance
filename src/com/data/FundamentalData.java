package com.data;

public class FundamentalData {

	private Double priceToEarnings;
	private Double earningsPerShare;
	private Double forwardPriceToEarnings;
	private String marketCap;
	private Double roi;

	public Double getPriceToEarnings() {
		return priceToEarnings;
	}
	public void setPriceToEarnings(Double priceToEarnings) {
		this.priceToEarnings = priceToEarnings;
	}
	public Double getEarningsPerShare() {
		return earningsPerShare;
	}
	public void setEarningsPerShare(Double earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}
	public Double getForwardPriceToEarnings() {
		return forwardPriceToEarnings;
	}
	public void setForwardPriceToEarnings(Double forwardPriceToEarnings) {
		this.forwardPriceToEarnings = forwardPriceToEarnings;
	}
	public String getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	public Double getRoi() {
		return roi;
	}
	public void setRoi(Double roi) {
		this.roi = roi;
	}
}
