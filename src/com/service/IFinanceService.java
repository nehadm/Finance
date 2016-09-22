package com.service;

import java.text.ParseException;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;

import com.data.FundamentalData;
import com.data.IndexData;
import com.data.StockData;

public interface IFinanceService {

	public StockData getFinDataForSymbol(String tickerSymbol) throws HttpStatusException;

	public FundamentalData getFundamentalData(String ticker, Document finvizDoc) throws ParseException;
	
	public ArrayList<IndexData> getAllMarketIndexes();
	
}
