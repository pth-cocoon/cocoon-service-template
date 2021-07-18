# cocoon-service-template

基于 SpringBoot，MyBatisPlus，SpringSecurity实现的最基本的管理端模版


## 目录结构

├── common-core 核心依赖
├── common-utils 工具包
├── template-mbp-dao MyBatisPlus的封装
├── template-security 安全权限控制模版{核心}
└── template-system 一个简单的Demo，还不完善，暂不包含数据库实现，仅供参考

## template-security

### 核心文件

SecurityConfiguration 配置文件， 在简单的业务场景是够用的，是够用的，可以根据业务需要个性化实现;

* getWhiteList:一个抽象方法，描述了访问路径白名单。在此之内的路径，将不会校验权限;
* getLoginSuccessHandler: 登陆成功的处理器，自行处理登陆成功后的返回体；
* getLoginFailureHandler: 登陆失败的处理器，同上；
* 其余配置较简单，请参考代码注释阅读；

### 主要的Bean注册于: SecurityBeanConfig

主要的接口，均定义在service下，并在service.impl下生成了默认实现。根据自己业务需要作个性化实现。

现在已经实现的登陆方式：

1. 用户名+密码 登陆;
2. 手机号+短信 登陆;

如需其他登陆方式，需要补充 AuthenticationTokenFactory 的Token返回的实现，并配合自行实现的provider即可；
例如邮箱实现：

1. 需要修改 AuthenticationTokenFactory 返回的token。（在后续的版本将由修改改为拓展）
2. 根据SmsCodeAuthenticationProvider,实现自己的EmailProvider即可。

### 需要自行实现的Service

#### RbacService[核心]

hasPermission 函数决定了请求是否被放行。可自行从request取出请求信息，配合Authentication对象自行处理。默认实现，所有请求均通过。

#### VerificationCodeService 验证码服务

短信验证码接口，需要短信登陆可自行实现。

#### JwtService

本模版设计基础是Jwt，理论讲也支持其他token，前提：只要能支持JwtService即可。

### 重要的Filter

#### JwtFilter

用于通过Header的Authorization,取出token，判断用户是否过期，将用户信息封装到SecurityContextHolder;

#### LoginFilter

登陆的拦截器，主要实现为：

1. 拦截指定路径的请求；
2. 从请求的Body中获取到请求Json，封装成Map传递给AuthenticationTokenFactory,返回AbstractAuthenticationToken；
3. 将AbstractAuthenticationToken传递下去，交由provider处理；





### 写在最后：

本模版开发初衷是给自己写管理端提供方便，现在做了一定程度的可复用封装后进行开源。

目前可用的模块：template-security，其余模块暂不完善（虽然不是不能用，但是自己认为还没有到可以拿出来用的程度）。

依赖的Spring目前还基于2.3.0,后续调试通过后会逐渐更新。接受issue。



联系邮箱：6631097@qq.com

