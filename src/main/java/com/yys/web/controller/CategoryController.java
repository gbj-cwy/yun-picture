package com.yys.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yys.web.common.utils.ResultUtils;
import com.yys.web.common.vo.BaseResponse;
import com.yys.web.common.vo.PageResponse;
import com.yys.web.entity.Category;
import com.yys.web.properties.request.CategoryListReq;
import com.yys.web.properties.request.CategorySaveReq;
import com.yys.web.response.CategoryListResp;
import com.yys.web.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 28142
 * @description 分类管理控制器
 * @date 2025/8/27 15:44
 */
@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/list")
    @Operation(summary = "分类列表")
    public BaseResponse<PageResponse<CategoryListResp>> list(CategoryListReq req) {
        log.info("【分类管理 - 分类列表】request: {}", JSON.toJSONString(req));
        return ResultUtils.success(categoryService.list(req));
    }

    @PostMapping("/save")
    @Operation(summary = "分类编辑")
    public BaseResponse<Boolean> save(CategorySaveReq req) {
        log.info("【分类管理 - 分类编辑】request: {}", JSON.toJSONString(req));
        return ResultUtils.success(categoryService.save(req));
    }
}
