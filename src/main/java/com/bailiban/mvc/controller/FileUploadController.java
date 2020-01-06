package com.bailiban.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class FileUploadController {

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(String fileName, MultipartFile uploadFile, HttpServletRequest request) {
        String fName = "";
        String originName = uploadFile.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        if (!StringUtils.isEmpty(fileName)) {
            fName = fileName + "_" + uuid + "." + ext;
        } else {
            fName = uuid + originName;
        }
        ServletContext context = request.getServletContext();
        String basePath = context.getRealPath("/upload");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File f = new File(basePath + "/" + date);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            uploadFile.transferTo(new File(f, fName));
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}
