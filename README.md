# demo
人工客服系统
本项目是我学习阶段的人工客服系统后端 demo。主要是Spring Boot + MySQL + JPA 的基础结构，前两周开发Shiro 权限框架和 Netty 简化通信功能。
# Reference Documentation
[Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
[Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
[Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)

# Guides
[Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
[Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
[Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
[Apache Shiro](https://shiro.apache.org/index.html)
https://blog.csdn.net/qq_41822345/article/details/107444270?ops_request_misc=elastic_search_misc&request_id=f2d2d4e1a8311830d2458c9436bc17e7&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~ElasticSearch~search_v2-1-107444270-null-null.142^v102^control&utm_term=SpringBoot%2BShiro%EF%BC%88%E7%94%A8%E6%88%B7%E8%A7%92%E8%89%B2%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86%E7%9A%84%E5%90%8E%E7%AB%AF%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0%EF%BC%89&spm=1018.2226.3001.4187



## 功能
本周主要做用户、角色、权限相关接口。
用户增删改查
角色增删改查
权限增删改查
用户绑定角色
角色绑定权限
Shiro 登录和权限判断

## 主要表
sys_user
sys_role
sys_permission
sys_user_role
sys_role_permission

# 本版本做了什么

Spring Boot + MySQL 项目，新增：
简化版 Netty TCP 通信
调研并接入 Apache Shiro，用于后续登录认证和权限控制
完成用户管理 CRUD：新增、查询、修改、删除
完成权限管理 CRUD：新增、查询、修改、删除
支持客户端发送消息后保存到 MySQL

# 相关技术
Java 17
Spring Boot 3.2.5
Maven
MySQL and Docker
Spring Data JPA
Apache Shiro 2.2.0
Netty 4.1.x

## 启动 Docker MySQL：

```powershell
docker start mysql8
docker ps
```

如果没有 mysql8，则创建：

```powershell
docker run -d --name mysql8 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=testdb -p 3307:3306 mysql:8.0
```
