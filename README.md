# CS-430 Project README
## Project Overview
CS-430 is a collection of software development projects focused on Android application development, backend services, and database operations. The repository contains multiple sub-projects including:
- Android applications demonstrating core concepts (Activities, Fragments, SQLite/MySQL integration)
- A comprehensive final project with admin, database, and user components
- Spring Boot backend samples
- SQLite database operation examples
The project aims to provide educational resources for understanding mobile application development, database management systems, and backend services implementation.
## File Structure
├── AndroidProgramming101
│   ├── ActivityAndFragment
│   ├── ActivityJumps
│   ├── AndroidMySQLDemo
│   ├── AndroidSQLiteDemo
│   └── gradle
├── FinalProject
│   ├── FinalProject
│   │   ├── Admin
│   │   ├── Database
│   │   ├── JumpJS
│   │   ├── Portal
│   │   ├── Resources
│   │   └── User
│   └── Screenshot
├── SQL-Sample
│   └── SQLite_Insert
├── SpringBoot-Sample
│   ├── complete
│   └── initial
└── .gitignore
└── LICENSE
## Installation
1. **Android Projects**:
   - Install Android Studio (https://developer.android.com/studio)
   - Open each AndroidProgramming101/.../app directory with Android Studio
   - Ensure Android SDK is properly configured
2. **Spring Boot Projects**:
   - Install Java Development Kit (JDK 17+)
   - Install Maven (https://maven.apache.org)
   - Run `mvn spring-boot:run` in SpringBoot-Sample/complete directory
3. **General Requirements**:
   - Git for version control
   - Java 17+ runtime environment
   - Node.js (for JavaScript examples)
## Usage
1. **Android Applications**:
   - Open corresponding AndroidStudio project
   - Build and run on Android emulator/device
   - Key features include:
     - Activity/Fragment navigation
     - SQLite database operations
     - MySQL database integration
2. **Spring Boot Backend**:
   - Navigate to SpringBoot-Sample/complete
   - Run `mvn spring-boot:run` to start the server
   - Access REST APIs via browser or Postman
3. **Database Samples**:
   - SQLite_Insert contains SQLite database operation examples
   - MySQL demos include database connection and query implementations
## Dependencies
- Android SDK (for Android projects)
- Gradle (build system for Android projects)
- Maven (dependency management for Spring Boot)
- Java 17+ runtime
- Node.js (for JavaScript samples)
## Contribution Guidelines
1. **Code Contributions**:
   - Fork the repository
   - Create a new branch for each feature
   - Follow Android/Java coding standards
   - Include unit tests where applicable
2. **Documentation**:
   - Update README.md for new features
   - Add comments to complex code sections
   - Maintain consistent file naming conventions
3. **Testing**:
   - Run existing unit tests before submitting changes
   - Add new tests for implemented features
   - Verify database connection stability
## Technical Notes
- The project contains multiple independent modules that can be developed/tested separately
- Android projects use Gradle for build automation
- Spring Boot projects use Maven for dependency management
- Database operations include both SQLite (file-based) and MySQL (relational) implementations
- JavaScript samples are primarily for frontend demonstrations
## File Types
- **.java**: Android application code (33 files)
- **.php**: Backend scripts (15 files)
- **.js**: JavaScript examples (8 files)
- **.sql**: Database schema definitions (8 files)
- **.xml**: Resource files (99 files)
- **.webp/png**: UI assets (72 files)
- **.gradle**: Build configuration files (22 files)
## License
This project is licensed under the terms of the [LICENSE](LICENSE) file. Please review the license terms before using or modifying any portion of this code.

---

## 中文版本

# CS-430 项目文档
## 项目简介
## 安装方式
1. 使用 Android Studio 打开项目根目录
2. 通过 Gradle 构建依赖（各模块位于 `AndroidProgramming101`、`FinalProject` 等子目录）
3. Spring Boot 后端服务需单独运行（位于 `SpringBoot-Sample` 模块）
## 使用方法
### Android 模块
- **Activity 与 Fragment 示例**：运行 `AndroidProgramming101/ActivityAndFragment` 模块，演示基础组件生命周期与数据绑定
- **Activity 跳转示例**：通过 `AndroidProgramming101/ActivityJumps` 模块实现多 Activity 间的数据传递与导航
- **SQLite 操作**：在 `AndroidSQLiteDemo` 模块中查看数据库增删改查实现
- **MySQL 连接**：通过 `AndroidMySQLDemo` 模块演示 Android 与 MySQL 服务器的网络通信
### Spring Boot 后端
1. 进入 `SpringBoot-Sample/complete` 目录
2. 执行 `./mvnw spring-boot:run` 启动嵌入式 Tomcat 服务器
3. 通过 `/api/data` 端点进行数据交互（需配合 Android 模块使用）
## 项目结构说明
.
├── AndroidProgramming101
│   ├── ActivityAndFragment
│   ├── ActivityJumps
│   ├── AndroidMySQLDemo
│   └── AndroidSQLiteDemo
├── FinalProject
│   ├── FinalProject
│   └── Screenshot
├── Project
│   ├── 1
│   ├── 2
│   ├── 3
│   ├── 4
│   ├── 5
│   └── 7
├── SQL-Sample
│   └── SQLite_Insert
└── SpringBoot-Sample
    ├── complete
    └── initial
## 依赖项
- Android SDK（含 Architecture Components、Material Design）
- Spring Boot 2.x（用于后端服务）
- MySQL JDBC 驱动（`mysql-connector-java`）
- SQLite Android 库（`androidx.sqlite:sqlite`）
- PHP 7.x（用于部分脚本开发）
## 开发与贡献指南
1. 代码规范：遵循 Android 开发者指南与 Spring Boot 官方编码规范
2. 提交要求：
   - 新增功能需包含单元测试（位于 `test` 目录）
   - 修改现有功能需更新对应模块的 `README.md`
   - 跨模块依赖需在 `gradle.build` 文件中声明
3. 贡献流程：
   - Fork 项目仓库
   - 创建独立分支（`feature/xxx`）
   - 提交代码前运行 `./gradlew check` 验证
   - 提交 Pull Request 时说明变更内容
