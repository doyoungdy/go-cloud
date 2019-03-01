#
##授权码授权

###获取授权码
http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read

localhost:9001/callback?code=F4FR7z
###
curl -X POST --user clientapp:1111 http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=8uYpdo&grant_type=authorization_code&redirect_uri=http://localhost:9001/callback&scope=read_userinfo"



##基于oauth2的隐式授权
http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=token&scope=read&state=abc


###获取访问令牌
http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=token&scope=read&state=abc

##基于oauth2的客户端模式
###获取访问令牌
curl -X POST "http://localhost:8080/oauth/token" --user clientapp:secret -d "grant_type=client_credentials&scope=read"


##基于oauth2的密码
###获取访问令牌
curl -X POST --user clientapp:secret http://localhost:8080/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=bobo&password=xyz&scope=read_userinfo"


password : $2a$10$8EllJVT55LkPKGuNTHGfkeA5T6g1l9FwjAlDr6UD2MZll59lAIEpW

