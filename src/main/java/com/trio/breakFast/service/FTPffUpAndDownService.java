package com.trio.breakFast.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by ienovo on 2016/8/11.
 */

public interface FTPffUpAndDownService {
    public boolean FileUp(String fileName, String file);

    public String FileDown(String fileName);


}
