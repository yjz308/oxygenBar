package cn.geekzone.oxygenBar.common.entity;

import java.util.Random;

/**
 * 发送验证码工具类
 * Created by Sunny  An on 2017/1/12.
 */
public class PhoneCodeUtils {

    /**
     * 发送短信验证码
     */
    public static boolean sendMsg(String phone, String code) {
        return true;
    }


    /**
     * 获得指定长度的验证码
     */
    public static String getRandomCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 产生1-9随机数
            sb.append(random.nextInt(9) + 1);
        }
        return sb.toString();
    }


}
