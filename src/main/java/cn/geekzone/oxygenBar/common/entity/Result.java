package cn.geekzone.oxygenBar.common.entity;

import java.util.HashMap;



/**
 * JSON  传输的 数据格式
 * @author Sunny  An
 * @version   创建时间   2016年10月26日 下午2:07:00
 */
@SuppressWarnings("serial")
public class Result extends HashMap<String, Object> {
    /** 成功 */
    public static final Integer CODE_SUCCESS = 1;
    
    /** 失败 */
    public static final Integer CODE_ERROR = 2;
    
    /** 设置提示信息 */
    public Result setMessage(String message) {
        put("message", message);
        return this;
    }
    
    /** 设置结果代码 */
    public Result setResultCode(Integer code) {
        put("code", code);
        return this;
    }

    /** 返回结果代码 */
    public Integer getResultCode() {
        return (Integer) get("code");
    }
    
    /** 设置结果成功*/
    public Result setSuccessCode() {
        put("code", CODE_SUCCESS);
        setStatus(Status.success);
        return this;
    }

    public boolean isSuccess() {
        Integer code = getResultCode();
        return code != null && code.equals(CODE_SUCCESS);
    }

    public boolean isError() {
        Integer code = getResultCode();
        return code != null && code.equals(CODE_ERROR);
    }
    
    /** 设置结果失败*/
    public Result setErrorCode() {
        put("code", CODE_ERROR);
        return this;
    }
    
    /** 设置数据 */
    public Result setData(Object data) {
        put("data", data);
        return this;
    }

    public Object getData() {
        return get("data");
    }
    
    /** 添加键值对 */
    public Result add(String key, Object value) {
        put(key, value);
        return this;
    }
    
    /** 设置状态码 */
    public Result setStatus(Status status) {
        put("status", status.getCode());
        put("statusMsg", status);
        return this;
    }
    
    /** 获取状态码 */
    public Status getStatus() {
        Status status = (Status) get("statusMsg");
        return status == null ? Status.none : status;
    }
    
    
}
