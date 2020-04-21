package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String signup(@ModelAttribute("SpringWeb") User user, Model model) {
        try {
            if(user == null) {
                model.addAttribute("invalidDetails",true);
                return "signup";
            }

            if(userService.isUserExists(user.getUsername())){
                model.addAttribute("isUserExists",true);
                return "signup";
            }

            userService.insertUser(user);
            model.addAttribute("signup",true);
            return "signup";

        } catch (Exception e) {
            model.addAttribute("error",true);
            return "signup";
        }
    }
}