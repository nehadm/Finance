<!DOCTYPE html>
<html lang="en"  data-ng-app="myApp" data-ng-controller="financeController" data-ng-init="showTable=false;getStockData('FCX')">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="SHORTCUT ICON" href="images/fin.png">
    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap less CSS -->
    <link href="bower_components/bootstrap/less/component-animations.less" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/custom.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="bower_components/morrisjs/morris.css" rel="stylesheet">

	<link href="dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.min.js"></script>
    <script src="js/financeController.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
    <script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });
    </script>
    <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
    <title>{{finance.ticker | uppercase}} - Welcome to Trade Analyzer!</title>
</head>

<body>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0px;">
            <div class="navbar-header">
                <a class="navbar-brand titleFont" href="help_doc.html">Trade Analyzer</a>
<!--             	<ul class="nav navbar-top-links navbar-right"> -->
                    <div class="input-group custom-search-form" style="float:right; width: 25%; margin-left: 50%;padding-top: 7px;">
                    	<input type="text" class="form-control" placeholder="Search for ticker or company name" id="tickerInput" data-ng-model="selected" required="required" data-ng-minlength="1" data-ng-maxlength="5">
                    	<span class="input-group-btn" >
                        	<button class="btn btn-default" type="button" id="buttonSearch" data-ng-click="getStockData($('#tickerInput'))">
                        		<i class="fa fa-search"></i>
                    		</button>
                		</span>
<!--                     	<select id="tickerSelected" class="form-control" data-ng-model="eg" data-ng-change="getStockData(eg.split('-')[0])"> -->
<!--   							<option data-ng-repeat="x in stockList | filter: selected"  > -->
<!--                             	{{ x }} -->
<!--                         	</option> -->
<!--                     	</select> -->
                        <div class="table-responsive">
                            <table class="table table-hover autoCompleteSuggestionsTable" id="tickerSelected">
                                <tbody>
	                    			<tr data-ng-repeat="x in stockList | filter: selected" data-ng-click="getStockData(x.split('-')[0])">
	                    			 	<td class="ticker"> {{x.split('-')[0]}} </td>
	                    			 	<td class="companyName"> {{x.split('-')[1]}} </td>
                    			 	</tr>
                    			</tbody>
                    		</table>
                    	</div>
					</div>
<!--             	</ul> -->
            <br/>
<!--             <span class="clockLabel navbar-default">Local time: {{dateLocal | date:'MMM dd yyyy, hh:mm'}}</span> -->
			<span class="clockLabel navbar-default">{{dateNY}}</span>
			</div>
		</nav>

            <div class="indexStrip">
<!--             	<span>Major market indexes today</span><br/> -->
	            <a class="tooltip-demo"
	            data-ng-repeat="x in marketIndices" id="news_{{x.symbol}}"
	            target="_blank" data-toggle="tooltip" data-placement="left" title="{{x.indexName}}"
	            data-ng-class="(x.percentChange < 0) ? 'marketIndexRed' : 'marketIndexGreen'">
					{{ x.symbol }}
					<span style="font-size:small;">({{ x.indexLastValue }}) ({{ x.percentChange }} %)&nbsp;</span>
<%-- 			    	<img data-ng-show="x.percentChange < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="16"/> --%>
<!-- 			    	<img data-ng-show="x.percentChange > 0" src="images/green-triangle.gif" title="Bullish" width="16"/>&nbsp;&nbsp; -->
	            </a>
			</div>
			<hr style="border: 5; margin-top: 0px; border-top: 1px solid #eee;"/>
        <br/>
        <br/>

        <div id="page-wrapper" style="width: 100%; margin: -60px 0 0 0; height: auto;overflow: auto;">
            <div class="row">
                <div class="col-lg-12" data-ng-show="showTable && finance != null" style="padding-left: 0px;">
                    <h3>{{finance.companyName}}, {{finance.ticker | uppercase}} ({{finance.stockPrice | currency}})</h3>
