package com.util;

import com.trio.breakFast.util.PictureFilter;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author loser
 * @ClassName PatternTest
 * @Description
 * @Date 2016/05/06 18:35
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public class PatternTest {
    @Test
    public void main(){
        String input = "fdg{dfg}df{s}ga{s}dfg{asdgfsdgsdfg}dfgdfgdf{gsdg}dfhfhfhdfdfh";
        String string  = "\\{.*?\\}";
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }

    @Test
    public void picTest(){
        String filename = "a.txt";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "a.jpg";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "ff19a44c4480868e589f7c95e5ef1e5f.jpeg";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "a.jPg";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "dsfds12312.png";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "sdfsdf.doc";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "sdfsd545465.gif";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "家图片.png";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
        filename = "56sd4f6sd546.xls";
        System.out.println(filename+"    "+PictureFilter.PICTURE_PATTERN.matcher(filename).matches());
    }
}
