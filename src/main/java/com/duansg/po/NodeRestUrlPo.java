package com.duansg.po;

import java.io.Serializable;

public class NodeRestUrlPo implements Serializable {

    private static final long serialVersionUID = 7196353134486674981L;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 方法名称
     */
    private String funcName;
    /**
     * 注释,有的没有.
     */
    private String desc;
    
}
