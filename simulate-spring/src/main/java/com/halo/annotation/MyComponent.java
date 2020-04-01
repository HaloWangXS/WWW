package com.halo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被该注解标注, 代表着该类受容器管理。
 * @Retention(RetentionPolicy.RUNTIME) 该注解会在运行时一直保留
 * @Target({ElementType.TYPE}) 这个注解只可以标注在类上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MyComponent {
    /**
     * value属性是当前类在容器中的唯一标识id
     * @return
     */
    public String value();
}
