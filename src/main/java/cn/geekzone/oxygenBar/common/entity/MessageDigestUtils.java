package cn.geekzone.oxygenBar.common.entity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要工具类
 * Created by Sunny  An on 2017/2/17 0017.
 */
public class MessageDigestUtils {

    private final static char[] CHARS = "0123456789abcdef".toCharArray();

    public static String getSHA1(String message, String salt) {
        if (message == null) {
            //logger.debug("message 为空");
            return null;
        }
        if (salt != null && !"".equals(salt)) {
            message = message + salt;
        }
         try {
             MessageDigest digest = MessageDigest.getInstance("SHA-1");
             digest.update(message.getBytes());
             byte[] bytes = digest.digest();
             StringBuilder sb = new StringBuilder(50);
             for (byte b : bytes) {
                 sb.append(CHARS[(b & 0xFF) >> 4]);
                 sb.append(CHARS[b & 0x0F]);
             }
             return sb.toString();
         } catch (NoSuchAlgorithmException e) {
             // e.printStackTrace();
             //logger.debug("无效的摘要算法");
             return null;
         }
     }
    
    public static String getMD5(String message, String salt) {
        if (message == null) {
            //logger.debug("message 为空");
            return null;
        }
        if (salt != null && !"".equals(salt)) {
            message = message + salt;
        }
         try {
             MessageDigest digest = MessageDigest.getInstance("MD5");
             digest.update(message.getBytes());
//             byte[] bytes = digest.digest();
//             StringBuilder sb = new StringBuilder(50);
//             for (byte b : bytes) {
//                 sb.append(CHARS[(b & 0xFF) >> 4]);
//                 sb.append(CHARS[b & 0x0F]);
//             }
//             return sb.toString();
             return new BigInteger(1,digest.digest()).toString(16);
         } catch (NoSuchAlgorithmException e) {
             // e.printStackTrace();
             //logger.debug("无效的摘要算法");
             return null;
         }
     }

    public static void main(String[] args) {
        System.out.println(getMD5("222222",""));
    }

}
