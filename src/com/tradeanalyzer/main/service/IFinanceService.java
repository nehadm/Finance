package com.tradeanalyzer.main.service;

import java.text.ParseException;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;

import com.tradeanalyzer.main.dao.FundamentalData;
import com.tradeanalyzer.main.dao.IndexData;
import com.tradeanalyzer.main.dao.StockData;

public interface IFinanceService {

	public StockData getFinDataForSymbol(String tickerSymbol) throws HttpStatusException;

	public FundamentalData getFundamentalData(String ticker, Document finvizDoc) throws ParseException;

	public ArrayList<IndexData> getAllMarketIndexes();

	public ArrayList<StockData> getStocksList();
}
