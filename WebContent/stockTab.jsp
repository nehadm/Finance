<div id="stockTab" ng-controller="financeController" ng-init="showTable=false;">

<div class="marketIndicesStrip">
<a ng-repeat="x in marketIndices" id="news_{{x.symbol}}" target="_blank" class="tooltip">
<span class="w3-small"> 
{{ x.symbol }} {{ x.indexLastValue }} {{ x.percentChange }} %&nbsp;
	    	<img ng-show="x.percentChange < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="16"/>
	    	<img ng-show="x.percentChange > 0" src="images/green-triangle.gif" title="Bullish" width="16"/>&nbsp;&nbsp;
</span>
<span class="w3-small tooltiptext">{{x.indexName}}</span>
</a> &nbsp; &nbsp; &nbsp;

</div> 
<span class="w3-tiny">{{date | date:'MMM dd yyyy, hh:mm:ss a'}}
<span ng-show="{{date}}"></span>
</span>
<!-- Show the stock input text and button here-->
<div class="w3-left">
	<form name="inputSymbolForm">
	
	  <div>  
	  	<input id="tickerInput" type="text" ng-model="selected" ng-pattern="regex" required="" ng-minlength="1" ng-maxlength="5">
<!-- 	  	<label class="w3-red w3-validate" ng-hide="inputSymbolForm.selected.$valid">Invalid ticker symbol.</label> -->
	    <label class="w3-validate" ng-show="inputSymbolForm.selected.$error.minlength">Symbol too short</label>
		<label class="w3-validate" ng-show="inputSymbolForm.selected.$error.maxlength">Symbol too long</label>
		<br/>
	  
		<select id="tickerSelected" ng-model="eg" ng-change="getStockData(eg.split('-')[0])">
  			<option ng-repeat="x in stockList | filter: selected">
    			{{ x }}
  			</option>
	  	</select>
	  </div>
	</form>
	
	<p ng-show="finance == null" class="w3-validate">{{errorText}}</p>
</div>

<div class="w3-row-padding w3-center w3-margin-top">
	<div ng-show="showTable && finance != null" class="w3-container m2 w3-text-dark-grey">
		<div class="w3-theme-light">
			<h2> {{finance.companyName}} ({{finance.stockPrice | currency}})</h2>
			<span class="w3-tiny">Industry - {{finance.industry}} , Sector - {{finance.sector}} </span>
		</div>
		<span ng-show="showTable" class="w3-small">52 Week Range : {{finance.perfData.tradingRange52Week}}</span>
	</div>
	<img id="spinner" src="images/spinner.gif" style="display:none"></img>
	<div class="w3-center">
		<div ng-class="classValue" ng-show="showTable && finance != null">						
		  <div class="w3-card-2 w3-padding-top" style="min-height:460px">
		  <h5 class="w3-theme-light">General Data</h5>
		  <hr>
		  <p>Ticker : {{ finance.ticker | uppercase}}</p>
		  <p>ATR : {{ finance.atr }}</p>
		  <p>Short Float : {{ finance.shortFloat }} %</p>
		  <p>Target Price : {{ finance.targetPrice |currency}}</p>
		  <p>Earnings Call : {{ finance.earningsCall }}</p>
		  </div>
		</div>
	
		<div ng-show="showTable && finance.divData != null" class="w3-third">
		  <div class="w3-card-2 w3-padding-top" style="min-height:460px">
		  <h5 class="w3-theme-light">Dividend Data</h5><hr>
		  <p>Ex Div Date : {{ finance.divData.exDivDate | date: 'MMM dd yyyy' }}</p>
		  <p>Pay Date : {{ finance.divData.payDate }}</p>
		  <p>Annual Dividend : {{ finance.divData.annualDividend | currency }}</p>
		  <p>Div Yield(%) : {{ finance.divData.divYield }}</p>
		  <p>Years Paying : {{ finance.divData.yearsPaying }}</p>
		  </div>
		</div>
		
		<div ng-show="showTable && finance.fundaData != null" ng-class="classValue">
		  <div class="w3-card-2 w3-padding-top" style="min-height:460px">
		  <h5 class="w3-theme-light">Fundamental Data</h5><hr>
		  <p>P/E Ratio : {{ finance.fundaData.priceToEarnings }}</p>
		  <p>Forward P/E Ratio : {{ finance.fundaData.forwardPriceToEarnings }}</p>
		  <p>Earnings Per Share : {{ finance.fundaData.earningsPerShare | currency }}</p>
		  <p>ROI(%) : {{ finance.fundaData.roi }}</p>
		  <p>Market Cap : {{ finance.fundaData.marketCap }}</p>
		  </div>
		</div>
