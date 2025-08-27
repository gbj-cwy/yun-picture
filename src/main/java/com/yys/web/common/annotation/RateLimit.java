package com.yys.web.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 28142
 * @description 限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * 限流的key前缀
     */
    String key() default "";

    /**
     * 限流次数，窗口内允许的最大请求数
     */
    int limit();

    /**
     * 限流时间，窗口大小
     */
    int window();

    /**
     * 时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 是否全局限流
     */
    boolean global() default false;
}
