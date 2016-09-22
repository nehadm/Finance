package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.data.IndexData;
import com.google.gson.Gson;
import com.service.IFinanceService;
import com.service.FinanceServiceImpl;

public class MarketIndexesServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public MarketIndexesServlet() {
                super();
        }

        protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        	ArrayList<IndexData> indexesData = new ArrayList<IndexData>();
            IFinanceService finServ = new FinanceServiceImpl();
            indexesData = finServ.getAllMarketIndexes();

            String json = new Gson().toJson(indexesData);
            System.out.println("market indexes json:"+json);
            response.setContentType("application/json");
            response.getWriter().write(json);

        }
}