package com.wzx.nirvana.controller;

import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

@Controller
@RequestMapping("/api/upload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("{name}")
    @ResponseBody
    public CommonResult<String> upload(@PathVariable String name, @RequestPart MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) return CommonResult.errorReturn(401, "Empty File");
        String fileName = file.getOriginalFilename();
        String suffixName = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : null;

        String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/" + name + suffixName;

        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            Files.deleteIfExists(dest.toPath());
            Files.copy(file.getInputStream(), dest.toPath());
        } catch (Exception e) {
            logger.info(e.toString());
            return CommonResult.errorReturn(e.toString());
        }

        return CommonResult.successReturn(dest.getAbsolutePath());
    }
}
