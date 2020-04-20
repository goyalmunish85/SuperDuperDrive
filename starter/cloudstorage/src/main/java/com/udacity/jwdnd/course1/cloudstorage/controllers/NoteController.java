package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public String createOrUpdateNote(Note note, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userid = auth.getName();
            String noteid = note.getNoteId();

            if ((noteid == null) || noteid.isEmpty()) {
                noteService.insertNote(note, userid);
            } else {
                noteService.updateNoteById(note, userid);
            }

            model.addAttribute("success",true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error",true);
            return "result";
        }
    }

    @GetMapping("/note/delete")
    public String deleteNote(@RequestParam("id") String id, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userid = auth.getName();
            if (noteService.isNoteExists(id, userid)) {
                noteService.deleteNoteById(id, userid);
                model.addAttribute("success", true);
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