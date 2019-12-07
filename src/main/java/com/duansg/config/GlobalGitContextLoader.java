package com.duansg.config;

import com.duansg.base.GlobalProperties;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;

/**
 * @author Duansg
 * @date 2019-12-06
 * @desc
 */
public class GlobalGitContextLoader {

    private static CloneCommand cloneCommand;

    public static String create(GlobalProperties globalProperties) throws GitAPIException {
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