<!--                  Button trigger modal -->
<!--                  <button class="alert-link" data-toggle="modal" data-target="#myModal"> -->
<!--                      View Chart -->
<!--                  </button> -->

                    <h5>Industry - {{finance.industry}} , Sector - {{finance.sector}} </h5>
                    <h6 data-ng-show="showTable">52 Week Price Range : {{finance.perfData.tradingRange52Week}}</h6>
                    <h6 data-ng-show="showTable">Previous Close : {{finance.prevClose}}</h6>

                    <hr/>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="panel-body">
            <img id="spinner" src="images/spinner.gif" class="spinner" style="display:none"></img>
            <div class="errorText" data-ng-show="showError">
            	{{ errorText }}
            </div>
            <div style="float:left; width: 20%;" class="row" data-ng-show="showTable && finance != null">
                <div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            General Data
                        </div>
                        <div class="panel-body">
<!--                             <p>Ticker : {{ finance.ticker | uppercase}}</p> -->
                            <p style="color: red;">ATR(N):{{ finance.atr }}</p>
                            <p>Short Float : {{ finance.shortFloat }} %</p>
                            <p>Target Price : {{ finance.targetPrice |currency}}</p>
                            <p>Earnings Call : {{ finance.earningsCall }}</p>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
<!--             </div> -->
<!--             <div class="row" data-ng-show="showTable && finance != null"> -->
<!--             </div> -->
<!--             <div class="row" data-ng-show="showTable && finance != null"> -->
                <div data-ng-show="showTable && finance.fundaData != null">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Fundamental Data
                        </div>
                        <div class="panel-body">
                            <p>P/E Ratio : {{ finance.fundaData.priceToEarnings }}</p>
                            <p>Forward P/E Ratio : {{ finance.fundaData.forwardPriceToEarnings }}</p>
                            <p>Earnings Per Share : {{ finance.fundaData.earningsPerShare | currency }}</p>
                            <p>ROI(%) : {{ finance.fundaData.roi }}</p>
                            <p>Market Cap : {{ finance.fundaData.marketCap }}</p>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
<!-- 			</div> -->
<!-- 			 <div class="row" data-ng-show="showTable && finance != null"> -->
                <div>
                    <div class="panel panel-primary" data-ng-show="showTable">
                        <div class="panel-heading">
                            Performance Data
                        </div>
                        <div class="panel-body">
                            <p>Weekly Performance(%) : {{finance.perfData.perfWeek}}
                                <img data-ng-src="{{ finance.perfData.perfWeek < 0 ? 'images/270px-Red_triangle.svg.png' : 'images/green-triangle.gif'}}" width="12" />
                                <a></a>
                            </p>
                            <p>Monthly Performance(%) : {{finance.perfData.perfMonthly}}
                                <img data-ng-src="{{ finance.perfData.perfMonthly < 0 ? 'images/270px-Red_triangle.svg.png' : 'images/green-triangle.gif'}}" width="12" />
                            </p>
                            <p>Quarterly Performance(%) : {{finance.perfData.perfQuarter}}
                                <img data-ng-src="{{ finance.perfData.perfQuarter < 0 ? 'images/270px-Red_triangle.svg.png' : 'images/green-triangle.gif' }}" width="12" />
                            </p>
                            <p>Yearly Performance(%) : {{finance.perfData.perfYear}}
                                <img data-ng-src="{{ finance.perfData.perfYear < 0 ? 'images/270px-Red_triangle.svg.png' : 'images/green-triangle.gif' }}" width="12" />
                            </p>
                            <p>YTD Performance(%) : {{finance.perfData.perfYTD}}
                                <img data-ng-src="{{ finance.perfData.perfYTD < 0 ? 'images/270px-Red_triangle.svg.png' : 'images/green-triangle.gif' }}" width="12" />
                            </p>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
                <div data-ng-show="showTable && finance.divData != null">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Dividend Data
                        </div>
                        <div class="panel-body">
                            <p>Ex Div Date : {{ finance.divData.exDivDate}}</p>
                            <p>Pay Date : {{ finance.divData.payDate}}</p>
                            <p>Annual Dividend : {{ finance.divData.annualDividend | currency }}</p>
                            <p>Div Yield(%) : {{ finance.divData.divYield }}</p>
                            <p>Years Paying : {{ finance.divData.yearsPaying }}</p>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>

             </div>
             <div style="width:	40%; float: left; height: auto" data-ng-show="showTable && finance != null">
                  <div class="panel panel-primary" style="margin-left: 3%">
                      <div class="panel-heading">
                          Plan Trades.
                      </div>
                      <div class="panel-body">
                     	<div style="float:left; width:40%;">
	                        <div style="float: left; border-color: white;" class="form-control " >Enter account size</div>
	                        <div style="float: left;  border-color: white; clear:left" class="form-control " >% riskage per trade</div>
	                        <div style="float: left; border-color: white; clear:left" class="form-control " >
	                        	{{ accountSizeOption }} % of account size:
	                        </div>
	                        <div style="float: left; border-color: white; clear:left" class="form-control " >
								No of shares to buy with {{ accountSizeOption }} % risk
							</div>
                        	<div style="float: left; border-color: white; clear:left" class="form-control " data-ng-show="finance.divData != null">
								Receivable dividend (per year)
                        	</div>
                        </div>

                        <div style="float: right; width:60%">
                        	<div style="float: left"><input class="form-control " type="text" data-ng-model="accountSize" data-ng-show="showTable"/></div>
                        	<div style="float: left; clear:left"><select class="form-control " data-ng-model="accountSizeOption">
								<option value="2" selected="selected">2%</option>
								<option value="3">3%</option>
								<option value="5">5%</option>
								<option value="10">10%</option>
								<option value="20">20%</option>
								<option value="50">50%</option>
								<option value="90">90%</option>
                        		</select>
                        	</div>
                        	<div style="float: left; clear:left;  border-color: white; " class="form-control ">
                        	<input type="text" style="display:none;" value="{{ accountSizeOption/100 * accountSize}}" data-ng-model="portionAccountSize"/>
                        		{{ accountSizeOption/100 * accountSize | currency }}
                        	</div>

                        	<div style="float: left; clear:left; border-color: white; " class="form-control ">

                        		{{ (accountSizeOption/100 * accountSize) / finance.stockPrice | number:0}}
                        	</div>
                        	<div style="float: left; clear:left; border-color: white; " class="form-control " data-ng-show="finance.divData != null">
                        		{{ ((accountSizeOption/100 * accountSize ) / finance.stockPrice) * finance.divData.annualDividend  | currency}} &nbsp;&nbsp;&nbsp;
                        		({{ finance.divData.divYield }} % of {{ (accountSizeOption/100 * accountSize ) }})
                        	</div>
                        </div>
                        <a style="font-size:smaller" href="login.jsp" >Click to see more.</a>
                      </div><br/>

                  </div>
