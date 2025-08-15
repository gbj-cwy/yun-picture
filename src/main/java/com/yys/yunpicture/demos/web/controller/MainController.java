package com.yys.yunpicture.demos.web.controller;

import com.yys.yunpicture.demos.web.common.utils.ResultUtils;
import com.yys.yunpicture.demos.web.common.vo.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public BaseResponse<String> health() {
        return ResultUtils.success("ok");
    }
}
