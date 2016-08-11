package com.trio.breakFast.service.impl;

import com.trio.breakFast.service.FileUploadService;
import com.trio.breakFast.util.ftp.FtpUtil;
import com.trio.breakFast.util.ftp.PropertiesUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author loser
 * @ClassName FileUploadServiceImpl
 * @Description
 * @Date 2016/04/23 15:11
 * @History :
 * <author>
 * <time>
 * <desc>
 */
//
@Service
public class FileUploadServiceImpl implements FileUploadService{
    static Logger logger = Logger.getLogger(FileUploadServiceImpl.class);
    @Override
    public Map.Entry<String, String> upload(MultipartFile file, String prefix){
        HashMap<String, String> result = new HashMap<String, String>();

        InputStream is = null;
        try {
            String fileName = getUniqueName(file.getOriginalFilename());
            is = file.getInputStream();
            FTPClient ftp = FtpUtil.getFtpClient(PropertiesUtil.getProperty("FTP_IP"),
                    Integer.parseInt(PropertiesUtil.getProperty("FTP_PORT")), PropertiesUtil.getProperty("FTP_USER"),
                    PropertiesUtil.getProperty("FTP_PASSWORD"));// 获取ftpclient，是的读取一次list，只建立一个ftpclient

            FtpUtil.uploadFile(ftp, prefix, fileName, is);
            result.put(file.getOriginalFilename(), PropertiesUtil.getProperty("FILE_SERVER") + prefix + "/" + fileName);
            FtpUtil.closeFtp(ftp);
            is.close();
        } catch (IOException e){
            logger.error("上传失败");
            return null;
        }

        return result.size() == 1 ? result.entrySet().iterator().next() : null;
    }

    @Override
    public Map<String, String> upload(MultipartFile[] files, String prefix){
        if(files == null || files.length == 0){
            return null;
        }

        HashMap<String, String> result = new HashMap<String, String>();

        InputStream is = null;
        try {
            FTPClient ftp = FtpUtil.getFtpClient(PropertiesUtil.getProperty("FTP_IP"),
                    Integer.parseInt(PropertiesUtil.getProperty("FTP_PORT")), PropertiesUtil.getProperty("FTP_USER"),
                    PropertiesUtil.getProperty("FTP_PASSWORD"));// 获取ftpclient，是的读取一次list，只建立一个ftpclient

            for(MultipartFile file : files) {
                String fileName = getUniqueName(file.getOriginalFilename());
                is = file.getInputStream();
                FtpUtil.uploadFile(ftp, prefix, fileName, is);
                result.put(file.getOriginalFilename(),  PropertiesUtil.getProperty("FILE_SERVER") + prefix + "/" + fileName);
                is.close();
            }

            FtpUtil.closeFtp(ftp);

        } catch (IOException e){
            logger.error("上传失败");
            return null;
        }

        return result.size() == 0 ? null : result;
    }

    @Override
    public Map<String, String> upload(Collection<MultipartFile> files, String prefix){
        if(files == null || files.isEmpty()){
            return null;
        }

        HashMap<String, String> result = new HashMap<String, String>();

        InputStream is = null;
        try {
            FTPClient ftp = FtpUtil.getFtpClient(PropertiesUtil.getProperty("FTP_IP"),
                    Integer.parseInt(PropertiesUtil.getProperty("FTP_PORT")), PropertiesUtil.getProperty("FTP_USER"),
                    PropertiesUtil.getProperty("FTP_PASSWORD"));// 获取ftpclient，是的读取一次list，只建立一个ftpclient

            for(MultipartFile file : files) {
                String fileName = getUniqueName(file.getOriginalFilename());
                is = file.getInputStream();
                FtpUtil.uploadFile(ftp, prefix, fileName, is);
                result.put(file.getOriginalFilename(),  PropertiesUtil.getProperty("FILE_SERVER") + prefix + "/" + fileName);
                is.close();
            }

            FtpUtil.closeFtp(ftp);

        } catch (IOException e){
            logger.error("上传失败");
            return null;
        }

        return result.size() == 0 ? null : result;
    }

    private String getUniqueName(String fileName){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return  df.format(Calendar.getInstance().getTime()) + UUID.randomUUID().toString() + fileName.substring(fileName.indexOf(".")); //为日期 + uuID + 后缀名
    }

    @Override
    public String upload(InputStream inputStream, String prefix) {
        String ftpAddress="";
        try {
            String fileName = "agreement_"+System.currentTimeMillis() + ".pdf";
            logger.error("fileName="+fileName);
//            is = file.getInputStream();
            FTPClient ftp = FtpUtil.getFtpClient(PropertiesUtil.getProperty("FTP_IP"),
                    Integer.parseInt(PropertiesUtil.getProperty("FTP_PORT")), PropertiesUtil.getProperty("FTP_USER"),
                    PropertiesUtil.getProperty("FTP_PASSWORD"));// 获取ftpclient，是的读取一次list，只建立一个ftpclient
            FtpUtil.uploadFile(ftp, prefix, fileName, inputStream);
            ftpAddress =  PropertiesUtil.getProperty("FILE_SERVER") + prefix + "/" + fileName;
            logger.error("ftpAddress="+ftpAddress);
            FtpUtil.closeFtp(ftp);
            inputStream.close();
        } catch (IOException e){
            logger.error("FTP服务器出错");
            return null;
        }
        return ftpAddress;
    }
}
