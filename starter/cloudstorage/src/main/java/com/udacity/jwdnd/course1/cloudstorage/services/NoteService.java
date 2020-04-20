package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.repositories.NoteRepository;
import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Boolean isNoteExists(String id, String userid) throws Exception {
        try {
            Note note = noteRepository.findById(id, userid);
            if (note == null) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }  catch (Exception e) {
            throw e;
        }
    }

    public List<Note> getAllNotes(String userid) throws Exception {
        try {
            List<Note> notes = noteRepository.get(userid);
            return notes;
        }  catch (Exception e) {
            throw e;
        }
    }

    public void insertNote(Note note, String userid) {
        try {
            noteRepository.insert(note, userid);
        }  catch (Exception e) {
            throw e;
        }
    }

    public void updateNoteById(Note note, String userid) {
        try {
            noteRepository.updateById(note, userid);
        }  catch (Exception e) {
            throw e;
        }
    }

    public void deleteNoteById(String id, String userid) {
        try {
            noteRepository.deleteById(id, userid);
        }  catch (Exception e) {
            throw e;
        }
    }
}