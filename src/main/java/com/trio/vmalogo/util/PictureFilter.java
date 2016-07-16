package com.trio.vmalogo.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author loser
 * @ClassName PictureFilter
 * @Description
 * @Date 2016/06/02 15:05
 */

public class PictureFilter {
    private  static Logger logger = Logger.getLogger(PictureFilter.class);
    static public final Pattern PICTURE_PATTERN = Pattern.compile("^.*(.jpg|.gif|.png|.bmp|.jpeg)$", Pattern.CASE_INSENSITIVE);

    static public List<MultipartFile> getPictureList(MultipartFile... files){
        List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();

        if(files == null || files.length == 0){
            return Lists.<MultipartFile>newArrayList();
        }

        for(MultipartFile file : files){
            file = getPicture(file);
            if(file != null){
                multipartFileList.add(file);
            }
        }
        return multipartFileList;
    }

    static public MultipartFile getPicture(MultipartFile file){

        if(checkSuffix(file) && checkStream(file)){
            logger.info(file.getOriginalFilename()+"是图片");
            return file;
        }else{
            logger.info(file.getOriginalFilename()+"不是图片");
        }

        return null;
    }

    static public Boolean checkSuffix(MultipartFile file){
        return file!=null && !Strings.isNullOrEmpty(file.getOriginalFilename()) && PICTURE_PATTERN.matcher(file.getOriginalFilename()).matches();
    }

    static public Boolean checkStream(MultipartFile file){
        try {
            String fileHex = getFileHexString(file.getBytes()).toUpperCase();
            if(fileHex.startsWith(FileType.jpg.getCode())
                    || fileHex.startsWith(FileType.png.getCode())
                    ||fileHex.startsWith(FileType.gif.getCode())
                    ||fileHex.startsWith(FileType.bmp.getCode()) ){
                return true;
            }
        }catch (Exception e){
            logger.debug(file.getOriginalFilename()+"异常",e);
        }

        return false;
    }

    public final static String getFileHexString(byte[] b)
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0)
        {
            return new String();
        }
        for (int i = 0; i < b.length; i++)
        {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2)
            {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }

        return stringBuilder.toString();
    }

    public enum FileType{
        jpg("FFD8FF"),png("89504E47"),gif("47494638"),bmp("424D");
        private String code;
        private FileType(String code){
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
