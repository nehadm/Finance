package com.dac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.util.DBUtil;

public class AutoCompleteStocksDAC implements IAutoCompleteStockDAC {

	@Override
	public StringBuffer getStocksList() {
		Map<String, String> stocksList = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer("{\"finData\":[");
        // init connection object
        Connection connection=null;
        // init properties object
        Properties properties=null;

        // create properties
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", DBUtil.USERNAME);
            properties.setProperty("password", DBUtil.PASSWORD);
            properties.setProperty("MaxPooledStatements", DBUtil.MAX_POOL);
        }

        // connect database
        if (connection == null) {
            try {
                Class.forName(DBUtil.DATABASE_DRIVER);
                connection = DriverManager.getConnection(DBUtil.DATABASE_URL, properties);
                System.out.println(connection +"is the connection");
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT symbol, name FROM stock_info order by symbol desc limit 1500");
                
                while (rs.next()) {
                	String symbol = rs.getString("symbol");
                	sb.append("\""+symbol+"-");
                	String companyName = rs.getString("name");
                	
                    sb.append(companyName+"\"");
                    if(!rs.isLast()) sb.append(",");
                    stocksList.put(symbol,companyName);
                };
                sb.append("]}");
                System.out.println(sb.toString());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sb;
	}
	
}
