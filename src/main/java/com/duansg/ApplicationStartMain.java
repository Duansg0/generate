package com.duansg;

import com.duansg.annotation.ApplicationInitScan;
import com.duansg.annotation.GlobalFileHandleConfig;
import com.duansg.annotation.GlobalGitContextScan;
import com.duansg.config.ApplicationInitExcute;
import com.duansg.route.NodeJsFileHandle;
import org.apache.commons.lang3.StringUtils;

@ApplicationInitScan(scan = "conf.properties")
@GlobalGitContextScan
@GlobalFileHandleConfig(routeHandle = NodeJsFileHandle.class)
public class ApplicationStartMain {
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        ApplicationInitExcute.excute(ApplicationStartMain.class);
    }
}
