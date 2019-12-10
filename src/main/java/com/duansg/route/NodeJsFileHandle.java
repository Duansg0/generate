package com.duansg.route;

import com.alibaba.fastjson.JSON;
import com.duansg.base.FileRouteHandle;
import com.duansg.base.GlobalProperties;
import com.duansg.base.GlobalPropertiesContext;
import com.duansg.constant.FileConstant;
import com.duansg.constant.LogConstant;
import com.duansg.utils.CommonFileFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Duansg
 * @date 2019-12-09 11:25:22
 * @desc NodeJs文件处理派生类
 */
@Slf4j
public class NodeJsFileHandle extends FileRouteHandle {
    /**
     * NodeJs文件处理器
     * @return
     */
    @Override
    public String excute() {
        GlobalProperties globalProperties = GlobalPropertiesContext.getContext();
        String filePath = globalProperties.getGlobalGitLocalFolderPath();
        String fileFilter = globalProperties.getFolderFiltersRetain();
        Map<String, List<File>> filesMap = CommonFileFilterUtil.getFilesMap(filePath,fileFilter);
        Map<String, List<Map<String,String>>> poiMap = new HashMap<>();
        log.info(JSON.toJSONString(filesMap));
        log.info(String.format(LogConstant.LOG_TEXT,"文件清洗完毕......"));
        filesMap.forEach((filePrefix,files)->{
            files.forEach(file -> {
                try (
                        InputStream is = new FileInputStream(file);
                        Reader reader = new InputStreamReader(is);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                ){
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                       if (filePrefix.equals(FileConstant.HTTP_PREFIX)&&StringUtils.isNotBlank(line)){
                           if ( StringUtils.trim(line).startsWith("const")&&line.contains("MODULE")&&!line.contains("$")&&!StringUtils.trim(line).startsWith("const address")){//模块名称
                               System.err.println(line);
                           }
                           if ( StringUtils.trim(line).startsWith("function")&&!line.contains("removeObjFn")){//前端服务方法
                               System.out.println(line);
                           }
                           if ( StringUtils.trim(line).startsWith("const address")){//服务端接口地址
                               System.out.println(line);
                           }
                       }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        return null;
    }
}
