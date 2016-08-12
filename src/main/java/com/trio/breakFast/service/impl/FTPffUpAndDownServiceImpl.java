package com.trio.breakFast.service.impl;


import com.trio.breakFast.service.FTPffUpAndDownService;
import com.trio.breakFast.sys.exception.ServiceException;
import com.trio.breakFast.util.ftp.FTPfUpAndDownUtil;
import org.springframework.stereotype.Service;

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
    public boolean FileUp(String fileName, InputStream inputStream) {

        String hostname = "127.0.0.1";
        int port = 21;
        String username = "vv";
        String password = "5215215241";
        String pathname = "business/ebook";

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
    public InputStream FileDown(String fileName) {
        String hostname = "127.0.0.1";
        int port = 21;
        String username = "vv";
        String password = "5215215241";
        String pathname = "business/ebook";
        InputStream inputStream = null;

        inputStream = FTPfUpAndDownUtil.downloadFile(hostname, port, username, password, pathname, fileName);

        return inputStream;
    }

}
