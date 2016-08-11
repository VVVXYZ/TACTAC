package com.trio.breakFast.service;

import java.io.InputStream;

/**
 * Created by ienovo on 2016/8/11.
 */

public interface FTPffUpAndDownService {
    public boolean FileUp(String fileName, InputStream inputStream);

    public InputStream FileDown(String fileName);


}
