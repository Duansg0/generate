package com.duansg.base;

import org.springframework.util.ObjectUtils;

/**
 * @author Duansg
 * @date 2019-12-09 10:56:13
 * @desc 配置文件上下文
 */
public class GlobalPropertiesContext {
    /**
     * 配置文件容器
     */
    private static final ThreadLocal<GlobalProperties> CONTEXT = new ThreadLocal<GlobalProperties>();

    /**
     * @desc 设置上下文
     * @param globalProperties
     */
    public static void setContext(GlobalProperties globalProperties) {
        if (!ObjectUtils.isEmpty(globalProperties)){
            CONTEXT.set(globalProperties);
        }
    }

    /**
     * @desc 获取上下文
     * @return
     */
    public static GlobalProperties getContext() {
        return CONTEXT.get();
    }

    /**
     * @desc 清理上下文
     */
    public static void clean() {
        CONTEXT.remove();
    }

}
