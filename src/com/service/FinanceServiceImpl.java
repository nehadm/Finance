package com.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.data.DividendData;
import com.data.FundamentalData;
import com.data.IndexData;
import com.data.PerformanceData;
import com.data.StockData;

public class FinanceServiceImpl implements IFinanceService {

	@Override
	public StockData getFinDataForSymbol(String tickerSymbol) {
		
		MarketWatchService mwService;
		FinvizService fvService;
		DividataService ddService;
		
		final Logger log= Logger.getLogger(FinanceServiceImpl.class.getName());
		StockData finData = new StockData();
		Document finvizDoc;
		Document marketWatchDoc;
		Document dividendDataDoc;
		boolean hasDividend = true;

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
			if(dividendValueString.equals("-")) {
				hasDividend = false;
				//finData.setDividend(Double.parseDouble(dividendValueString.replace("%", "")));
			}
			
			Element prevClose = finvizDoc.getElementsMatchingOwnText("Prev Close").first();
			Node prevCloseNode = prevClose.nextElementSibling().childNode(0);
			String prevCloseString = Jsoup.parse(prevCloseNode.toString()).text();
			if(!prevCloseString.equals("-"))
				finData.setPrevClose(Double.parseDouble(prevCloseString));

			Element shortFloat = finvizDoc.getElementsMatchingOwnText("Short Float").first();
			Node shortVal = shortFloat.nextElementSibling().childNode(0);
			String shortValString = Jsoup.parse(shortVal.toString()).text();
			if(!shortValString.equals("-"))
				finData.setShortFloat(Double.parseDouble(shortValString.replace("%", "")));
			
			Element targetPrice = finvizDoc.getElementsMatchingOwnText("Target Price").first();
			Node targetPriceNode = targetPrice.nextElementSibling().childNode(0);
			String targetPriceString = Jsoup.parse(targetPriceNode.toString()).text();
			if(!targetPriceString.equals("-"))
				finData.setTargetPrice(Double.parseDouble(targetPriceString));
			
		    Element earningsCall = finvizDoc.getElementsMatchingOwnText("Earnings").first();
			Node earningsCallNode = earningsCall.nextElementSibling().childNode(0);
			String earningsCallString = Jsoup.parse(earningsCallNode.toString()).text();
			if(!earningsCallNode.equals("-"))
				finData.setEarningsCall(earningsCallString);
			
			marketWatchDoc = Jsoup.connect("http://www.marketwatch.com/investing/stock/"+tickerSymbol).get();
			Element companyName = marketWatchDoc.getElementById("instrumentname");
			finData.setCompanyName(Jsoup.parse(companyName.toString()).text());
			
			Element stockPrice = marketWatchDoc.select(".data.bgLast").first();
			Double priceDouble = (Double.parseDouble(Jsoup.parse(stockPrice.toString()).text()));

			finData.setStockPrice(priceDouble);
			
			Document marketWatchProfileDoc = Jsoup.connect("http://www.marketwatch.com/investing/Stock/"+tickerSymbol+"/profile?CountryCode=US").get();
			Element parentElementForSectorIndustry = marketWatchProfileDoc.getElementsMatchingOwnText("At a Glance").first();
			String industry = parentElementForSectorIndustry == null ? "" : Jsoup.parse(parentElementForSectorIndustry.siblingElements().get(1).getElementsMatchingOwnText("Industry").first().nextElementSibling().childNode(0).toString()).text();
			System.out.println(industry);
			finData.setIndustry(industry);
			
			Element sector = marketWatchProfileDoc.getElementsMatchingOwnText("At a Glance").first();
			String sectorName = parentElementForSectorIndustry == null ? "" : Jsoup.parse(sector.siblingElements().get(1).getElementsMatchingOwnText("Sector").first().nextElementSibling().childNode(0).toString()).text();
			System.out.println(sectorName);
			finData.setSector(sectorName);
			
			Document earningsStatsDoc = Jsoup.connect("http://stocksearning.com/q.aspx?Sys="+tickerSymbol).get();
			
			Element earningsStatElement = earningsStatsDoc.getElementById("ContentPlaceHolder1_lbltotaltime");
			//Element earningsStatElement2 = earningsStatsDoc.getElementById("ContentPlaceHolder1_lblTotalTimes_Per");
			//System.out.println("Finance Data object:"+finData.toString());
			
			//System.out.println(earningsStatElement.toString());

			if(hasDividend) {
				dividendDataDoc = Jsoup.connect("https://dividata.com/stock/"+tickerSymbol).get();
				Element exDivDateElement = dividendDataDoc.getElementsMatchingOwnText("Ex-Dividend Date").first();
				Node exDivDate = exDivDateElement.nextElementSibling().childNode(0);
				// = (Jsoup.parse(exDivDate.toString()).text().equals("N/A")) ? false : true;
				//hasDividend = finData.getDividend() == null ? false : true;

				Date exDivDateFormat = (new SimpleDateFormat("MM/dd/yy").parse(Jsoup.parse(exDivDate.toString()).text()));
				
				Element payDateElement = dividendDataDoc.getElementsMatchingOwnText("Pay Date").first();
				Node payDateNode = payDateElement.nextElementSibling().childNode(0);
				Date payDateFormat = (new SimpleDateFormat("MM/dd/yy").parse(Jsoup.parse(payDateNode.toString()).text()));
				
				Element divYieldElement = dividendDataDoc.getElementsMatchingOwnText("Dividend Yield").get(1);
				Node divYieldNode = divYieldElement.nextElementSibling().childNode(0);
				String divYieldString = Jsoup.parse(divYieldNode.toString()).text().replace("%", "");
				Double divYield = Double.parseDouble(divYieldString.equals("N/A") ? "0" : divYieldString);
				
				Element annualDivElement = dividendDataDoc.getElementsMatchingOwnText("Annual Dividend").first();
				Node annualDivNode = annualDivElement.nextElementSibling().childNode(0);
				String annualDivString = Jsoup.parse(annualDivNode.toString()).text().replace("$", "");
				Double annualDiv = Double.parseDouble(annualDivString.equals("N/A") ? "0" : annualDivString);				
				Element yearsPaying = dividendDataDoc.getElementsMatchingOwnText("Years Paying").first();
				Node yearsPayingNode = yearsPaying.nextElementSibling().childNode(0);
				int yearsPayingDiv = Integer.parseInt((Jsoup.parse(yearsPayingNode.toString()).text()).replace("$", ""));
				
				
				DividendData divData = new DividendData(exDivDateFormat, payDateFormat, divYield, annualDiv, yearsPayingDiv);
				finData.setDivData(divData);
			} else {
				finData.setDivData(null);
			}
			
			FundamentalData fundData = getFundamentalData(tickerSymbol, finvizDoc);
			finData.setFundaData(fundData);
			
			PerformanceData perfData = getPerfData(tickerSymbol, finvizDoc);
			finData.setPerfData(perfData);
			
		} catch(HttpStatusException hse) {
			finData = null;
			log.info("Symbol Not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		
		return finData;
	}

	private PerformanceData getPerfData(String tickerSymbol, Document doc) {
		
		PerformanceData perfData = new PerformanceData();
		Element tradingRange52W = doc.getElementsMatchingOwnText("52W Range").first();
		Node tradingRange52WNode = tradingRange52W.nextElementSibling().childNode(0);
		String tradingRange52WString = Jsoup.parse(tradingRange52WNode.toString()).text();
		if(!tradingRange52WString.equals("-"))
			perfData.setTradingRange52Week(tradingRange52WString);
		
		Element perfYear = doc.getElementsMatchingOwnText("Perf Year").first();
		Node perfYearNode = perfYear.nextElementSibling().childNode(0);
		String perfYearString = Jsoup.parse(perfYearNode.toString()).text();
		if(!perfYearString.equals("-"))
			perfData.setPerfYear(Double.parseDouble(perfYearString.replace("%", "")));
		
		Element perfQuarter = doc.getElementsMatchingOwnText("Perf Quarter").first();
		Node perfQuarterNode = perfQuarter.nextElementSibling().childNode(0);
		String perfQuarterString = Jsoup.parse(perfQuarterNode.toString()).text();
		if(!perfQuarterString.equals("-"))
			perfData.setPerfQuarter(Double.parseDouble(perfQuarterString.replace("%", "")));
		
		Element perfWeek = doc.getElementsMatchingOwnText("Perf Week").first();
		Node perfWeekNode = perfWeek.nextElementSibling().childNode(0);
		String perfWeekString = Jsoup.parse(perfWeekNode.toString()).text();
		if(!perfWeekString.equals("-"))
			perfData.setPerfWeek(Double.parseDouble(perfWeekString.replace("%", "")));
		
		Element perfMonthly = doc.getElementsMatchingOwnText("Perf Month").first();
		Node perfMonthlyNode = perfMonthly.nextElementSibling().childNode(0);
		String perfMonthlyString = Jsoup.parse(perfMonthlyNode.toString()).text();
		if(!perfMonthlyString.equals("-"))
			perfData.setPerfMonthly(Double.parseDouble(perfMonthlyString.replace("%", "")));

		Element perfYTD = doc.getElementsMatchingOwnText("Perf YTD").first();
		Node perfYTDNode = perfYTD.nextElementSibling().childNode(0);
		String perfYTDString = Jsoup.parse(perfYTDNode.toString()).text();
		if(!perfYTDString.equals("-"))
			perfData.setPerfYTD(Double.parseDouble(perfYTDString.replace("%", "")));

		return perfData;		
	}

	@Override
	public FundamentalData getFundamentalData(String ticker, Document doc) throws ParseException {
		FundamentalData fd = new FundamentalData();
		
		Element p2e = doc.getElementsMatchingOwnText("P/E").first();
		Node p2eValue = p2e.nextElementSibling().childNode(0);
		String p2eString = Jsoup.parse(p2eValue.toString()).text();
		if(!p2eString.equals("-"))
			fd.setPriceToEarnings(Double.parseDouble(p2eString));
		
		Element eps = doc.getElementsMatchingOwnText("EPS").first();
		Node epsNode = eps.nextElementSibling().childNode(0);
		String epsString = Jsoup.parse(epsNode.toString()).text();
		if(!epsString.equals("-"))
			fd.setEarningsPerShare(Double.parseDouble(epsString));
		
		Element forwardP2E = doc.getElementsMatchingOwnText("Forward P/E").first();
		Node forwardP2Enode = forwardP2E.nextElementSibling().childNode(0);
		String forwardP2EString = Jsoup.parse(forwardP2Enode.toString()).text();
		if(!forwardP2EString.equals("-"))
			fd.setForwardPriceToEarnings(Double.parseDouble(forwardP2EString));
		
		Element marketCap = doc.getElementsMatchingOwnText("Market Cap").first();
		Node marketCapNode = marketCap.nextElementSibling().childNode(0);
		String marketCapString = Jsoup.parse(marketCapNode.toString()).text();
		if(!marketCapString.equals("-"))
			fd.setMarketCap(marketCapString);
		
		Element roi = doc.getElementsMatchingOwnText("ROI").first();
		Node roiNode = roi.nextElementSibling().childNode(0);
		String roiString = Jsoup.parse(roiNode.toString()).text();
		if(!roiString.equals("-"))
			fd.setRoi(Double.parseDouble(roiString.replace("%", "")));
		return fd;
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
//		for (IndexData index : indexesList) {
//			if(!index.getSymbol().equalsIgnoreCase("SPX") || )
//		}
		return indexesList;
	}
	
	public ArrayList<IndexData> getIndicesBasedOnClass(Elements fields, ArrayList<IndexData> indexesList) {
		for(Element field : fields) {
	    	IndexData data = new IndexData();
	    	Node index = field.childNode(0);
	    	Node indexName = index.parentNode().childNode(1);
	    	Node symbol = index.parentNode().childNode(3);
	    	Node value = index.parentNode().childNode(7);
	    	Node percentChange = index.parentNode().childNode(11);
	    	data.setIndexName(Jsoup.parse(indexName.toString()).text());
	    	data.setIndexLastValue(Jsoup.parse(value.toString()).text());
	    	data.setSymbol(Jsoup.parse(symbol.toString()).text());
	    	data.setPercentChange(Double.parseDouble(Jsoup.parse(percentChange.toString().replace("%", "")).text()));
	    	indexesList.add(data);
	    }
		return indexesList;
	}
	
}
