package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dac.AutoCompleteStocksDAC;
import com.google.gson.JsonObject;

public class AutoCompleteServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public AutoCompleteServlet() {
                super();
        }

        protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        	AutoCompleteStocksDAC dac = new AutoCompleteStocksDAC();
        	StringBuffer sb = dac.getStocksList();

            response.setContentType("application/json");
            response.getWriter().write(sb.toString());
        }
}
