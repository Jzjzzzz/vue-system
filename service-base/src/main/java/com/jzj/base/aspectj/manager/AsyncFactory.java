package com.jzj.base.aspectj.manager;

import com.jzj.base.factory.UploadFactory;
import com.jzj.base.utils.sign.AddressUtils;
import com.jzj.base.utils.sign.SpringUtils;
import com.jzj.base.web.pojo.entity.MtLog;
import com.jzj.base.web.pojo.entity.MtUser;
import com.jzj.base.web.pojo.entity.SysOperLog;
import com.jzj.base.web.service.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.TimerTask;

/**
 * <p>
 * 异步工厂（产生任务用）
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Slf4j
public class AsyncFactory {

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

    /**
     * 批量删除图片
     * @param imgList 图片列表
     * @param request 请求
     * @return  任务task
     */
    public static TimerTask deleteBtnImg(List<String> imgList, HttpServletRequest request){
        return new TimerTask(){
            @Override
            public void run() {
                UploadService uploadService = UploadFactory.getUploadService(SpringUtils.getBean(SysConfigService.class));
                uploadService.deleteBtnImg(imgList,request);
            }
        };
    }

    /**
     * 发送邮件
     * @param to 目标email地址
     * @param subject 邮件主题
     * @param text 纯文本内容
     * @return  任务task
     */
    public static TimerTask sendMail(String to, String subject, String text){
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(EmailService.class).sendMail(to,subject,text);
            }
        };
    }

    /**
     * 茅台预约日志记录
     * @param log 日志
     */
    public static TimerTask mtLogRecords(MtLog log){
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(MtLogService.class).insertLog(log);
            }
        };
    }

    /**
     * 茅台预约
     * @param user 预约用户
     */
    public static TimerTask mtReservation(MtUser user){
        return new TimerTask() {
            @Override
            public void run() {
                log.info("「开始预约用户」" + user.getMobile());
                SpringUtils.getBean(MtApiService.class).reservation(user);
            }
        };
    }

    /**
     * 茅台旅游
     * @param user 预约用户
     */
    public static TimerTask mtTravelReward(MtUser user){
        return new TimerTask() {
            @Override
            public void run() {
                log.info("「开始获得旅行奖励」" + user.getMobile());
                SpringUtils.getBean(MtApiService.class).getTravelReward(user);
            }
        };
    }
}
