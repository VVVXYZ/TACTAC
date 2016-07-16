package com.trio.breakFast.util;

import java.awt.*;
import java.util.Random;

/**
 * Author: QiKai
 * Create Time: 14-2-2 下午3:45
 * Version: 1.0
 */
public class RandomColorGenerator {

    public static Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
