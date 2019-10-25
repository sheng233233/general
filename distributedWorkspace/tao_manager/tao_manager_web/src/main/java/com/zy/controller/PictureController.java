package com.zy.controller;

import com.zy.service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureController {
    @Autowired
    PictureService ps;

    @ResponseBody
    @RequestMapping("/pic/upload")
    public Map UploadPic(MultipartFile uploadFile){
        return ps.uploadPicture(uploadFile);
    }


}
