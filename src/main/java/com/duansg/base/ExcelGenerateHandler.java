package com.duansg.base;

import com.duansg.po.ExcelPo;

import java.util.List;
import java.util.Map;
/**
 * @author Duansg
 * @date 2019-12-09 11:25:22
 * @desc Excel生成处理基类
 */
public abstract class ExcelGenerateHandler {
    /**
     * @desc 基本数据
     * @key 文件名称
     * @value 数据描述
     */
    public List<ExcelPo> poiList;

    /**
     * @desc Constructor
     * @param poiList
     */
    public ExcelGenerateHandler(List<ExcelPo> poiList) {
        this.poiList = poiList;
    }

    /**
     * @desc 生成执行入口
     * @param excelGenerateHandler
     */
    public static String generate(ExcelGenerateHandler excelGenerateHandler) {
        return excelGenerateHandler.create();
    }
    /**
     * @desc 抽象生成方法
     * @return
     */
    public abstract String create();
}
