package com.tradeanalyzer.main.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "indexData")
public class IndexData {
	private String indexName;
	private String indexLastValue;
	private String symbol;
	private Double percentChange;

	public IndexData() {

	}

	public String getIndexName() {
		return indexName;
	}

	@XmlElement
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexLastValue() {
		return indexLastValue;
	}

	@XmlElement
	public void setIndexLastValue(String indexLastValue) {
		this.indexLastValue = indexLastValue;
	}

	public String getSymbol() {
		return symbol;
	}

	@XmlElement
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPercentChange() {
		return percentChange;
	}

	@XmlElement
	public void setPercentChange(Double percentChange) {
		this.percentChange = percentChange;
	}

	@Override
	public String toString() {
		return indexName + " | " + symbol + " | " + indexLastValue + " | " + percentChange;
	}
}
