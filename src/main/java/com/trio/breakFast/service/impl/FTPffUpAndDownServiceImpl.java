package com.trio.breakFast.service.impl;


import com.trio.breakFast.service.FTPffUpAndDownService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ftp.FTPfUpAndDownUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


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
    public boolean FileUp(String fileName, MultipartFile file) {

        String hostname = "127.0.0.1";
        int port = 21;
        String username = "vv";
        String password = "5215215241";
        String pathname = "business/ebook";

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            //logger.error("上传失败");
            //return null;
        }

        boolean flag = false;
        boolean f1 = FTPfUpAndDownUtil.deleteFile(hostname, port, username, password, pathname, fileName);
        boolean f2 = FTPfUpAndDownUtil.uploadFile(hostname, port, username, password, pathname, fileName, inputStream);

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
    public MultipartFile FileDown(String fileName) {
        String hostname = "127.0.0.1";
        int port = 21;
        String username = "vv";
        String password = "5215215241";
        String pathname = "business/ebook";
        MultipartFile multipartFile = null;

        multipartFile = FTPfUpAndDownUtil.downloadFile(hostname, port, username, password, pathname, fileName);

        return multipartFile;
    }

}
