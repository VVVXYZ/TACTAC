package com.trio.breakFast.service.impl;


import com.trio.breakFast.service.FTPffUpAndDownService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ftp.FTPfUpAndDownUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


/**
 * Created by ienovo on 2016/8/11.
 */
@Service
public class FTPffUpAndDownServiceImpl implements FTPffUpAndDownService {

    /*
    *
    * 两个功能
    * 1.上传图片
    * 2.下载图片
    * */


    //上传图片
    @Override
    public boolean FileUp(String fileName, String file) {

        String hostname = "127.0.0.1";
        int port = 21;
        String username = "vv";
        String password = "5215215241";
        String pathname = "business/ebook";
        Base64 base64 = null;

        String s = null;
        byte[] b = null;
        String result = null;

        String name = "back_" + fileName;
        InputStream is = null;
        InputStream inputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        inputStream = byteArrayInputStream;

        s = file;

        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            inputStream = new ByteArrayInputStream(file.getBytes("UTF-8"));
            is = new ByteArrayInputStream(result.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            //logger.error("上传失败");
            //return null;
        }

        boolean flag = false;
        boolean f1 = FTPfUpAndDownUtil.deleteFile(hostname, port, username, password, pathname, fileName);
        boolean f2 = FTPfUpAndDownUtil.uploadFile(hostname, port, username, password, pathname, fileName, inputStream);

        FTPfUpAndDownUtil.uploadFile(hostname, port, username, password, pathname, name, is);


        if (f1 == true && f2 == true)
            flag = true;
        if (flag = false) {
            throw new ServiceException("头像上传失败");
        }

        return flag;
    }

    //
    //
    //下载图片
    @Override
    public String FileDown(String fileName) {
        String hostname = "127.0.0.1";
        int port = 21;
        String username = "vv";
        String password = "5215215241";
        String pathname = "business/ebook";
        MultipartFile multipartFile = null;

        String picture = null;
        picture = FTPfUpAndDownUtil.downloadFile(hostname, port, username, password, pathname, fileName);

        if (picture == null) {
            throw new ServiceException("tupian未找到");
        }
        System.out.println("***************** 下载图片 fileName:" + fileName);
        return picture;
    }

}
