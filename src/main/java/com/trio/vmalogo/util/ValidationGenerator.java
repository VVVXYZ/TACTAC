package com.trio.vmalogo.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

/**
 * Description: 参照jsp验证码的生成方法。
 * 考虑并发访问，不能将生成的验证码字符串放在类中让别人取。故，直接在controller中生成验证码并返回
 * Author: QiKai
 * Create Time: 14-2-2 下午3:46
 * Version: 1.0
 */
public class ValidationGenerator {

    //  the size of the validation image
    private static final int width = 60;
    private static final int height = 20;
    //  the length of the validation string
    private static final int length = 4;

    private String validation;

    private static ValidationGenerator instance = null;

    private ValidationGenerator() {
    }

    public static ValidationGenerator getInstance() {
        if (instance == null)
            instance = new ValidationGenerator();
        return instance;
    }

    public RenderedImage generateValidation() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(RandomColorGenerator.getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(RandomColorGenerator.getRandomColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(10);
            int yl = random.nextInt(10);
            g.drawLine(x, y, x + xl, y + yl);
        }
//        Another method to generate validation!

//        char c[] = new char[62];
//        for (int i = 97, j = 0; i < 123; i++, j++) {
//            c[j] = (char) i;
//        }
//        for (int o = 65, p = 26; o < 91; o++, p++) {
//            c[p] = (char) o;
//        }
//        for (int m = 48, n = 52; m < 58; m++, n++) {
//            c[n] = (char) m;
//        }
//        String sRand = "";
//        for (int i = 0; i < 4; i++) {
//            int x = random.nextInt(62);
//            String rand = String.valueOf(c[x]);
//            sRand += rand;
//            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
//            g.drawString(rand, 13 * i + 6, 16);
//        }

        validation = RandomStringGenerator.getInstance(length).getRandomString62();
        for (int i = 0; i < length; i++) {
            String tmp = "" + validation.charAt(i);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(tmp, 13 * i + 6, 16);
        }
        g.dispose();
        return image;
    }

    public String getValidation() {
        return validation;
    }

}
