package com.duansg.route;

import com.alibaba.fastjson.JSON;
import com.duansg.base.ExcelGenerateHandler;
import com.duansg.base.FileRouteHandle;
import com.duansg.base.GlobalProperties;
import com.duansg.base.GlobalPropertiesContext;
import com.duansg.constant.FileConstant;
import com.duansg.constant.LogConstant;
import com.duansg.handler.NodeJsExcelGenerateHandler;
import com.duansg.po.ExcelNodeJsPo;
import com.duansg.po.ExcelPo;
import com.duansg.utils.CommonFileFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
        log.info(JSON.toJSONString(filesMap));
        log.info(String.format(LogConstant.LOG_TEXT,"文件清洗完毕......"));
        List<ExcelPo> poiList = new ArrayList<>();
        filesMap.forEach((filePrefix,files)->{
            files.forEach(file -> {
                //判断符合命名规范的文件
                if (file.getName().substring(0,1).equals(file.getName().substring(0,1).toUpperCase())&&!file.getName().startsWith(".")){
                    try (
                            InputStream is = new FileInputStream(file);
                            Reader reader = new InputStreamReader(is);
                            BufferedReader bufferedReader = new BufferedReader(reader);
                    ){
                        String line = null;
                        ExcelNodeJsPo excelNodeJsPo = new ExcelNodeJsPo();
                        StringBuilder stringBuilder = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            if (filePrefix.equals(FileConstant.HTTP_PREFIX)&&StringUtils.isNotBlank(line)){

                                if ( StringUtils.trim(line).startsWith("const")&&line.contains("MODULE")&&!line.contains("$")&&!StringUtils.trim(line).startsWith("const address")){//模块名称
                                    if (StringUtils.isBlank(excelNodeJsPo.getModelName())){
                                        excelNodeJsPo.setModelName(String.format(FileConstant.MODELNAME_PREFIX,line));
                                    }else {
                                        excelNodeJsPo.setModelName(excelNodeJsPo.getModelName() + "," + String.format(FileConstant.MODELNAME_PREFIX,line));
                                    }
                                }
                                stringBuilder.append(line);
                            }
                            if (filePrefix.equals(FileConstant.RPC_PREFIX)&&StringUtils.isNotBlank(line)){
                                //判断符合命名规范的文件
                                if (StringUtils.trim(line).startsWith(".")){
                                    continue;
                                }
                                if (!file.getName().substring(0,1).equals(file.getName().substring(0,1).toUpperCase())){
                                    continue;
                                }
                                excelNodeJsPo.setModelName(String.format(FileConstant.MODELNAME_PREFIX,file.getName().replace(".js","")));
                                stringBuilder.append(line);
                            }
                        }
                        excelNodeJsPo.setFileText(stringBuilder.toString());
                        excelNodeJsPo.setFileName(file.getName());
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        excelNodeJsPo.setGenerateTime(df.format(new Date()));
                        poiList.add(excelNodeJsPo);
                        log.info(String.format(LogConstant.LOG_TEXT,"文件【"+file.getName()+"】读取完毕......"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        return ExcelGenerateHandler.generate(new NodeJsExcelGenerateHandler(poiList));
    }
}
