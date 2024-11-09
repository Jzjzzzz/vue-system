package com.jzj.base.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.util.ListUtils;
import com.jzj.base.listener.base.BaseExcelListener;
import com.jzj.base.utils.sign.SpringUtils;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.service.SysUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * <p>
 * 用户数据导入监听器
 * </p>
 *
 * @author Jzj
 * @since 2024/9/1 02:00
 */
@Component
public class ExcelSysUserListener extends BaseExcelListener<SysUser> {


    @Override
    public void read(InputStream inputStream) {
        EasyExcel.read(inputStream, SysUser.class,this).sheet().doRead();
    }

    @Override
    public void saveData() {
        SpringUtils.getBean(SysUserService.class).saveBatch(cachedDataList);
    }

    @Override
    public String getCode() {
        return "user";
    }

    @Override
    public void invoke(SysUser data, AnalysisContext context) {
        String encode = new BCryptPasswordEncoder().encode("111111");
        data.setPassword(encode);
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }


}
