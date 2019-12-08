package com.duansg.config;

import com.duansg.base.GlobalProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

/**
 * @author Duansg
 * @date 2019-12-06
 * @desc 全局配置初始化构建类
 */
public class GlobalPropertiesContextLoader {

    /**
     * 全局配置类构建
     * @param localPropertiesPath
     * @return
     * @throws Exception
     */
    public static GlobalProperties create(String localPropertiesPath) throws Exception{
        Properties prop = new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(localPropertiesPath));
        Class clazz = Class.forName("com.duansg.base.GlobalProperties");
        GlobalProperties globalProperties = (GlobalProperties) clazz.newInstance();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            for (Method method : clazz.getMethods()) {
                if (!ObjectUtils.isEmpty(method) && StringUtils.isNotBlank(method.getName())){
                    if (converMethodName((String)entry.getKey()).equals(method.getName()))
                        method.invoke(globalProperties,entry.getValue());
                }
            }
        }
        return globalProperties;
    }

    /**
     * @desc 方法名称转换
     * @param key
     * @return
     */
    private static String converMethodName(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] keys = key.split("_");
        stringBuilder.append("set");
        for (int i = 0; i < keys.length; i++) {
            stringBuilder.append(keys[i].substring(0,1)).append(keys[i].substring(1,keys[i].length()).toLowerCase());
        }
        return stringBuilder.toString();
    }
}
