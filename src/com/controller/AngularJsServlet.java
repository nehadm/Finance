package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.StockData;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.service.IFinanceService;
import com.service.FinanceServiceImpl;

public class AngularJsServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public AngularJsServlet() {
                super();
        }

        protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
                //FinanceData finData = new FinanceData();  
        	
        	StringBuffer sb = new StringBuffer();
        	
        	try {
        		BufferedReader br = request.getReader();
        		String line = null;
        		
        		if((line = br.readLine()) != null) {
        			sb.append(line);
        		}
        		
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        	JSONParser parser = new JSONParser();
        	//JsonObject finance = null;
        	JSONObject finance = null;
            try
            {
            	finance = (JSONObject) parser.parse(sb.toString());
            } catch (ParseException e) { 
            	e.printStackTrace(); 
            }
         
            String tickerSymbol = (String) finance.get("ticker");
            
            IFinanceService finServ = new FinanceServiceImpl();
            StockData finData = finServ.getFinDataForSymbol(tickerSymbol);
            
            
            String json = new Gson().toJson(finData);
            System.out.println("finance data json:"+json);
            response.setContentType("application/json");
            response.getWriter().write(json);

        }
}
