package com.finance.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;




import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class FinanceTest {

	public void calculatePositionSize(HashMap<String, String> stockList) {
		Iterator it = stockList.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        System.out.println("you risk a 100$ every day for "+pair.getKey() + " if you get " + 100 / Math.floor((Double.parseDouble(pair.getValue().toString()))) + " shares");
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	}
	
	public void calculatePositionSize(String stock, String atrValue) {
	   System.out.println(stock + " = " + atrValue);
	   System.out.println("you risk a $100 every day for "+stock + " if you get " + 100 / Math.floor((Double.parseDouble(atrValue.toString()))) + " shares");
	}
	
	public void getStockTickers() {
		HashMap<String, String> dow30 = new HashMap<String, String>();
		HashMap<String, String> myfav = new HashMap<String, String>();
		try{
			BufferedReader dow30BR = null;
			BufferedReader myfavBR = null; 
//			dow30BR = new BufferedReader(new FileReader("src/com/finance/main/dow30.txt"));
			String line = "";
//			while ((line = dow30BR.readLine()) != null) {
//				dow30.put(line, getATR(line));
//			}
			
			myfavBR = new BufferedReader(new FileReader("src/com/finance/main/myfavstocks.txt"));
			while ((line = myfavBR.readLine()) != null)
			{
				myfav.put(line, getATR(line));
			}
			calculatePositionSize(dow30);
			calculatePositionSize(myfav);
			//dow30BR.close();
			myfavBR.close();
		} catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getATR(String ticker) throws IOException {
		Document doc = Jsoup.connect("http://finviz.com/quote.ashx?t="+ticker).get();
		Element atrField = doc.getElementsMatchingOwnText("ATR").first();
		Node atrValue = atrField.nextElementSibling().childNode(0);
		return Jsoup.parse(atrValue.toString()).text();
	}

	public String getShortFloat(String ticker) throws IOException {
		Document doc = Jsoup.connect("http://finviz.com/quote.ashx?t="+ticker).get();
		Element shortFloat = doc.getElementsMatchingOwnText("Short Float").first();
		Node shortVal = shortFloat.nextElementSibling().childNode(0);
		
		return Jsoup.parse(shortVal.toString()).text();
	}
	
	public static void main(String[] args) throws IOException {		
		FinanceTest ft = new FinanceTest();
		String ticker = "dis";
		//System.out.println(ft.getATR(ticker));
		System.out.println("**************************************");
		//ft.getStockTickers();
	//	ft.calculatePositionSize(ticker, ft.getATR(ticker));
//		System.out.println(ft.getShortFloat(ticker));
//		String abc = ft.getShortFloat(ticker);
//		abc = abc.replace("%", "");
//		System.out.println(abc);
//		Double d = Double.parseDouble(abc);
//		System.out.println(d);
		
//		Document doc = Jsoup.connect("http://www.marketwatch.com/investing/stock/"+ticker).get();
//		Element companyName = doc.getElementById("instrumentname");
//		//Node node = companyName.html(html)
//		System.out.println(Jsoup.parse(companyName.toString()).text());
//		
		Document doc2 = Jsoup.connect("http://finviz.com/quote.ashx?t="+ticker).get();
	    Element earningscall = doc2.getElementsMatchingOwnText("Earnings").first();
		Node atrValue = earningscall.nextElementSibling().childNode(0);

		
		System.out.println(Jsoup.parse(atrValue.toString()).text());
//		
//		//Double db = Double.parseDouble(Jsoup.parse(lastPrice.toString()).text());
//		Document dividendDataDoc = Jsoup.connect("https://dividata.com/stock/DIS").get();
//		Element divYieldElement = dividendDataDoc.getElementsMatchingOwnText("Dividend Yield").get(1);
//		Node divYieldNode = divYieldElement.nextElementSibling().childNode(0);
//		Double divYield = Double.parseDouble((Jsoup.parse(divYieldNode.toString()).text()).replace("%", ""));
//		System.out.println(divYield);
		
		
//		Document indexes;
//		
//		try {
//			indexes = Jsoup.connect("http://bigcharts.marketwatch.com/markets/indexes.asp").get();
//		    Elements atrFields = indexes.getElementsByClass("down");
//		   // System.out.println(atrFields.toString());
//		    
//		    for(Element field : atrFields) {
//		    	
//		    	Node xyz = field.childNode(0);
//		    	
//		    	Node symbol = xyz.parentNode().childNode(1);
//		    	Node value = xyz.parentNode().childNode(3);
//		    	//Node abc = xyz.parentNode().childNode(5);
//		    	Node def = xyz.parentNode().childNode(7);
//		    	//System.out.println(xyz.toString());
//		    	System.out.println(symbol.toString());
//		    	System.out.println(value.toString());
//		    	//System.out.println(Jsoup.parse(abc.toString()).text());
//		    	System.out.println(Jsoup.parse(def.toString()).text());
////				Double divYield = Double.parseDouble((Jsoup.parse(divYieldNode.toString()).text()).replace("%", ""));
//		    }
//	}
//		    
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}

}
