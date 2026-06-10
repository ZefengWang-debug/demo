# demo
人工客服系统
本项目是我学习阶段的人工客服系统后端 demo。主要是Spring Boot + MySQL + JPA 的基础结构，前两周开发Shiro 权限框架和 Netty 简化通信功能。
# Reference Documentation
[Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
[Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
[Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)

# Guides
```[Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
```[Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
```[Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
```[Apache Shiro](https://shiro.apache.org/index.html)

# 本版本做了什么

Spring Boot + MySQL 项目，新增：
用户模块 User
客服模块 Customer
会话模块 ChatSession
消息持久化模块 ChatMessage
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
