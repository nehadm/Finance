package com.service;

import java.util.ArrayList;

import com.data.IndexData;
import com.data.StockData;

public interface IFinanceService {

	public StockData getFinDataForSymbol(String tickerSymbol);

	public ArrayList<IndexData> getAllMarketIndexes();
	
}
