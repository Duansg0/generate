package com.duansg.utils;

import java.io.FileOutputStream;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.duansg.po.ExcelNodeJsPo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {

    public static final String[] strArray = { "模块名", "文件名称","文件内容" ,"生成时间" };

    /**
     * @功能：手工构建一个简单格式的Excel
     */
    public static void createExcel(Map<String, List<String>> map) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(40);// 默认列宽
        HSSFRow row = sheet.createRow((int) 0);//抬头行
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//首行居中
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        int i = 0;
        for (String str : map.keySet()) {
            row = sheet.createRow((int) i + 1);
            List<String> list = map.get(str);
            int rowNum = row.getRowNum();
            System.out.println(list.get(2).length());
            // 第四步，创建单元格，并设置值
            for (int j = 0; j < strArray.length; j++) {

                String s = list.get(j);
                if (StringUtils.isNotBlank(s)&&s.length()>32767){
                    System.out.println(s);
                }else {
                    row.createCell((short) j).setCellValue(s);
                }
            }
            i++;
        }

        //CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 3, 0, 0);
        //sheet.addMergedRegion(cellRangeAddress);
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("D:/Members.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
