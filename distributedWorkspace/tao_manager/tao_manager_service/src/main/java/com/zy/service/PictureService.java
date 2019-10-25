package com.zy.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PictureService {

    //上传图片
    public Map<String,Object> uploadPicture(MultipartFile uploadFile);

}
