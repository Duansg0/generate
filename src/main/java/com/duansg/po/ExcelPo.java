package com.duansg.po;

import lombok.Data;

import java.util.List;

@Data
public abstract class ExcelPo  {

    /**
     * @desc 模块名称
     */
    private String modelName;
    /**
     * @desc 文件名
     */
    private String fileName;
    /**
     * @desc 文件内容
     */
    private String fileText;
    /**
     * @desc 生成时间
     */
    private String generateTime;
}
