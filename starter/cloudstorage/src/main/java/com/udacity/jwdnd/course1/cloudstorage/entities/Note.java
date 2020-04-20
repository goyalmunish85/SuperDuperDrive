package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class Note {
    public String noteid;
    public String notetitle;
    public String notedescription;

    public String getNoteId() {
        return noteid;
    }
}