package com.duansg.config;

import com.duansg.base.GlobalProperties;
import com.duansg.base.GlobalPropertiesContext;
import com.duansg.exp.InitException;
import com.duansg.utils.CommonFileFilterUtil;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.util.ObjectUtils;

import java.io.File;

/**
 * @author Duansg
 * @date 2019-12-06
 * @desc GIT全局配置初始化构建类
 */
public class GlobalGitContextLoader {

    private static CloneCommand cloneCommand;

    public static String create() throws GitAPIException {
        GlobalProperties globalProperties = GlobalPropertiesContext.getContext();
        CommonFileFilterUtil.delete(globalProperties.getGlobalGitLocalFolderPath());
        if (ObjectUtils.isEmpty(globalProperties)){
            throw new InitException("配置读取为空!");
        }
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =
                new UsernamePasswordCredentialsProvider(globalProperties.getGlobalGitUserName(),globalProperties.getGlobalGitPassword());
        cloneCommand = Git.cloneRepository();
        /**
         * @desc
         * 这里实际没有拉取,只是克隆了远程仓库
         * 但是你可以使用这个git对象来进行你想要的操作
         */
        Git git = GlobalGitContextLoader.cloneCommand.setURI(globalProperties.getGlobalGitRmoteUrl())
                .setBranch(globalProperties.getGlobalGitPullBranch())
                .setDirectory(new File(globalProperties.getGlobalGitLocalFolderPath()))
                .setCredentialsProvider(usernamePasswordCredentialsProvider).call();
        return globalProperties.getGlobalGitLocalFolderPath();
    }

}
