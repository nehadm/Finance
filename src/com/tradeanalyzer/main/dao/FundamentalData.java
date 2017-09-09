package com.tradeanalyzer.main.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fundaData")
public class FundamentalData {

	private Double priceToEarnings;
	private Double earningsPerShare;
	private Double forwardPriceToEarnings;
	private String marketCap;
	private Double roi;

	public FundamentalData() {
	}

	public Double getPriceToEarnings() {
		return priceToEarnings;
	}

	@XmlElement
	public void setPriceToEarnings(Double priceToEarnings) {
		this.priceToEarnings = priceToEarnings;
	}

	public Double getEarningsPerShare() {
		return earningsPerShare;
	}

	@XmlElement
	public void setEarningsPerShare(Double earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}

	public Double getForwardPriceToEarnings() {
		return forwardPriceToEarnings;
	}

	@XmlElement
	public void setForwardPriceToEarnings(Double forwardPriceToEarnings) {
		this.forwardPriceToEarnings = forwardPriceToEarnings;
	}

	public String getMarketCap() {
		return marketCap;
	}

	@XmlElement
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public Double getRoi() {
		return roi;
	}

	@XmlElement
	public void setRoi(Double roi) {
		this.roi = roi;
	}
}
