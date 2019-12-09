package com.duansg.annotation;

import java.lang.annotation.*;

/**
 * @author Duansg
 * @date 2019-12-09 10:33:11
 * @desc 路由处理
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Documented
@Inherited
public @interface GlobalFileHandleConfig {
    /**
     * @desc 路由处理器配置
     * @return
     */
    Class<?> routeHandle();

}
