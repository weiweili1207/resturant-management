package com.dine.controller.admin;

import com.dine.constant.MessageConstant;
import com.dine.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.dine.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags="common interface")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * upload file
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("upload file")
    public Result<String> upload(MultipartFile file) {
        log.info("Starting to upload file: {}", file);
        try {
            //original file name
            String originalFileName = file.getOriginalFilename();
            //get the extension of original file name
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            //construct new file name
            String objectName = UUID.randomUUID().toString() + extension;
            //request path of file
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("upload file failed: {}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
