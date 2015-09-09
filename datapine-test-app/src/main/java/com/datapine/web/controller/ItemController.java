package com.datapine.web.controller;

import com.datapine.dao.ItemDAO;
import com.datapine.dao.UserDAO;
import com.datapine.domain.Item;
import com.datapine.domain.User;
import com.datapine.domain.dto.UserDTO;
import com.datapine.service.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView listItems(Principal principal) {
        User user = userDAO.findByEmail(principal.getName());
        ModelAndView model = new ModelAndView("itemlist");
        List<Item> list = itemDAO.findAllOrderById(user.getId());
        model.addObject("itemsList", list);
        return model;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addItem() {
        ModelAndView model = new ModelAndView("addItem");
        model.addObject("item",new Item());
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addItem(@ModelAttribute("item") Item item, Principal principal) {
        item.setUser(userDAO.findByEmail(principal.getName()));
        if (item != null && item.getUser() != null) {
            itemDAO.save(item);
        }
        return new ModelAndView("redirect:/items/");
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteItem(@RequestParam(value = "id", required = true) Long id) {
        itemDAO.delete(id);
        return new ModelAndView("redirect:/items/");
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public ModelAndView updateUserDetails(Principal principal) {
        ModelAndView model = new ModelAndView("passUpdate");
        User u = userDAO.findByEmail(principal.getName());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(u.getId());
        userDTO.setEmail(u.getEmail());
        userDTO.setOldPassword(u.getPassword());
        model.addObject("user",userDTO);
        return model;
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public ModelAndView updateUserDetails(@ModelAttribute("user") UserDTO userDTO) {
        userService.updatePassword(userDTO.getId(),userDTO.getOldPassword(),userDTO.getNewPassword());
        return new ModelAndView("redirect:/items/");
    }
}
