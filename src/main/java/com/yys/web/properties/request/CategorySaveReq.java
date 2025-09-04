package com.yys.web.properties.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 28142
 * @description 分类保存请求参数
 * @date 2025/8/28 17:46
 */
@Data
public class CategorySaveReq {
    @Schema(description = "分类id")
    private Long id;

    @Schema(description = "父级分类id")
    private Long pId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类描述")
    private String description;
}
