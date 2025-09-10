package com.yys.web.manager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 28142
 * @description
 * @date 2025/9/10 19:29
 */
@Service
public class FileManager {

    @Resource
    private CosManager cosManager;

    public void uploadFile(MultipartFile file, String pathPrefix) {

    }
}
