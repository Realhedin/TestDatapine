package com.datapine.service;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Realization of PostSuccessfulAuthenticationHandler to enhance logging with Log4J and AspectJ
 *
 * Created by Dmitrii on 9/13/15.
 */
public class PostSuccessfulAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final static Logger logger = Logger.getLogger(PostSuccessfulAuthenticationHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
        if (authentication.getName().equals("admin@admin.com")) {
            logger.info("Log4j: User: "+authentication.getName()+" logged as ADMINISTRATOR");
        }
        logger.info("Log4j: User: "+authentication.getName()+ " successfully logged");
    }
}