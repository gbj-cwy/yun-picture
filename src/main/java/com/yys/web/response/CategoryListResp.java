package com.yys.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 28142
 * @description 分类列表响应
 * @date 2025/8/28 9:50
 */
@Data
public class CategoryListResp {

    @Schema(description = "分类id")
    private Long id;

    @Schema(description = "分类名称")
    private String name;
}
