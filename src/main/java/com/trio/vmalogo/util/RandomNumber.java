package com.trio.vmalogo.util;

import java.util.Random;

/**
 * @author //作者 fjfzuhqc
 * @ClassName: //
 * @Description: //一句话功能简述/表所属模块
 * @Date //创建/生成日期 2016/03/15 20:30
 * @History:// 历史修改记录
 * <author>  // 修改人1
 * <time>    // 修改时间
 * <desc>    // 描述修改内容
 * <author>  // 修改人2
 * <time>    // 修改时间
 * <desc>    // 描述修改内容
 */
public class RandomNumber {

    /**
     * 获取count个随机数
     *
     * @param count 随机数位数
     * @return
     */
    public static String generatorNumber(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }

    /**
     * 〈方法的功能描述〉:TODO
     * 生成订单号
     *
     * @param //
     * @return
     * @throws [返回类型说明]
     * @Date // 2016/4/5/21:21
     * @author // lw
     * @methodName
     * @History:// 历史修改记录
     * <author>  // 修改人1
     * <time>    // 修改时间
     * <desc>    // 描述修改内容
     */
    static public String getmerchRechOrdNo() {
        String val = "";
        Random random = new Random();

        int length = random.nextInt(30) + 1;
        //参数length，表示生成几位随机数
        for (int i = 0; i < 30; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    static public String getRandomContractNo() {
        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < 26; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return "CONT" + val;
    }

}