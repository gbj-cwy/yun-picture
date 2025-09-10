package com.yys.web.controller;

import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import com.yys.web.common.enums.ErrorCode;
import com.yys.web.common.exception.BusinessException;
import com.yys.web.common.utils.ResultUtils;
import com.yys.web.common.vo.BaseResponse;
import com.yys.web.manager.CosManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author 28142
 * @description
 * @date 2025/9/5 10:00
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Tag(name = "文件管理", description = "文件管理接口")
public class FileController {

    @Resource
    private CosManager cosManager;

    @PostMapping("/upload")
    @Operation(summary = "文件上传测试")
    public BaseResponse<String> upload(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String filePath = String.format("/test/%s", originalFilename);
        File file = null;
        try {
            file = File.createTempFile(filePath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filePath, file);
            log.info("上传成功：{}", filePath);
            return ResultUtils.success(filePath);
        } catch (IOException e) {
            log.error("上传失败：filePath：{}，error：{}", filePath, e.getMessage());
            throw new BusinessException(ErrorCode.FILE_UPLOAD_ERROR);
        } finally {
            if (file != null) {
                boolean delete = file.delete();
                if (!delete) {
                    log.error("删除临时文件失败：{}", file.getAbsolutePath());
                }
            }
        }
    }

    @PostMapping("/download")
    @Operation(summary = "文件下载测试")
    public void download(String filePath, HttpServletResponse response) throws IOException {
        try (COSObject cosObject = cosManager.getObject(filePath);
             COSObjectInputStream objectInputStream = cosObject.getObjectContent();
             ServletOutputStream outputStream = response.getOutputStream()) {

            // 获取文件信息
            long contentLength = cosObject.getObjectMetadata().getContentLength();
            String contentType = cosObject.getObjectMetadata().getContentType();

            // 设置响应头
            // response.setContentType("application/octet-stream;charset=UTF-8");
            // response.setHeader("Content-Disposition", "attachment; filename=" + filePath);
            response.setContentType(contentType != null ? contentType : "application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filePath);
            response.setHeader("Content-Length", String.valueOf(contentLength));
            // 写入数据
            // outputStream.write(bytes);
            // outputStream.flush();

            // 使用流式传输
            byte[] bytes = new byte[8192];
            int read;
            while ((read = objectInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException e) {
            log.error("下载失败：filePath：{}，error：{}", filePath, e.getMessage());
            throw new BusinessException(ErrorCode.FILE_DOWNLOAD_ERROR);
        }
    }
}
