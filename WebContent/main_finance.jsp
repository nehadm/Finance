<!DOCTYPE html>
<html lang="en"  data-ng-app="myApp" data-ng-controller="financeController" data-ng-init="showTable=false;getStockData('AAPL')">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="SHORTCUT ICON" href="images/fin.png">
    <title>{{finance.ticker}} - Welcome to your Stock Analyzer!</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

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

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="js/financeController.js"></script>
    <script>
    // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });
    </script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <a class="navbar-brand" href="help_doc.html">Stock Analyzer</a>
<!--             	<ul class="nav navbar-top-links navbar-right"> -->
                    <div class="input-group custom-search-form" style="float:right; width: 25%; margin-left: 50%;padding-top: 7px;">
                    	<input type="text" class="form-control" placeholder="Search..." id="tickerInput" data-ng-model="selected" data-ng-pattern="regex" required="" data-ng-minlength="1" data-ng-maxlength="5">
                    	<span class="input-group-btn" >
                        	<button class="btn btn-default" type="button" id="buttonSearch">
                        		<i class="fa fa-search"></i>
                    		</button>
                		</span>
                		
                    	<select id="tickerSelected" class="form-control" data-ng-model="eg" data-ng-change="getStockData(eg.split('-')[0])">
                        	<option data-ng-repeat="x in stockList | filter: selected">
                            	{{ x }}
                        	</option>
                    	</select>
					</div>
<!--             	</ul> -->
            <br/>
            <span style="float:left; clear: left; padding-left: 13px;">{{date | date:'MMM dd yyyy, hh:mm:ss a'}}</span>
			<span data-ng-show="{{date}}"></span>

			</div>
		</nav>

            <div>
	            <a style="text-decoration: none !important;" class="tooltip-demo" data-ng-repeat="x in marketIndices" id="news_{{x.symbol}}" 
	            target="_blank" data-toggle="tooltip" data-placement="left" title="{{x.indexName}}">
					{{ x.symbol }} ({{ x.indexLastValue }}) ({{ x.percentChange }} %)&nbsp;
			    	<img data-ng-show="x.percentChange < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="16"/>
			    	<img data-ng-show="x.percentChange > 0" src="images/green-triangle.gif" title="Bullish" width="16"/>&nbsp;&nbsp;
	            </a>
		    	<div>&nbsp; &nbsp; &nbsp;</div>
			</div>
			<hr/>
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">

                        <li>
                            <a href="tables.html"><i class="fa fa-table fa-fw"></i> Symbol Check</a>
                        </li>
                        <li>
                            <a href="forms.html"><i class="fa fa-edit fa-fw"></i> ETFs</a>
                        </li>
                        <li>
                            <a href="tables.html"><i class="fa fa-table fa-fw"></i> Currencies</a>
                        </li>
                        <li>
                            <a href="trade_plan.jsp"><i class="fa fa-edit fa-fw"></i> Trade Plan</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->

        <br/>
        <br/>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12" data-ng-show="showTable && finance != null">
                    <h2>{{finance.companyName}}, {{finance.ticker}} ({{finance.stockPrice | currency}})</h2>
                    <h5>Industry - {{finance.industry}} , Sector - {{finance.sector}} </h5>
                    <h6 data-ng-show="showTable">52 Week Price Range : {{finance.perfData.tradingRange52Week}}</h6>
                    <h6 data-ng-show="showTable">Previous Close : {{finance.prevClose}}</h6>

                    <hr/>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <img id="spinner" src="images/spinner.gif" style="display:none"></img>
            <div class="row" data-ng-show="showTable && finance != null">
                <div class="col-lg-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            General Data
                        </div>
                        <div class="panel-body">
                            <p>Ticker : {{ finance.ticker | uppercase}}</p>
                            <p>ATR(N) : {{ finance.atr }}</p>
                            <p>Short Float : {{ finance.shortFloat }} %</p>
                            <p>Target Price : {{ finance.targetPrice |currency}}</p>
                            <p>Earnings Call : {{ finance.earningsCall }}</p>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
