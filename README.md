# 药店药品进销存管理系统

一个基于 Spring Boot 3 + Java 21 + MySQL 的药店药品进销存管理系统，适用于药店日常进货、销售、库存管理和统计分析。

## 功能模块

- 药品信息管理
- 供应商管理
- 入库/出库记录
- 库存预警
- 销售统计
- API 接口文档（Swagger）

## 技术栈

- Java 21
- Spring Boot 3.3.4
- Spring Data JPA
- MySQL 8
- Maven
- Swagger / OpenAPI

## 目录结构

- `src/main/java/com/tom/pharmacy`：核心代码
- `src/main/resources/application.yml`：应用配置
- `docker-compose.yml`：MySQL 容器化启动配置
- `pom.xml`：Maven 项目配置

## 本地运行步骤

1. 克隆本仓库
   ```bash
   git clone https://github.com/Tom526852/pharmacy-inventory-system.git
   cd pharmacy-inventory-system
   ```

2. 启动 MySQL
   ```bash
   docker compose up -d mysql
   ```

3. 启动 Spring Boot 应用
   ```bash
   mvn clean package
   java -jar target/pharmacy-inventory-system-0.0.1-SNAPSHOT.jar
   ```

   或使用：
   ```bash
   mvn spring-boot:run
   ```

4. 访问系统 API 文档
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - API Docs: http://localhost:8080/v3/api-docs

## 默认数据库配置

- 数据库：`pharmacy_db`
- 用户名：`pharmacy`
- 密码：`pharmacy123`
- 地址：`localhost:3306`

## 核心接口示例

- GET `/api/drugs`：查询药品列表
- POST `/api/drugs`：新增药品
- GET `/api/suppliers`：查询供应商列表
- POST `/api/suppliers`：新增供应商
- POST `/api/inventory/inbound`：药品入库
- POST `/api/inventory/outbound`：药品出库
- GET `/api/inventory/alerts`：库存预警
- GET `/api/dashboard`：库存统计概览

## 建议扩展

- 增加用户权限与登录模块
- 增加前端管理界面（Vue / React / Element Plus）
- 增加药品权限、审批、报表导出功能
- 增加仓库/门店多仓库管理

## 说明

该项目已按药店进销存场景设计 API 层，便于在 VS Code 中直接开发与调试。
