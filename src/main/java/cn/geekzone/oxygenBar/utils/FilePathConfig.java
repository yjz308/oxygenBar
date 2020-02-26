package cn.geekzone.oxygenBar.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 上传文件路径类
 *
 * @author Sunny  An
 * @version 创建时间   2016年12月1日 上午11:27:38
 */
public class FilePathConfig {
    
    /** 上传文件目录 */
    public final static String UPLOAD_FOLDER = ".." + File.separator + "oxygenBarUpload";
    
    /** 上传文件目录 --本机测试时使用*/
    public final static String UPLOAD_FOLDER_BENJI_CESHI = "oxygenBarUpload";
    
    /**
     * 路径类型
     *
     * @author Sunny  An
     * @version 创建时间   2016年12月1日 上午10:39:52
     */
    public enum Type {

        /**
         * 用户上传
         */
        USER("user" + File.separator),

        /**
         * 管理端上传
         */
        ADMIN("admin" + File.separator),
        
        /**
         * 管理员上传
         */
        BUSINESS("business" + File.separator), 
        
        
        
        /**
         * 网页文件
         */
        HTML("html" + File.separator);

        private String path;

        Type(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return path;
        }

    }

    /**
     * <p>获得上传文件绝对路径
     * <p>包含最后的 "\" 或 "/"
     *
     * @return
     */
    private static String getBasePath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String base = request.getServletContext().getRealPath("/");
        if (base != null && !base.endsWith(File.separator)) {
            return base + File.separator;
        }
        return base;
    }

    /**
     * <pre>
     * 获得完整路径，包括项目路径
     * 如 C:\tomcat\webapps\test\ upload\BankCardType\image\
     *</pre>
     * @param type
     * @return
     */
    public static String getLocalPath(Type type) {
        return getBasePath() + UPLOAD_FOLDER + File.separator + type;
    }
    public static String getLocalPathByLocalCeshi(Type type) {
    	return getBasePath() + UPLOAD_FOLDER_BENJI_CESHI + File.separator + type;
    }

    /**
     * <pre>
     * 获得完整路径，包括项目路径，同时创建指定目录
     * 如 C:\tomcat\webapps\test\ upload\BankCardType\image\
     * </pre>
     *
     * @param type
     * @param create 是否创建指定目录
     * @return
     */
    public static String getLocalPath(Type type, boolean create) {
        String path = getLocalPath(type);
        if (create) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return path;
    }
    public static String getLocalPathByLocalCeshi(Type type, boolean create) {
    	String path = getLocalPathByLocalCeshi(type);
    	if (create) {
    		File file = new File(path);
    		if (!file.exists()) {
    			file.mkdirs();
    		}
    	}
    	return path;
    }

    /**
     * <p>获取URL的路径片段
     * <p>如  /upload/BankCardType/image/
     *
     * @param type
     * @return
     */
    public static String getUrlPath(Type type) {
        return "/" /*+ UPLOAD_FOLDER + "/"*/ + type.toString().replace(File.separator, "/");
    }

}
