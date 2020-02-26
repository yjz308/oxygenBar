package cn.geekzone.oxygenBar.common.entity;

public class Constants {
	
	public static String photoPrefix = "http://39.98.37.28/oxygenBarUpload";
	
	public final static String alidayu_accessKeyId = "LTAIeDlOSYXhqEUV";

	public final static String alidayu_accessKeySecret = "7kRhDnCzetph2vCLur4ClwA5Q3abQv";

	public final static String alidayu_signName = "人人众包";
	
    /** 基础图片URL */
    public static String BASE_IMG_URL = null;
    static {
        if ("Sunny  An".equals(System.getenv("USERNAME"))) {
            BASE_IMG_URL = "http://whj.tunnel.qydev.com/wenhuaju";
        } else {
//        	BASE_IMG_URL = "http://127.0.0.1:8080/whjupload";
//        	BASE_IMG_URL = "http://120.77.41.17/whjupload";
            BASE_IMG_URL = "http://www.wuqingwenhua.com/whjupload";
        }
    }
    
    /** 性别-未知0 */
    public final static int GENDER_UNKNOWN = 0;
    
    /** 性别-男1 */
    public final static int GENDER_MALE = 1;
    
    /** 性别-女2 */
    public final static int GENDER_FEMALE = 2;
    

    /** 验证码-业务代码-账户注册 */
    public final static String OPERATION_USER_REG = "user_reg";
    
    /** 验证码-业务代码-找回密码 */
    public final static String OPERATION_USER_FORGET_PWD = "user_forget_pwd";
    
    /** 验证码-业务代码-参加活动 */
    public final static String OPERATION_JOIN_ACTIVITY = "join_activity";
    
    /** 验证码-业务代码-参加活动 */
    public final static String OPERATION_QUICK_LOGIN = "user_quick_login";
    
    /** 验证码-业务代码-绑定用户 */
    public final static String OPERATION_BIND_USER = "bind_user";
    
    //salt
    public final static String SALT = "TIAN-RAN-YANG-BA";
	
}
