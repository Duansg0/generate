package com.duansg.annotation;

import java.lang.annotation.*;

/**
 * @author Duansg
 * @date 2019-12-09 10:33:11
 * @desc git标记
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Documented
@Inherited
public @interface GlobalGitContextScan {

}
