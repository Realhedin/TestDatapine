package com.datapine.web.controller;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
import com.datapine.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController {

    private final static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;

	/*
	 * This controller will only work, if the Spring MVC settings are set
	 * completely and correctly (just as a hint).
	 */

	@RequestMapping("/")
	public ModelAndView showWelcome() {
		return new ModelAndView("index");
	}


    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/tryLogin", method = RequestMethod.POST)
    public ModelAndView tryLogin(@ModelAttribute("user") User user, BindingResult result) {
        logger.info("User: "+user.getEmail()+ " is trying to login into the system.");
        System.out.println("We are here");
        User loginUser = userDAO.findByEmail(user.getEmail());
        if (loginUser != null) {
            logger.info("User was found and accepted!");
            return new ModelAndView("index");
        }
        logger.info("User was not found. Redirected to login page");
        return new ModelAndView("login");
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user") User user) {
        if (user != null) {
            if (StringUtils.isNotEmpty(user.getEmail()) && StringUtils.isNotEmpty(user.getPassword())) {
              userService.register(user.getEmail(),user.getPassword());
            }
        }
        return new ModelAndView("index");
    }

}
