package com.tradeanalyzer.main.data;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.tradeanalyzer.main.dao.StockData;

public class DatabaseConnectionImpl implements IDatabaseConnection {

	final Logger LOGGER = Logger.getLogger(DatabaseConnectionImpl.class.getName());

	@Override
	public ArrayList<StockData> getStocksList() {
		return null;
		// StockData sd;
		// List<StockData> list = new ArrayList<StockData>();
		// // JSONObject responseDetailsJson = new JSONObject();
		// // JSONArray jsonArray = new JSONArray();
		//
		// Map<String, String> stocksList = new HashMap<String, String>();
		// StringBuffer sb = new StringBuffer("{\"finData\":[");
		// // init connection object
		// Connection connection = null;
		// // init properties object
		// Properties properties = null;
		//
		// // create properties
		// if (properties == null) {
		// properties = new Properties();
		// properties.setProperty("user", DBUtil.USERNAME);
		// properties.setProperty("password", DBUtil.PASSWORD);
		// properties.setProperty("MaxPooledStatements", DBUtil.MAX_POOL);
		// }
		//
		// // connect database
		// if (connection == null) {
		// try {
		// Class.forName(DBUtil.DATABASE_DRIVER);
		// connection = DriverManager.getConnection(DBUtil.DATABASE_URL,
		// properties);
		// LOGGER.info(connection + "is the connection");
		// Statement stmt = connection.createStatement();
		// ResultSet rs = stmt.executeQuery("SELECT symbol, name FROM stock_info
		// order by symbol limit 1000");
		//
		// while (rs.next()) {
		// String symbol = rs.getString("symbol");
		// sb.append("\"" + symbol + "-");
		// String companyName = rs.getString("name");
		//
		// sb.append(companyName + "\"");
		// if (!rs.isLast())
		// sb.append(",");
		// stocksList.put(symbol, companyName);
		// sd = new StockData(rs.getString("name"), rs.getString("symbol"));
		// list.add(sd);
		// // JSONObject formDetailsJson = new JSONObject();
		// //
		// // formDetailsJson.put(rs.getString("symbol"),rs.getString("name"));
		// // jsonArray.add(formDetailsJson);
		//
		// }
		// ;
		// sb.append("]}");
		// LOGGER.info(sb.toString());
		// } catch (ClassNotFoundException | SQLException e) {
		// e.printStackTrace();
		// }
		// }
		// // responseDetailsJson.put("finData", jsonArray);//Here you can see
		// the
		// // data in json format
		//
		// if (connection != null) {
		// try {
		// connection.close();
		// connection = null;
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		// // return responseDetailsJson;
		// return sb;
	}

}
