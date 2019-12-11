package com.duansg.utils;

import com.duansg.constant.FileConstant;
import org.springframework.util.CollectionUtils;
import java.io.*;
import java.util.*;

/**
 * @author Duansg
 * @date 2019-12-09 15:58:22
 * @desc 公共文件处理工具
 */
public class CommonFileFilterUtil {

    public static Map<String, List<File>> filesMap = new HashMap<>();

    /**
     * 获取过滤的文件上下文
     * @param filePath
     * @param fileFilter
     * @return
     */
    public static Map<String, List<File>> getFilesMap(String filePath, String fileFilter) {
        File file = new File(filePath);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                getFilesMap(f.getAbsolutePath(),fileFilter);
            }
        }else{
            String[] filters = fileFilter.split(",");
            if(null != filters && filters.length>0){
                for (String filter : filters) {
                    String substring = filter.substring(filter.indexOf(":") + 1, filter.length());
                    if (file.getAbsolutePath().contains(substring)){
                        if (filter.startsWith(FileConstant.HTTP_PREFIX)){
                            addFile(file,filesMap,FileConstant.HTTP_PREFIX);
                        }else if (filter.startsWith(FileConstant.RPC_PREFIX)){
                            addFile(file,filesMap,FileConstant.RPC_PREFIX);
                        }
                    }
                }
            }
        }
        return filesMap;
    }

    /**
     * 添加文件到上下文
     * @param file
     * @param filesMap
     * @param prefix
     */
    private static void addFile(File file,Map<String, List<File>> filesMap, String prefix) {
        List<File> httpFiles = filesMap.get(prefix);
        if (CollectionUtils.isEmpty(httpFiles)){
            httpFiles = new ArrayList<>();
        }
        httpFiles.add(file);
        filesMap.put(prefix,httpFiles);
    }

    /**
     * 删除文件
     * @param filePath
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        deleteFileAll(file);
    }

    /**
     * 删除文件
     * @param file
     */
    private static void deleteFileAll(File file) {
        if (file.exists()) {
            File files[] = file.listFiles();
            int len = files.length;
            for (int i = 0; i < len; i++) {
                if (files[i].isDirectory()) {
                    deleteFileAll(files[i]);
                } else {
                    files[i].delete();
                }
            }
            file.delete();
        }
    }
}
