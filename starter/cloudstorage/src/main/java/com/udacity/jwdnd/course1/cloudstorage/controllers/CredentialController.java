package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/credential")
    public String createOrUpdateCredential(Credential credential, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userid = auth.getName();
            String credentialid = credential.getCredentialId();

            if ((credentialid == null) || credentialid.isEmpty()) {
                credentialService.insertCredential(credential, userid);
            } else {
                credentialService.updateCredentialById(credential, userid);
            }

            model.addAttribute("success",true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error",true);
            return "result";
        }
    }

    @GetMapping("/credential/delete")
    public String deleteCredential(@RequestParam("id") String id, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userid = auth.getName();
            if (credentialService.isCredentialExists(id, userid)) {
                credentialService.deleteCredentialById(id, userid);
                model.addAttribute("success",true);
                return "result";
            }
            model.addAttribute("error",true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error",true);
            return "result";
        }
    }
}