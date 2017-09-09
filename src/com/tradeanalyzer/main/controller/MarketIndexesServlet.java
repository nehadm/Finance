package com.tradeanalyzer.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tradeanalyzer.main.dao.IndexData;
import com.tradeanalyzer.main.service.IFinanceService;
import com.tradeanalyzer.main.service.impl.FinanceServiceImpl;

public class MarketIndexesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger LOGGER = Logger.getLogger(MarketIndexesServlet.class.getName());

	public MarketIndexesServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<IndexData> indexesData = new ArrayList<IndexData>();
		IFinanceService finServ = new FinanceServiceImpl();
		indexesData = finServ.getAllMarketIndexes();

		String json = new Gson().toJson(indexesData);
		response.setContentType("application/json");
		response.getWriter().write(json);

	}
}