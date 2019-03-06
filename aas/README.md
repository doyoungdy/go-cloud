#认证设计
* 客户端：

        公司web服务端应用;
        第三方原生APP;
       第三方WEB服务端应用;
        第三方单页应用 ;
* 认证流程：

    1.用户输入用户名及密码,完成认证。如果输入错误给予提示。 
* 异常处理设计

    1.对用户输入的认证信息给予相应的界面提示。

#授权设计
##授权码授权
* 客户端：

        公司web服务端应用;
        第三方原生APP;
        第三方WEB服务端应用;
* 授权流程：

    1.第三方web应用提供一个回调地址向我司申请client的id和password,公司为其分配帐号以线下邮件的方式发送给第三方。
    2.当第三方web应用要代理用户访问我司应用资源的时候，需要引导用户到我司的授权界面登录授权。
    3.用户通过界面完成认证以后,跳到授权界面，点击同意授权给第三方web相应资源访问权限以后,回调第三方web应用所提供的地址,将授权码回传给第三方。
    4.第三方回调地址在接收到授权码以后,调用token获取接口,获取token.
    5.第三方web应用需要将token与用户建立关联关系，当需要替用户访问我方应用资源的时候,携带token信息请求资源。
    (注：如果有freshtoken,需要根据timeout值,使用freshtoken更换token信息)
  
##隐式授权
* 客户端：
  
        第三方单页应用
* 授权流程：         
 
    1.第三方提供一个回调地址向我司申请client的id和password,公司为其分配帐号以线下邮件的方式发送给第三方。
    2.当第三方单页应用要代理用户访问我司应用资源的时候，需要引导用户到我司的授权界面登录授权。
    3.用户通过界面完成认证以后,跳到授权界面，点击同意授权给第三方web相应资源访问权限以后,回调第三方web应用所提供的地址,将Token直接回传给第三方。
    4.第三方单页应用需要将token存储在本地的缓存中,当需要替用户访问我方应用资源的时候,携带token信息请求资源。
  

##用户密码授权
* 客户端：
 
         公司单页应用
         公司原生APP
* 授权流程：         

    1.为用户提供认证界面,用户输入用户名密码.根据用户输入的用户名密码完成授权请求,获取Token信息

##客户端授权
* 客户端：
   
         公司后端应用服务
* 授权流程：         

    1.客户端根据客户端的ide及密码完成授权请求,获取Token信息

* 异常处理设计
       对授权相关的信息给予相关的提示
    
    
#授权测试

##授权码授权

###获取授权码
http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read

localhost:9001/callback?code=EWwgzZ
###
curl -X POST http://localhost:8080/oauth/token?grant_type=authorization_code&redirect_uri=http://localhost:9001/callback&scope=read&client_id=clientapp&client_secret=secret&code=EWwgzZ

##基于oauth2的隐式授权

###获取访问令牌
http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=token&scope=read&state=abc


##基于oauth2的客户端模式
###获取访问令牌
curl -X POST http://localhost:8080/oauth/token?client_id=clientapp&client_secret=secret&grant_type=client_credentials&scope=read


##基于oauth2的密码
###获取访问令牌
curl -X POST http://localhost:8080/oauth/token?username=admin&password=1111&client_id=clientapp&client_secret=secret&grant_type=password&scope=read