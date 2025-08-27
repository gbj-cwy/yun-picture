package com.yys.web.common.aspect;

import com.yys.web.common.annotation.RateLimit;
import com.yys.web.common.enums.ErrorCode;
import com.yys.web.common.exception.BusinessException;
import com.yys.web.common.utils.ThrowUtils;
import com.yys.web.utils.RateLimiterUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author 28142
 * @description 限流切面
 * @date 2025/8/27 14:00
 */
@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final RateLimiterUtil rateLimiterUtil;

    private final HttpServletRequest request;

    private final ExpressionParser parser;

    @Around("@annotation(RateLimit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 方法
        Method method = signature.getMethod();
        // 限流注解
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        String key = rateLimit.key();

        // 支持SpEL动态参数
        if (key.contains("#")) {
            EvaluationContext context = new StandardEvaluationContext();
            Object[] args = joinPoint.getArgs();
            String[] parameterNames = signature.getParameterNames();
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
            key = parser.parseExpression(key).getValue(context, String.class);
        }

        // 如果不是全局限流，拼接上客户端IP
        if (!rateLimit.global()) {
            key = key + ":" + getClientIp(request);
        }

        // 是否允许访问
        boolean allowed = rateLimiterUtil.allowed(key, rateLimit.limit(),
                (int) rateLimit.unit().toSeconds(rateLimit.window()));
        ThrowUtils.throwIf(allowed, ErrorCode.FREQUENT_VISIT);
        return joinPoint.proceed();
    }

    /**
     * 获取客户端IP
     *
     * @param request
     * @return ip
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            ip = ip.split(",")[0];
        }
        return ip;
    }
}
