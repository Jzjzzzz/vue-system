package com.jzj.common.utils.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 统一返回结果的类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Data
public class R {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据(Object类型)
     */
    private Object data;

    private R(){}

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("操作成功");
        return r;
    }

    public static R ok(Object object){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("操作成功");
        r.setData(object);
        return r;
    }

    public static R ok(String key, Object value){
        R r = new R();
        HashMap<String, Object> map = new HashMap<>();
        map.put(key,value);
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("操作成功");
        r.setData(map);
        return r;
    }

    public static R ok(Map<String, Object> map){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("操作成功");
        r.setData(map);
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg("操作失败");
        return r;
    }

    public static R error(String message){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg(message);
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMsg(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }
}
