package com.zy.service.impl;

import com.zy.common.utils.FtpUtil;
import com.zy.service.PictureService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PictureServiceImpl implements PictureService {

    //从properties取值
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER_NAME}")
    private String FTP_USER_NAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;


    @Override
    public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
        String originalFilename = uploadFile.getOriginalFilename();
        String newName = getNewName(originalFilename);

        InputStream inputStream=null;
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String imagePath=new DateTime().toString("/yyyy/MM/dd");
        //上传
        boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName, inputStream);
        HashMap<String, Object> hashMap = new HashMap<>();

        if(result){
            hashMap.put("error",0);
            hashMap.put("url", IMAGE_BASE_URL+imagePath+"/"+newName);
        }else{
            hashMap.put("error",1);
            hashMap.put("message", "不好意思,上传失败");
        }

        return hashMap ;
    }

    //
    private String getNewName(String oldName){
        String substring = oldName.substring(oldName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();

        return  uuid+substring;
    }


}
