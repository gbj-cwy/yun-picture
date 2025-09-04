package com.yys.web.properties.request;

import com.yys.web.common.vo.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 28142
 * @description 分类列表请求参数
 * @date 2025/8/28 11:36
 */
@Data
public class CategoryListReq extends PageRequest {
    @Schema(description = "分类名称")
    private String name;
}
