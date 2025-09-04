package com.yys.web.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 28142
 * @description 分页响应
 * @date 2025/8/27 15:48
 */
@Data
public class PageResponse<T> {

    private long total;

    private List<T> list;

    public PageResponse() {
        this.total = 0;
        this.list = new ArrayList<>();
    }

    public PageResponse(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
