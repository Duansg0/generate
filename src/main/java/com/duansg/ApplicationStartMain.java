package com.duansg;

import com.duansg.base.GlobalProperties;
import com.duansg.config.GlobalGitContextLoader;
import com.duansg.config.GlobalPropertiesContextLoader;

public class ApplicationStartMain {

    public static void main(String[] args) throws Exception{
        GlobalProperties globalProperties = GlobalPropertiesContextLoader.create("conf.properties");
        GlobalGitContextLoader.create(globalProperties);

    }
}
