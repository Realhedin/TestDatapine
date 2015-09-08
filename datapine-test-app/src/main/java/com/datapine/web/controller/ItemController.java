package com.datapine.web.controller;

import com.datapine.dao.ItemDAO;
import com.datapine.dao.UserDAO;
import com.datapine.domain.Item;
import com.datapine.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by Dmitrii on 9/8/15.
 */
@Controller
@RequestMapping("/items")
public class ItemController {


    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView listItems(@RequestParam(value = "id", required = false) String email) {
        if (StringUtils.isEmpty(email)) {
            return new ModelAndView("redirect:/users/");
        }
        User user = userDAO.findByEmail(email);
        ModelAndView model = new ModelAndView("itemlist");
        List<Item> list = itemDAO.findAllOrderById(user.getId());
        model.addObject("itemsList", list);
        return model;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addItem(@RequestParam(value = "id", required = true) String email) {
        ModelAndView model = new ModelAndView("addItem");
        model.addObject("item",new Item());
        return model;
    }

    @RequestMapping(value = "/add/{email}", method = RequestMethod.POST)
    public ModelAndView addItem(@PathVariable("email") String email ,@ModelAttribute("item") Item item) {
        item.setUser(userDAO.findByEmail(email));
        if (item != null && item.getUser() != null) {
            itemDAO.save(item);
        }
        return new ModelAndView("redirect:/items/?id="+item.getUser().getEmail()+"");
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam(value = "id", required = true) Long id,
                                   @RequestParam(value = "userId", required = true) String email) {
        itemDAO.delete(id);
        return new ModelAndView("redirect:/items/?id="+email+"");
    }
}
