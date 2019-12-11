package com.duansg.config;

import com.duansg.annotation.ApplicationInitScan;
import com.duansg.annotation.GlobalFileHandleConfig;
import com.duansg.annotation.GlobalGitContextScan;
import com.duansg.base.ExcelGenerateHandler;
import com.duansg.base.FileRouteHandle;
import com.duansg.base.GlobalPropertiesContext;
import com.duansg.constant.LogConstant;
import com.duansg.exp.InitException;
import com.duansg.handler.LogoPrintHandler;
import com.duansg.po.ExcelPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author Duansg
 * @date 2019-12-09 10:35:33
 * @desc 服务初始化执行门面类
 */
@Slf4j
public class ApplicationInitExcute {
    /**
     * 服务执行入口
     * @param clazz
     */
    public static void excute(Class<?> clazz){
        try{
            LogoPrintHandler.print();
            ApplicationInitScan initScanAnnotation = clazz.getAnnotation(ApplicationInitScan.class);
            GlobalGitContextScan initGitScanAnnotation = clazz.getAnnotation(GlobalGitContextScan.class);
            GlobalFileHandleConfig routeHandleAnnotation = clazz.getAnnotation(GlobalFileHandleConfig.class);
            if (ObjectUtils.isEmpty(initScanAnnotation)){
                throw new InitException("未寻找到启动注解!");
            }
            if (ObjectUtils.isEmpty(routeHandleAnnotation)){
                throw new InitException("未寻找到处理器注解!");
            }
            if (StringUtils.isBlank(initScanAnnotation.scan())){
                throw new InitException("未寻找到配置文件!");
            }
            log.info(String.format(LogConstant.LOG_TEXT,"正在启动服务"));
            GlobalPropertiesContext.setContext(GlobalPropertiesContextLoader.create("conf.properties"));
            log.info(String.format(LogConstant.LOG_TEXT,"配置文件加载完毕"));
            if (!ObjectUtils.isEmpty(initGitScanAnnotation)){
                GlobalGitContextLoader.create();
                log.info(String.format(LogConstant.LOG_TEXT,"GIT仓库加载完毕"));
            }
            FileRouteHandle routeHandle = null;
            try{
                routeHandle = (FileRouteHandle) routeHandleAnnotation.routeHandle().newInstance();
                log.info(String.format(LogConstant.LOG_TEXT,"处理器加载完毕,逻辑执行中......"));
            }catch (Exception e){
                throw new InitException("路由处理器加载异常,请检查是否制定派生类!");
            }
            routeHandle.excute();
        }catch (InitException ie){
            throw ie;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            GlobalPropertiesContext.clean();
        }
    }
}
