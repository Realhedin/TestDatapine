package com.datapine.service;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Realization of PostFailureAuthenticationHandler to enhance logging with Log4J and AspectJ
 *
 * Created by Dmitrii on 9/13/15.
 */
public class PostFailureAuthenticationHandler extends SimpleUrlAuthenticationFailureHandler {

    private final static Logger logger = Logger.getLogger(PostFailureAuthenticationHandler.class);

    private final String defaultFailureUrl = "login";


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("Log4j: Login of user: " +request.getParameter("j_username") + " failed.");
        logger.info("Log4j: Redirected to login page");
        response.sendRedirect(defaultFailureUrl);
    }
}