<!--                   <div class="panel panel-primary" style="height: 900px; margin-left: 3%"> -->
<!--                       <div class="panel-heading"> -->
<!--                           Chart for {{ finance.ticker | uppercase}} (Provided by Trading View) -->
<!--                       </div> -->
<!--                       <p style="display:none" id="hiddenTicker">{{ finance.ticker }} </p> -->
<!--                       <div id="tradingWidget" class="panel-body"> -->
<!-- <!-- 						<script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script> -->
<!-- 						<script type="text/javascript">  -->
<!--   						</script> -->
<!-- 				  	 </div>                   -->
<!--              	</div> -->
             </div>
             <div >
             <div style="width:	10%; margin-left: 1%; float: left;" class="panel panel-primary" data-ng-show="showTable">
                <div class="panel-heading">
                   Watchlists
                </div>
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                          <li>
                              <a href="#">Dow 30 <span class="fa arrow"></span></a>
                              <ul class="nav nav-third-level">
                                  <li>
                                  	<a style="text-decoration: none !important; clear:left;float: left; cursor: pointer; font-size: smaller;" data-ng-repeat="x in dow30List" id="dow30_{{x}}"
       								 target="_blank" data-placement="left" title="{{x}}" data-ng-click="getStockData('{{ x }}')">
										{{ x }}
        							</a>
                                  </li>
                              </ul>
                          </li>
                          <li>
                              <a href="#">SPX 100<span class="fa arrow"></span></a>
                              <ul class="nav nav-third-level">
                                  <li>
                                    <a style="text-decoration: none !important; clear:left;float: left; cursor: pointer; font-size: smaller;" data-ng-repeat="x in spx100List" id="spx100_{{x}}"
      						    target="_blank" data-placement="left" title="{{ x | uppercase }}" data-ng-click="getStockData('{{ x }}')">
										{{ x }}
        							</a>
                                  </li>
                              </ul>
                          </li>
                      </ul>
                 </div>
	         </div>
            </div>
        </div>
        </div>
</body>