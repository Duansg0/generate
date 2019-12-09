package com.duansg.annotation;

import java.lang.annotation.*;

/**
 * @author Duansg
 * @date 2019-12-09 10:33:21
 * @desc 应用初始化扫描
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Documented
@Inherited
public @interface ApplicationInitScan {
    /**
     * 配置文件
     * @return
     */
    String scan() default "";

}
