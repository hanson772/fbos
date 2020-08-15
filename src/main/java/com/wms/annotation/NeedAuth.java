package com.wms.annotation;

import com.wms.bean.enums.ERole;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author max.chen
 * @class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface NeedAuth {
    ERole[] roles() default {}; // 默认非法用户
}

