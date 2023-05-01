package com.kaivikki.loans.context;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class RequestContextFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RequestContextFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		RequestContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(RequestContext.CORRELATION_ID));
		logger.debug("Loans Service incoming Correlation id: {}", RequestContextHolder.getContext().getCorrelationId());
		filterChain.doFilter(httpServletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void destroy() {
		//
	}
}
