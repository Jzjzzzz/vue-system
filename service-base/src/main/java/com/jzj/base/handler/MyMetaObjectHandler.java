package com.jzj.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Jzj
 * @Date 2022/6/6 16:46
 * @Version 1.0
 * @Message: 时间自动填充拦截器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //这里是属性名称，不是字段名称
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("createBy", getUserName(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", getUserName(), metaObject);
    }

    private String getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            return auth.getName();
        }
        return "";
    }
}
