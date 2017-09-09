package com.tradeanalyzer.main.data;

import java.util.ArrayList;

import com.tradeanalyzer.main.dao.StockData;

public interface IDatabaseConnection {

	ArrayList<StockData> getStocksList();
}