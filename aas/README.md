基于spring-cloud实现SAAS技术框架，

功能模块

* 微服务技术组件模块：
* go-gateway 
微服务网关,基于Zuul组件实现访问路由功能，配置降低Ribbon的读取与连接超时上限。

* go-config
微服务配置服务，将微服务的配置信息统一管理。
暂时以文件存放，后期会考虑由svn或git来管理。

* go-registry
微服务注册发现服务组件。



* 微服务平台功能组件
* go-process
服务编排的微服务，如果有就将引入流程引擎来做服务编排。

* go-monitor
监控功能，监控信息如下：
1. 物理结点的资源使用状况，如：CPU，内存，硬盘，网络等，可以基于openshift做二次扩展来实现。
2. 服务的统计和监控，以监测服务的超时，熔断，拒绝，降级等行为。可以基于Hystrix来实现。
3. 服务的跟踪与日志聚合，其主要功能是聚集来自各个异构系统的实时监控数据，用来追踪微服务架构下的系统时延问题。可以基于zipkin来实现。

* go-manage
微服务管理界面模块，技术上可以使用VUE或angular来实现，主要管理以下信息：
1. 权限管理（用户，角色，资源）
2. 批处理管理
3. 

* go-auth
统一权限认证微服务，基于spring-security-oauth2来实现

* go-batch
通用批处理微服务


技术组件
* 基础技术组件
* going-adapter
各类报文转换的通用技术框架，报文结构可配置，报文格式可转换，如：xml,json,pojo

* going-utils
工具类

* going-exception
异常组件


* 微服务配置技术组件
* going-cloud-service
引入服务加载及鉴权控制的包，以及服务之间调用的封装包。


其它：
* 在线文档生成
可以基于swagger实现








