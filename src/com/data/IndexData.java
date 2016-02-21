package com.data;

public class IndexData {
	private String indexName;
	private String indexLastValue;
	private String symbol;

	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexLastValue() {
		return indexLastValue;
	}
	public void setIndexLastValue(String indexLastValue) {
		this.indexLastValue = indexLastValue;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}	
	
	@Override
	public String toString() {
		return indexName+" | "+ symbol +" | "+indexLastValue;
	}
}
