package com.jasper.users.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class TOTPWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {
	private static final Logger logger = LoggerFactory.getLogger(TOTPWebAuthenticationDetailsSource.class);

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
		logger.debug("returning TOTPWebAuthenticationDetails");
		return new TOTPWebAuthenticationDetails(context);
	}

}
