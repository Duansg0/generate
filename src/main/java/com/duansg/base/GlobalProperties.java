package com.duansg.base;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Duansg
 * @date 2019-10-06 17:05:32
 * @desc 全局配置类
 * @desc 成员变量命名要符号驼峰规则,且要与配置文件中的Key一一对应
 */
@Data
public class GlobalProperties implements Serializable {

    private static final long serialVersionUID = -5049445784729915295L;
    /**
     * git远程仓库地址
     * @desc 暂时只支持git
     */
    private String globalGitRmoteUrl;
    /**
     * 账户
     * @desc 远程仓库账号
     */
    private String globalGitUserName;
    /**
     * 密码
     * @desc 远程仓库密码
     */
    private String globalGitPassword;
    /**
     * 本地仓库存放目录
     * @desc 建议使用临时目录,不要存放在工程目录下
     */
    private String globalGitLocalFolderPath;
    /**
     * 远程分支
     * @desc 选择需要拉取的切换分支
     */
    private String globalGitPullBranch;
}
