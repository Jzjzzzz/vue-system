package com.jzj.base.web.controller;

import com.jzj.common.annotation.Log;
import com.jzj.base.factory.UploadFactory;
import com.jzj.common.utils.result.R;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.base.web.service.SysConfigService;
import com.jzj.base.web.service.UploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 图片上传控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/27 17:13
 */
@RestController
@RequestMapping("/api/upload")
@Api("图片上传控制器")
public class UploadController {

    @Autowired
    private SysConfigService sysConfigService;

    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping("/uploadImg")
    public R uploadImg(@RequestParam("file") MultipartFile photo, String name, HttpServletRequest request) {
        //回显URL
        String url = "";
        UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
        url = uploadService.uploadImg(photo, name, request);
        return R.ok("url", url);
    }

    @Log(title = "文件管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteImg")
    public R deleteImg(@RequestParam("url") String url, HttpServletRequest request) {
        UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
        uploadService.deleteImg(url, request);
        return R.ok("删除成功");
    }
}
