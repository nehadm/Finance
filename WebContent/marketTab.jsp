<div class="w3-container tab" id="marketTab" ng-controller="marketIndicesController">
  <hr>
  <div class="w3-center">
    <h4>Market Indexes</h4>	
  </div>
<div class="w3-responsive w3-card-4">

<table class="w3-table w3-striped w3-bordered">
<thead>
<tr class="w3-theme">
  <th>Index Name</th>
  <th>Symbol</th>
  <th>Last Value</th>
  <th>Percent Change</th>
</tr>
</thead>
<tbody>
	  <tr ng-repeat="x in indexes">
	    <td ng-if="$odd" ng-class="w3-theme-light">{{ x.indexName }}</td>
	    <td ng-if="$odd" ng-class="w3-theme-light">{{ x.symbol }}</td>
	    <td ng-if="$odd" ng-class="w3-theme-light">{{ x.indexLastValue }}</td>
	    <td ng-if="$odd" ng-class="w3-theme-light">
	    	{{ x.percentChange }} %
	    	<img ng-show="x.percentChange < 0" src="images/redtriangle.jpg" title="Bearish" width="16"/>
	    	<img ng-show="x.percentChange > 0" src="images/green-triangle.gif" title="Bullish" width="16"/>
	    </td>
	    
	    <td ng-if="$even">{{ x.indexName }}</td>
	    <td ng-if="$even">{{ x.symbol }}</td>
	    <td ng-if="$even">{{ x.indexLastValue }}</td>
	    <td ng-if="$even">
	    	{{ x.percentChange }} %
	    	<img ng-show="x.percentChange < 0" src="images/redtriangle.jpg" title="Bearish" width="16"/>
	    	<img ng-show="x.percentChange > 0" src="images/green-triangle.gif" title="Bullish" width="16"/>
	    </td>
	  </tr>

</tbody>
</table>
</div>

<hr>
<br/>
</div>