<!--             </div> -->
<!--             <div class="row" data-ng-show="showTable && finance != null"> -->
                <div class="col-lg-4" data-ng-show="showTable && finance.divData != null">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Dividend Data
                        </div>
                        <div class="panel-body">
                            <p>Ex Div Date : {{ finance.divData.exDivDate | date: 'MMM dd yyyy' }}</p>
                            <p>Pay Date : {{ finance.divData.payDate }}</p>
                            <p>Annual Dividend : {{ finance.divData.annualDividend | currency }}</p>
                            <p>Div Yield(%) : {{ finance.divData.divYield }}</p>
                            <p>Years Paying : {{ finance.divData.yearsPaying }}</p>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
<!--             </div> -->
<!--             <div class="row" data-ng-show="showTable && finance != null"> -->
                <div class="col-lg-4" data-ng-show="showTable && finance.fundaData != null">
                    <div class="panel panel-info">
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
                <div class="col-lg-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Performance Data
                        </div>
                        <div class="panel-body">
                            <p data-ng-show="showTable">Weekly Performance(%) : {{finance.perfData.perfWeek}}
                                <img data-ng-show="finance.perfData.perfWeek < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12" />
                                <img data-ng-show="finance.perfData.perfWeek > 0" src="images/green-triangle.gif" title="Bullish" width="12" />
                                <a></a>
                            </p>
                            <p data-ng-show="showTable">Monthly Performance(%) : {{finance.perfData.perfMonthly}}
                                <img data-ng-show="finance.perfData.perfMonthly < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12" />
                                <img data-ng-show="finance.perfData.perfMonthly > 0" src="images/green-triangle.gif" title="Bullish" width="12" />
                            </p>
                            <p data-ng-show="showTable">Quarterly Performance(%) : {{finance.perfData.perfQuarter}}
                                <img data-ng-show="finance.perfData.perfQuarter < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12" />
                                <img data-ng-show="finance.perfData.perfQuarter > 0" src="images/green-triangle.gif" title="Bullish" width="12" />
                            </p>
                            <p data-ng-show="showTable">Yearly Performance(%) : {{finance.perfData.perfYear}}
                                <img data-ng-show="finance.perfData.perfYear < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12" />
                                <img data-ng-show="finance.perfData.perfYear > 0" src="images/green-triangle.gif" title="Bullish" width="12" />
                            </p>
                            <p data-ng-show="showTable">YTD Performance(%) : {{finance.perfData.perfYTD}}
                                <img data-ng-show="finance.perfData.perfYTD < 0" src="images/270px-Red_triangle.svg.png" title="Bearish" width="12" />
                                <img data-ng-show="finance.perfData.perfYTD > 0" src="images/green-triangle.gif" title="Bullish" width="12" />
                            </p>                            
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
             </div>
             <div class="row" data-ng-show="showTable && finance != null">
                <div class="col-lg-4" data-ng-show="showTable && finance != null">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Trade Plan
                        </div>
                        <div class="panel-body">
                        	Enter your account size
<!--                                     	 <th>2% of your account</th> -->
<!--                                    		 <th>5% of your account</th> -->
<!--                                     	 <th>10% of your account</th> -->
<!--                                     	 <th>25% of your account</th>                                         -->
                        	<input type="text" data-ng-model="accountSize" data-ng-show="showTable"/><br/>
                        	<select data-ng-model="accountSizeOption">
  								<option value="2">2%</option>
  								<option value="3">3%</option>
  								<option value="5">5%</option>
  								<option value="10">10%</option>
  								<option value="20">20%</option>
							</select>
  							<p>For {{accountSizeOption}} , the amount of money from your total account that should be invested is :
  							{{accountSizeOption/100 * accountSize}}</p>
  							<br/>
  							<p>2N = {{ finance.atr * 2}}</p> 
  							<p>3N = {{ finance.atr * 3}}</p>
  							<p>5N = {{ finance.atr * 5}}</p>
  							
  							
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </div>
				</div>
            </div>
        </div>
</body>