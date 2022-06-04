package com.br.lis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("========== Filter Execution ===========");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String url = (String)StringUtils.defaultIfEmpty(req.getRequestURL().toString(), "Not Found URL");
		String queryString = StringUtils.defaultString(req.getQueryString(), "");
		logger.info("=============== URL : {} ================", url);
		logger.info("=========== QueryString : {} =============", queryString);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("========== Filter End ===========");
	}

}
