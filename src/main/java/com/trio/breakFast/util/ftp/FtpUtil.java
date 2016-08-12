package com.trio.breakFast.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

public class FtpUtil {
    private static String FTP_BASE = PropertiesUtil.getProperty("FTP_BASE"); // ftp根目录

    /** 本地字符编码 */
    private static String LOCAL_CHARSET = "GBK";

    // FTP协议里面，规定文件名编码为iso-8859-1
    private static String SERVER_CHARSET = "ISO-8859-1";
    /**
     * FTP上传文件
     *
     * @param ip
     * @param port
     * @param username
     * @param password //* @param path
     *                 //* @param filename
     * @return
     */
    public static FTPClient getFtpClient(String ip, int port, String username, String password) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(ip, port);// 连接FTP服务器
            ftp.login(username, password);// 登录
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            if (FTPReply.isPositiveCompletion(ftp.sendCommand(
                    "OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }
//            ftp.setControlEncoding(LOCAL_CHARSET);
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(FTP_BASE);
//			System.err.println(ftp.printWorkingDirectory());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    public static boolean uploadFile(FTPClient ftp, String path, String filename, InputStream is) {
        boolean success = false;
        try {
            ftp.changeWorkingDirectory(FTP_BASE);
            boolean isRightDir = ftp.printWorkingDirectory().contains(path);
            if (!isRightDir) {
                boolean flag = ftp.changeWorkingDirectory(path);
                if (!flag) {
                    createD(ftp, path);
                }
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);// 二进制文件
//            success = ftp.storeFile(filename, is);
//            FileInputStream file = (FileInputStream)is;
            success = ftp.storeFile(new String(filename.getBytes(LOCAL_CHARSET),SERVER_CHARSET), is);
//			System.err.println("upload "+ftp.printWorkingDirectory()+"/"+filename+" "+success);

        } catch (Exception e) {
            System.out.println("no ftp server...");
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public static boolean createD(FTPClient ftp, String path) throws IOException {
        String temp[] = path.split("/");
        for (int i = 0; i < temp.length; i++) {
            temp[i]=new String(temp[i].getBytes(LOCAL_CHARSET),SERVER_CHARSET);
//			ftp.printWorkingDirectory();
            ftp.makeDirectory(temp[i]);
            ftp.changeWorkingDirectory((ftp.printWorkingDirectory().endsWith("/") ? ftp.printWorkingDirectory() : ftp.printWorkingDirectory() + "/") + temp[i]);
        }
        return true;

    }

    //
    public static void closeFtp(FTPClient ftp) {
        try {
            ftp.changeWorkingDirectory(FTP_BASE);
            ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
