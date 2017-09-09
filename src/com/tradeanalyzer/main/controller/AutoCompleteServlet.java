package com.tradeanalyzer.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tradeanalyzer.main.dao.StockData;
import com.tradeanalyzer.main.service.IFinanceService;
import com.tradeanalyzer.main.service.impl.FinanceServiceImpl;

public class AutoCompleteServlet extends HttpServlet {
	final Logger LOGGER = Logger.getLogger(AutoCompleteServlet.class.getName());
	private static final long serialVersionUID = 1L;

	public AutoCompleteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IFinanceService finServ = new FinanceServiceImpl();
		ArrayList<StockData> stocksList = finServ.getStocksList();

		StringBuffer sb = new StringBuffer();

		response.setContentType("application/json");
		response.getWriter().write(sb.toString());
	}
}
