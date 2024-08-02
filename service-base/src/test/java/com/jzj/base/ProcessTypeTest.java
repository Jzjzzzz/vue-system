package com.jzj.base;

import com.jzj.base.web.service.ProcessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Jzj
 * @Date 2024/8/1 下午4:29
 * @Version 1.0
 * @Message:
 */
@SpringBootTest
public class ProcessTypeTest {

    @Autowired
    private ProcessService processService;

    @Test
    public void findProcessType(){
        processService.findProcessType();
    }
}
