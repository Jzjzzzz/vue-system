package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.UserConstants;
import com.jzj.base.web.mapper.SysTodoMapper;
import com.jzj.base.web.pojo.entity.SysTodo;
import com.jzj.base.web.service.SysTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 备忘录 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-14
 */
@Service
public class SysTodoServiceImpl extends ServiceImpl<SysTodoMapper, SysTodo> implements SysTodoService {


    @Autowired
    private SysTodoMapper sysTodoMapper;

    @Override
    public String checkUnique(String content) {
        Long count = sysTodoMapper.selectCount(new QueryWrapper<SysTodo>().eq("text", content));
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertTodo(String content) {
        SysTodo sysTodo = new SysTodo();
        sysTodo.setText(content);
        sysTodo.setDone(false);
        return sysTodoMapper.insert(sysTodo);
    }

    @Override
    public int updateTodo(SysTodo sysTodo) {
        sysTodo.setDone(!sysTodo.isDone());
        return sysTodoMapper.updateById(sysTodo);
    }
}
