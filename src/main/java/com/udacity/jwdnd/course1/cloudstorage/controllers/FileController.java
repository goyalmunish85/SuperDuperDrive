package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/file")
    public String saveOrUpdateFile(MultipartFile fileUpload, Model model) throws IOException {
        try {

            if (fileUpload.isEmpty()) {
                model.addAttribute("error",true);
                return "result.html";
            }

           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           String userid = auth.getName();
            File file = new File();
            file.filename = fileUpload.getOriginalFilename();
            file.contenttype = fileUpload.getContentType();
            file.filesize = String.valueOf(fileUpload.getSize());
            file.filedata = fileUpload.getBytes();
            if (fileService.isFileExistsByFileName(file.filename, userid)) {
                model.addAttribute("customError",true);
                model.addAttribute("value", "File already exists.");
                return "result";
            }
            fileService.insertFile(file, userid);
            model.addAttribute("success",true);
           return "result";
       } catch (Exception e) {
           model.addAttribute("error",true);
           return "result";
       }
    }


    @GetMapping("file/download")
    @ResponseBody
    public ResponseEntity download(@RequestParam("id") String id) {
    try {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userid = auth.getName();
        File file = fileService.getFileById(id, userid);
        ByteArrayResource resource = new ByteArrayResource(file.filedata);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.filename)
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(Long.parseLong(file.filesize))
                .body(resource);
    } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }



    @GetMapping("/file/delete")
    public String deleteFile(@RequestParam("id") String id, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userid = auth.getName();
            if (fileService.isFileExists(id, userid)) {
                fileService.deleteFileById(id, userid);
                model.addAttribute("success", true);
                return "result";
            }
            model.addAttribute("error", true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", true);
            return "result";
        }
    }
}