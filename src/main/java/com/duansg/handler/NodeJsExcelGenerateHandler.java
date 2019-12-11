package com.duansg.handler;

import com.duansg.base.ExcelGenerateHandler;
import com.duansg.constant.ResultConstant;
import com.duansg.po.ExcelPo;
import com.duansg.utils.ExcelUtil;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.stream.Stream;

public class NodeJsExcelGenerateHandler  extends ExcelGenerateHandler {

    public NodeJsExcelGenerateHandler(List<ExcelPo> poiList) {
        super(poiList);
    }

    @Override
    public String create() {
        if (CollectionUtils.isEmpty(poiList)){
            return ResultConstant.FAILED;
        }
        Map<String, List<String>> excelContext = new HashMap<>();
        Stream.iterate(0, i -> i + 1).limit(poiList.size()).forEach(i -> {
            String generateTime = poiList.get(i).getGenerateTime();
            String modelName = poiList.get(i).getModelName();
            String fileName = poiList.get(i).getFileName();
            String fileText = poiList.get(i).getFileText();
            excelContext.put(String.valueOf(i + 1),Arrays.asList(modelName,fileName,fileText,generateTime));
        });
        ExcelUtil.createExcel(excelContext);
        return ResultConstant.SUCCESS;
    }
}
