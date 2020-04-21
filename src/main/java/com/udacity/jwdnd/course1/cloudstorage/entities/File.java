package com.udacity.jwdnd.course1.cloudstorage.entities;

import lombok.Data;

@Data
public class File {
    public String fileId;
    public String filename;
    public String contenttype;
    public String filesize;
    public String fileSrc;
    public String userid;
    public byte[] filedata;
}