<!-- 	</div> -->
<!-- 	</div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> -->
<!-- 	<div class="w3-center" ng-show="showTable && finance != null"> -->
	  <div class="w3-card-2 w3-padding-top w3-third" ng-show="showTable && finance != null" style="min-height:460px">
	  <h5 class="w3-theme-light">ATR Calculation</h5><hr>
		<table class="w3-table w3-bordered w3-theme-light">
	 	<tr colspan="3">
	  		<th>Risked Amount per day($)</th>
	  		<th>Position Size</th>
	  		<th>Amount invested($)</th>
	  	</tr>
	  	<tr>
	    	<td>100</td>
	    	<td ng-bind="100 / finance.atr | number:2"></td>
	    	<td ng-bind="(100 / finance.atr) * finance.stockPrice | number:0"></td>
	  	</tr>
	  	<tr>
	    	<td >150</td>
	    	<td ng-bind="150 / finance.atr | number:2"></td>
	    	<td ng-bind="(150 / finance.atr) * finance.stockPrice | number:0"></td>
	  	</tr>
	  	<tr>
	    	<td >200</td>
	    	<td ng-bind="200 / finance.atr | number:0"></td>
	    	<td ng-bind="(200 / finance.atr) * finance.stockPrice | number:0 "></td>
	  	</tr>
	  	<tr>
	    	<td>250</td>
	    	<td ng-bind="250 / finance.atr | number:0"></td>
	    	<td ng-bind="(250 / finance.atr) * finance.stockPrice | number:0 "></td>
	  	</tr>
	  	<tr>
	    	<td>300</td>
	    	<td ng-bind="300 / finance.atr | number:0"></td>
	    	<td ng-bind="(300 / finance.atr) * finance.stockPrice | number:0 "></td>
	  	</tr>
	  	
	  	<tr>
	    	<td><input type="text" ng-model="riskAmount" ng-show="showTable"></td>
	    	<td ng-init="0" ng-bind="riskAmount / finance.atr || 0 | number:2"></td>
	    	<td ng-init="0" ng-bind="(riskAmount / finance.atr) * finance.stockPrice || 0 | number:0"></td>
	  	</tr>
	  </table>
	  </div>
	  <div class="w3-card-2 w3-padding-top w3-quarter" ng-show="showTable && finance != null" style="min-height:460px">
	  	<h5 class="w3-theme-light">Performance Data</h5><hr>
			<h6 ng-show="showTable">Weekly Performance(%) : {{finance.perfData.perfWeek}} 
		    	<img ng-show="finance.perfData.perfWeek < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12"/>
	    		<img ng-show="finance.perfData.perfWeek > 0" src="images/green-triangle.gif" title="Bullish" width="12"/><a></a>
	    	</h6>
			<h6 ng-show="showTable">Quarterly Performance(%) : {{finance.perfData.perfQuarter}} 
				<img ng-show="finance.perfData.perfQuarter < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12"/>
	    		<img ng-show="finance.perfData.perfQuarter > 0" src="images/green-triangle.gif" title="Bullish" width="12"/>
	    	</h6>
			<h6 ng-show="showTable">Yearly Performance(%) : {{finance.perfData.perfYear}} 
				<img ng-show="finance.perfData.perfYear < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12"/>
	    		<img ng-show="finance.perfData.perfYear > 0" src="images/green-triangle.gif" title="Bullish" width="12"/>
			</h6>
	  </div>
	</div>
	
</div>
</div>