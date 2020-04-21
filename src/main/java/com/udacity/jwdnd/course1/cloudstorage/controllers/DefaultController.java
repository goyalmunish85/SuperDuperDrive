package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class DefaultController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @Autowired
    NoteService noteService;

    @Autowired
    CredentialService credentialService;

    @Autowired
    FileService fileService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = { "/", "/home" })
    public String getHomePage(Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id = auth.getName();

//        credentialService.getAllCredentials(id);

        model.addAttribute("notes", noteService.getAllNotes(id));
        model.addAttribute("credentials", credentialService.getAllCredentials(id));
        model.addAttribute("files", fileService.getAllFiles(id));

        return "home";
    }

}