# Node to server request mapping
### 简介
        用来抽取Node.js请求到后端的路径映射
### 背景
        因我司前端服务请求到服务端路径没有做一致性的映射,导致开发人员在
        调试阶段无法通过浏览器调试模式来定位请求的接口及服务,所以这个项目
        就是用来生成这个映射表的.
### 配置
        GLOBAL_GIT_RMOTE_URL            ：  Git地址
        GLOBAL_GIT_USER_NAME            ：  账号
        GLOBAL_GIT_PASSWORD             ：  密码
        GLOBAL_GIT_LOCAL_FOLDER_PATH    ：  本地仓库地址
        GLOBAL_GIT_PULL_BRANCH          ：  需要拉取分支
### 注意
        目前笔者只对Node.js进行了抽取,当然也不排除在项目结构上有差异的
        项目,所以我们只需要配置抽取目录即可,我也非常欢迎各位前辈提出宝
        贵的意见.