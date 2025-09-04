package com.yys.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yys.web.common.vo.PageResponse;
import com.yys.web.entity.Category;
import com.yys.web.properties.request.CategoryListReq;
import com.yys.web.properties.request.CategorySaveReq;
import com.yys.web.response.CategoryListResp;
import com.yys.web.service.CategoryService;
import com.yys.web.mapper.CategoryMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 28142
* @description 针对表【category(图片分类)】的数据库操作Service实现
* @createDate 2025-08-27 15:41:29
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResponse<CategoryListResp> list(CategoryListReq req) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Category::getName, req.getName());
        Page<Category> page = new Page<>(req.getCurrent(), req.getPageSize());
        List<Category> records = categoryMapper.selectPage(page, queryWrapper).getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new PageResponse<>();
        }
        List<CategoryListResp> list = records.stream()
                .map(item -> {
                    CategoryListResp resp = new CategoryListResp();
                    resp.setId(item.getId());
                    resp.setName(item.getName());
                    return resp;
                }).toList();
        return new PageResponse<>(page.getTotal(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(CategorySaveReq req) {
        Category category = new Category();
        BeanUtils.copyProperties(req, category);
        categoryMapper.insertOrUpdate(category);
        return true;
    }
}




