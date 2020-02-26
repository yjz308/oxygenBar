package cn.geekzone.oxygenBar.common.entity;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 数据格式验证工具包
 * Created by Sunny  An on 2017/1/12.
 */
public class DataFormatUtils {
    
    private final static Pattern phonePattern = Pattern.compile("^1\\d{10}$");
    private final static Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\\\\\|\\[\\]\\{\\}\\;\\:\\'\\\"\\,\\.\\/\\<\\>\\?]+$");
    private final static Pattern nickNamePattern = Pattern.compile("^[a-zA-Z0-9\\-\\_\\u4E00-\\u9FBF]+$");
    
    private final static List<String> operations = new ArrayList<String>();
    
    // 获取所有可用operation
    static {
        Class<?> cls = Constants.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().startsWith("OPERATION_")) {
                try {
                    Object value = field.get(null);
                    if (value != null && value instanceof String) {
                        String s = ((String) value).toLowerCase();
                        operations.add(s);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 是否是手机号
     */
    public static boolean isPhone(String phone) {
        if (phone == null) {
            return false;
        }
        return phonePattern.matcher(phone).matches();
    }

    /**
     * 是否是有效的业务代码
     */
    public static boolean isValidOperation(String operation) {
        if (operation == null) {
            return false;
        }
        operation = operation.toLowerCase();
        return operations.contains(operation);
    }
    
    /**
     * 是否是金额
     * 非null >=0 小数点后两位
     * @param money
     * @return
     */
    public static boolean isMoney(BigDecimal money) {
        if (money == null) {
            return false;
        }
        if (money.compareTo(BigDecimal.ZERO) == -1) {
            return false;
        }
        // 精度应小于等于2
        if (money.scale() > 2) {
            return false;
        }
        return true;
    }
    
    /**
     * 是否是有效的密码
     * @param password
     * @param minLength
     * @return
     */
    public static boolean isPassword(String password, int minLength) {
        if (password == null || password.length() < minLength) {
            return false;
        }
        return passwordPattern.matcher(password).matches();
    }
    
    /**
     * 检查是否只含有数字字母汉字-_
     * @return
     */
    public static boolean isNickName(String string) {
        if (string == null) {
            return false;
        }
        return nickNamePattern.matcher(string).matches();
    }

    public static void main(String[] args) {
        /*System.out.println(isPhone("18222950001"));
        System.out.println(isPhone(null));
        System.out.println(isValidOperation(Constants.OPERATION_USER_REG));
        System.out.println(isValidOperation("user_gg"));
        System.out.println(isValidOperation("111"));
        System.out.println(isMoney(new BigDecimal("0.01")));*/
        //System.out.println(isPassword("aaaaaaaaa", 6));
        System.out.println(isNickName("啊啊啊az34Fv-_ZQ这周"));
        System.out.println(isValidOperation(Constants.OPERATION_USER_REG));
        System.out.println(isValidOperation("user_gg"));
    }

}
