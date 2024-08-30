package com.jzj.base.utils.constant;

/**
 * <p>
 * 缓存的key 常量
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public interface CacheConstants {
    /**
     * 登录用户 redis key
     */
    String LOGIN_TOKEN_KEY = "vue_system:login_tokens:";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 阿里云存储开关 redis key
     */
    String A_LI_YUN_ENABLE_CODE = "sys.oss.enable";

    /**
     * 网站基本信息
     */
    String SYS_WEB_INFORMATION = "sys_web_information";

    /**
     *  邮箱通知开关
     */
    String SYS_EMAIL_ENABLE = "sys_email_enable";

    /**
     * Email发送是否使用RabbitMQ
     */
    String SYS_RABBIT_ENABLE = "sys_rabbit_enable";

}
