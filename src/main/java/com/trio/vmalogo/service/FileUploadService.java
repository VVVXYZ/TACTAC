package com.trio.vmalogo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * @author loser
 * @ClassName FileUploadService
 * @Description  文件上传服务
 * @Date 2016/04/23 15:05
 * @History :
 * <author>
 * <time>
 * <desc>
 */


public interface FileUploadService {
    /**
    * 〈方法的功能描述〉:单文件上传
    *
    * @Date	 2016/4/23 15:06
    * @author  loser
    * @methodName upload
    * @param      file     文件名
    * @param      prefix   目录前缀
    * @return     最终文件名和对应存储路径
    * @throws
    * @History:
    *	<author>
    *   <time>
    *   <desc>
    **/
    Map.Entry<String, String> upload(MultipartFile file, String prefix);

    /**
     * 〈方法的功能描述〉:多文件上传
     *
     * @Date	 2016/4/23 15:06
     * @author  loser
     * @methodName upload
     * @param      files     文件名
     * @param      prefix   目录前缀
     * @return     最终文件名和对应路径
     * @throws
     * @History:
     *	<author>
     *   <time>
     *   <desc>
     **/
    Map<String, String> upload(MultipartFile[] files, String prefix);

    /**
     * 〈方法的功能描述〉:多文件上传
     *
     * @Date	 2016/4/23 15:06
     * @author  loser
     * @methodName upload
     * @param      files     文件
     * @param      prefix   目录前缀
     * @return     最终文件名和对应路径
     * @throws
     * @History:
     *	<author>
     *   <time>
     *   <desc>
     **/
    Map<String, String> upload(Collection<MultipartFile> files, String prefix);

    /**
     * 〈方法的功能描述〉:文件输入流上传
     *
     * @Date	 2016/4/23 15:06
     * @author  fjfzuhqc
     * @methodName upload
     * @param      inputStream     文件流
     * @param      prefix   目录前缀
     * @return     最终文件名和对应存储路径
     * @throws
     * @History:
     *	<author>
     *   <time>
     *   <desc>
     **/
    String upload(InputStream inputStream, String prefix);
}
