package com.datapine.web.controller;

import com.datapine.dao.UserDAO;
import com.datapine.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
////    @ResponseBody
//    public List<User> listUsers() {
//        //return userDAO.findAllOrderById();
//        User u = new User("t","t");
//        return Arrays.asList(u);
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView listUsers() {

        ModelAndView model = new ModelAndView("userlist");
        List<User> list = userDAO.findAllOrderById();
        model.addObject("usersList",list);
        return model;
	}

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView model = new ModelAndView("addUser");
        model.addObject("user",new User());
        return model;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user) {
        if (user != null) {
            userDAO.save(user);
        }
        return new ModelAndView("addedUser");
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showUser(@RequestParam(value = "id", required = true) Long id) {
        ModelAndView model = new ModelAndView("showUser");
        model.addObject("user",userDAO.findById(id));
        return model;
	}

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestParam(value = "id", required = true) Long id) {
        ModelAndView model = new ModelAndView("editUser");
        model.addObject("user",userDAO.findById(id));
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("user")User user,
                                   @RequestParam(value = "id", required = true) Long id) {
        ModelAndView model = new ModelAndView("editedUser");
        user.setId(id);
        userDAO.update(user);
        model.addObject("id",id);
        return model;
	}

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(@RequestParam(value = "id", required = true) Long id) {
        ModelAndView model = new ModelAndView("deletedUser");
//        User u = userDAO.findById(id);
        User u = userDAO.getReference(id);
        model.addObject("id",id);
        userDAO.delete(u);
        return model;
	}

}
