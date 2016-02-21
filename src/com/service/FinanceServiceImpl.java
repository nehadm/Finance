package com.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.data.DividendData;
import com.data.IndexData;
import com.data.StockData;

public class FinanceServiceImpl implements IFinanceService {

	@Override
	public StockData getFinDataForSymbol(String tickerSymbol) {
		
		StockData finData = new StockData();
		Document finvizDoc;
		Document marketWatchDoc;
		Document dividendDataDoc;
		
		try {
			finvizDoc = Jsoup.connect("http://finviz.com/quote.ashx?t="+tickerSymbol).get();
			Element atrField = finvizDoc.getElementsMatchingOwnText("ATR").first();
			Node atrValue = atrField.nextElementSibling().childNode(0);
			//return Jsoup.parse(atrValue.toString()).text();
			finData.setAtr((Double.parseDouble(Jsoup.parse(atrValue.toString()).text())));
			finData.setTicker(tickerSymbol);
			
			Element dividendYield = finvizDoc.getElementsMatchingOwnText("Dividend %").first();
			Node dividendYieldValue = dividendYield.nextElementSibling().childNode(0);
			String dividendValueString = Jsoup.parse(dividendYieldValue.toString()).text();
			if(!dividendValueString.equals("-"))
				finData.setDividend(Double.parseDouble(dividendValueString.replace("%", "")));
			
			Element shortFloat = finvizDoc.getElementsMatchingOwnText("Short Float").first();
			Node shortVal = shortFloat.nextElementSibling().childNode(0);
			String shortValString = Jsoup.parse(shortVal.toString()).text();
			finData.setShortFloat(Double.parseDouble(shortValString.replace("%", "")));
			
			Element targetPrice = finvizDoc.getElementsMatchingOwnText("Target Price").first();
			Node targetPriceNode = targetPrice.nextElementSibling().childNode(0);
			String targetPriceString = Jsoup.parse(targetPriceNode.toString()).text();
			finData.setTargetPrice(Double.parseDouble(targetPriceString));
			
		    Element earningsCall = finvizDoc.getElementsMatchingOwnText("Earnings").first();
			Node earningsCallNode = earningsCall.nextElementSibling().childNode(0);
			finData.setEarningsCall(Jsoup.parse(earningsCallNode.toString()).text());
			
			marketWatchDoc = Jsoup.connect("http://www.marketwatch.com/investing/stock/"+tickerSymbol).get();
			Element companyName = marketWatchDoc.getElementById("instrumentname");
			finData.setCompanyName(Jsoup.parse(companyName.toString()).text());
			
			Element stockPrice = marketWatchDoc.select(".data.bgLast").first();
			Double priceDouble = (Double.parseDouble(Jsoup.parse(stockPrice.toString()).text()));

			finData.setStockPrice(priceDouble);
			
			System.out.println("Finance Data object:"+finData.toString());
			
			dividendDataDoc = Jsoup.connect("https://dividata.com/stock/"+tickerSymbol).get();
			Element exDivDateElement = dividendDataDoc.getElementsMatchingOwnText("Ex-Dividend Date").first();
			Node exDivDate = exDivDateElement.nextElementSibling().childNode(0);
			boolean hasDividend = (Jsoup.parse(exDivDate.toString()).text().equals("N/A")) ? false : true;
			
			if(hasDividend) {
				Date exDivDateFormat = (new SimpleDateFormat("MM/dd/yy").parse(Jsoup.parse(exDivDate.toString()).text()));
				
				Element payDateElement = dividendDataDoc.getElementsMatchingOwnText("Pay Date").first();
				Node payDateNode = payDateElement.nextElementSibling().childNode(0);
				Date payDateFormat = (new SimpleDateFormat("MM/dd/yy").parse(Jsoup.parse(payDateNode.toString()).text()));
				
				Element divYieldElement = dividendDataDoc.getElementsMatchingOwnText("Dividend Yield").get(1);
				Node divYieldNode = divYieldElement.nextElementSibling().childNode(0);
				Double divYield = Double.parseDouble((Jsoup.parse(divYieldNode.toString()).text()).replace("%", ""));
				
				Element annualDivElement = dividendDataDoc.getElementsMatchingOwnText("Annual Dividend").first();
				Node annualDivNode = annualDivElement.nextElementSibling().childNode(0);
				Double annualDiv = Double.parseDouble((Jsoup.parse(annualDivNode.toString()).text()).replace("$", ""));
				
				Element yearsPaying = dividendDataDoc.getElementsMatchingOwnText("Years Paying").first();
				Node yearsPayingNode = yearsPaying.nextElementSibling().childNode(0);
				int yearsPayingDiv = Integer.parseInt((Jsoup.parse(yearsPayingNode.toString()).text()).replace("$", ""));
				
				
				DividendData divData = new DividendData(exDivDateFormat, payDateFormat, divYield, annualDiv, yearsPayingDiv);
				finData.setDivData(divData);
			} else {
				finData.setDivData(null);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		
		return finData;
	}

	@Override
	public ArrayList<IndexData> getAllMarketIndexes(){
		
		Document indexes;
		ArrayList<IndexData> indexesList = new ArrayList<IndexData>();
		
		try {
			indexes = Jsoup.connect("http://bigcharts.marketwatch.com/markets/indexes.asp").get();
		    Elements downFields = indexes.getElementsByClass("down");
		    Elements upFields = indexes.getElementsByClass("up");
		    
		    getIndicesBasedOnClass(downFields, indexesList);
		    getIndicesBasedOnClass(upFields, indexesList);
		    
		    		    
		} catch(Exception e) {
			e.printStackTrace();
		}
		return indexesList;
	}
	
	public ArrayList<IndexData> getIndicesBasedOnClass(Elements fields, ArrayList<IndexData> indexesList) {
		for(Element field : fields) {
	    	IndexData data = new IndexData();
	    	
	    	Node index = field.childNode(0);
	    	Node indexName = index.parentNode().childNode(1);
	    	Node symbol = index.parentNode().childNode(3);
	    	Node value = index.parentNode().childNode(7);
	    	data.setIndexName(Jsoup.parse(indexName.toString()).text());
	    	data.setIndexLastValue(Jsoup.parse(value.toString()).text());
	    	data.setSymbol(Jsoup.parse(symbol.toString()).text());
	    	indexesList.add(data);
	    }
		return indexesList;
	}
	
}
