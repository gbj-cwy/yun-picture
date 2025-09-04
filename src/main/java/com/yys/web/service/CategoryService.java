package com.yys.web.service;

import com.yys.web.common.vo.PageResponse;
import com.yys.web.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yys.web.properties.request.CategoryListReq;
import com.yys.web.properties.request.CategorySaveReq;
import com.yys.web.response.CategoryListResp;

/**
* @author 28142
* @description 针对表【category(图片分类)】的数据库操作Service
* @createDate 2025-08-27 15:41:29
*/
public interface CategoryService extends IService<Category> {

    /**
     * 分类列表
     * @param req
     * @return
     */
    PageResponse<CategoryListResp> list(CategoryListReq req);

    /**
     * 分类保存
     * @param req
     * @return
     */
    boolean save(CategorySaveReq req);

}
