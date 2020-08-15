package com.wms.annotation;

import java.lang.annotation.*;

/**
 * 用户controller注入参数Employee
 * @author max.chen
 * @class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface Who {
}