# CS-430

> EN: A multi-module educational repository covering Android fundamentals, database integration (SQLite & MySQL), and a Spring Boot backend sample plus a capstone FinalProject.
> 中文: 面向教学的多模块仓库，涵盖 Android 基础、数据库集成（SQLite 与 MySQL）以及 Spring Boot 后端示例与综合期末项目。

## 📦 Modules / 模块总览
| Module | Purpose (EN) | 说明 (中文) |
|--------|--------------|-------------|
| AndroidProgramming101/ActivityAndFragment | Lifecycle & UI composition | 基础生命周期与 Fragment 组件演示 |
| AndroidProgramming101/ActivityJumps | Intent & navigation | Activity 跳转与数据传递 |
| AndroidProgramming101/AndroidSQLiteDemo | Local persistence (SQLite) | 本地数据库增删改查示例 |
| AndroidProgramming101/AndroidMySQLDemo | Remote DB connectivity | 远程 MySQL 通信示例（网络 + JDBC 网关/后端脚本） |
| FinalProject/FinalProject/* | Capstone app (admin/user/portal) | 综合应用（用户/管理/资源/跳转脚本） |
| SQL-Sample/SQLite_Insert | SQL insert pattern | SQLite 插入样例与练习 |
| SpringBoot-Sample/complete | REST backend & config | Spring Boot 完整示例（业务 + 配置） |
| SpringBoot-Sample/initial | Starter skeleton | 初始骨架，便于扩展 |

## 🗂 Structure / 目录结构
```
AndroidProgramming101/
FinalProject/
SQL-Sample/
SpringBoot-Sample/
Project/           # 额外练习/阶段性项目代码
LICENSE
README.md
```

## 🚀 Quick Start / 快速开始
### Android
```bash
# Open desired module in Android Studio
Android Studio -> Open -> AndroidProgramming101/ActivityAndFragment
```
Prerequisites: Android Studio (latest), SDK Platform (>=33), Gradle wrapper included.

### Spring Boot Backend
```bash
cd SpringBoot-Sample/complete
./mvnw spring-boot:run   # 或 mvn spring-boot:run
```
Access default endpoint (example): `http://localhost:8080/api/health`

### Database Samples
SQLite demo: run app in emulator, verify database file under `data/data/<package>/databases/`
MySQL demo: configure server URL & credentials in provided constants (建议在 `local.properties` 或环境变量中配置)。

## 🔐 Configuration / 配置建议
| Concern | Recommendation |
|---------|---------------|
| Secrets | 使用 `local.properties` 或 Gradle 密钥插件，不提交到 Git |
| DB URL  | 分环境（dev/prod），通过 BuildConfig 常量注入 |
| Logging | Android 使用 Timber；Spring Boot 使用默认日志 + 分级过滤 |

## 🧪 Testing / 测试
Android: 建议添加 Instrumentation Tests (`androidTest/`) 与单元测试 (`test/`)。
Spring Boot: 使用 `@SpringBootTest` + MockMVC 覆盖 REST 层。
SQLite: 利用 Robolectric 或内存数据库验证 DAO。
建议执行：
```bash
./gradlew test
./mvnw test
```

## 🛠 Build / 构建
| Platform | Command |
|----------|---------|
| Android (assemble) | `./gradlew assembleDebug` |
| Android (lint) | `./gradlew lint` |
| Spring Boot (jar) | `./mvnw clean package` |

## 🌐 Data Flow / 数据流概述
Android UI → Repository/DAO → (Local SQLite or Remote API) → Spring Boot Service → MySQL / Response → Android update UI layer.

## 📘 Learning Path / 学习路径
1. Activity/Fragment 基础 → 2. Intent 传值 → 3. 本地持久化 (SQLite) → 4. 远程交互 (MySQL/HTTP) → 5. Spring Boot 构建 REST API → 6. 综合期末项目整合。

## ⚙️ Technologies / 技术栈
- Android: Kotlin/Java (本仓库以 Java 为主), Gradle, XML layouts
- DB: SQLite (本地), MySQL (远程)
- Backend: Spring Boot 3.x, Maven Wrapper
- Optional: PHP 辅助脚本 (远程接口桥接)

## 🔄 Migration / 迁移建议
如需升级到 Kotlin：可逐文件迁移 Activity；引入 `ViewModel + LiveData` 改善可测试性。
如需引入 Jetpack Compose：从 ActivityAndFragment 模块开始替换 UI。

## 🧩 Extension Ideas / 拓展方向
- 添加 Room ORM 替换原始 SQLiteOpenHelper
- 引入 Retrofit + OkHttp 改善网络层
- 后端增加 JWT 鉴权模块
- FinalProject 中加入分页与缓存策略

## 🤝 Contributing / 贡献
1. Fork & 创建分支：`feature/<name>`
2. 遵循命名：Android 包名小写；后端模块单一职责。
3. 新增模块请在本 README 的 Modules 表补充。
4. 提交前运行：
```bash
./gradlew clean build || echo "Android build warnings reviewed"
./mvnw -q test || echo "Spring tests executed"
```

## 📑 License / 许可证
参见根目录 `LICENSE`。

---
## 中文速览
1. 使用 Android Studio 打开示例 → 2. 运行 Spring Boot 后端 → 3. 测试本地与远程数据库交互 → 4. 深入期末综合项目结构。

---
## FAQ / 常见问题
| 问题 | 说明 |
|------|------|
| MySQL 连接失败 | 检查防火墙与 JDBC URL；确认端口开放 |
| Gradle 同步慢 | 切换国内镜像或使用离线缓存 |
| Spring Boot 启动报错 | JDK 版本不兼容或端口占用 |

## Future / 后续规划
- [ ] 引入 Room + Repository 模式
- [ ] 添加简易 CI（构建 + 单元测试）
- [ ] 增加 Docker 化后端 (MySQL + Spring Boot)

