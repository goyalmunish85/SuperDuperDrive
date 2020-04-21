package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.repositories.FileRepository;
import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;


    public List<File> getAllFiles(String userid) throws Exception {
        try {
            List<File> files = fileRepository.get(userid);
            return files;
        }  catch (Exception e) {
            throw e;
        }
    }

     public Boolean isFileExists(String id, String userid) throws Exception {
         try {
             File file = fileRepository.findById(id, userid);
             if (file == null) {
                 return Boolean.FALSE;
             }
             return Boolean.TRUE;
         }  catch (Exception e) {
             throw e;
         }
     }

    public Boolean isFileExistsByFileName(String filename, String userid) {
        File file = fileRepository.findByFileName(filename, userid);
        if (file == null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public File getFileById(String id, String userid) throws Exception {
        try {
            File file = fileRepository.findById(id, userid);
            return file;
        }  catch (Exception e) {
            throw e;
        }
    }
    public void insertFile(File file, String userid) {
        try {
            fileRepository.insert(file, userid);
        }  catch (Exception e) {
            throw e;
        }
    }

     public void deleteFileById(String id, String userid) {
         try {
             fileRepository.deleteById(id, userid);
         }  catch (Exception e) {
             throw e;
         }
     }
}

