# CoWorking Platform - Setup Guide

## 环境要求

- **Java**: JDK 21 或 22
- **JavaFX**: 21.0.5 或 22
- **IDE**: Eclipse (推荐) 或 IntelliJ IDEA

---

## 安装步骤

### 1. 安装 Java JDK

- 下载：[Eclipse Temurin 21](https://adoptium.net/temurin/releases/?version=21)
- 选择对应操作系统和架构的版本

### 2. 下载 JavaFX SDK

- 访问：https://gluonhq.com/products/javafx/
- 选择版本：**21.0.5** 或 **22**
- 选择操作系统：Windows / macOS / Linux
- 架构：
  - macOS M 系列：**aarch64**
  - macOS Intel：**x64**
  - Windows/Linux：根据处理器选择
- 下载 **SDK** 版本

### 3. Eclipse 配置

#### 3.1 导入项目

1. File → Open Projects from File System
2. 选择 `Final` 文件夹
3. 点击 Finish

#### 3.2 配置 JavaFX 库

1. 右键项目 → Build Path → Configure Build Path
2. Libraries 标签
3. 删除所有报错的 JavaFX 库（红色叉号）
4. 点击 Add External JARs...
5. 选择你下载的 JavaFX SDK 中 `lib` 文件夹下的所有 JAR 文件
6. Apply and Close

#### 3.3 配置运行参数（重要）

1. Run → Run Configurations...
2. 选择或创建 Java Application → Main
3. **Arguments 标签**，在 VM arguments 中填入：
   ```
   --module-path "你的JavaFX路径/lib" --add-modules javafx.controls,javafx.fxml
   ```
   例如：
   - macOS: `--module-path "/Users/你的用户名/Downloads/javafx-sdk-21.0.5/lib" --add-modules javafx.controls,javafx.fxml`
   - Windows: `--module-path "C:\Users\你的用户名\Downloads\javafx-sdk-21.0.5\lib" --add-modules javafx.controls,javafx.fxml`
4. **JRE 标签**：选择 Java 21
5. Apply → Run

---

## 运行项目

### 方法 1：在 Eclipse 中运行（推荐）

1. 右键 `Main.java`
2. Run As → Java Application

### 方法 2：使用脚本（macOS，如果遇到窗口不显示问题）

1. 修改 `run-app` 脚本中的路径
2. 在终端运行：`./run-app`
3. 或在 Eclipse 中配置 External Tools

---

## 常见问题

### Q1: NoClassDefFoundError: javafx/application/Application

**原因**：VM arguments 没有配置
**解决**：按照 3.3 步骤配置 VM arguments

### Q2: UnsupportedClassVersionError: version 66.0

**原因**：用 Java 22 编译但用 Java 21 运行
**解决**：统一使用 Java 21 或 Java 22

### Q3: 窗口不显示（仅 macOS）

**原因**：Eclipse 在某些 macOS 版本上的已知问题
**解决**：使用 `run-app` 脚本，或配置 Eclipse External Tools

### Q4: Graphics Device initialization failed

**原因**：JavaFX SDK 架构不匹配
**解决**：

- M 系列 Mac 下载 **aarch64** 版本
- Intel Mac 下载 **x64** 版本

---

## 项目结构

```
Final/
├── src/application/        # 源代码
│   ├── Main.java          # 主程序入口
│   ├── LoginController.java
│   ├── RegisterController.java
│   ├── UserController.java
│   ├── AdminController.java
│   ├── *.fxml             # UI 界面文件
│   ├── data.csv           # 用户数据
│   ├── booking.csv        # 预订数据
│   └── userbooking.csv    # 用户预订关联
├── bin/                   # 编译输出（不要提交到 Git）
└── README.md             # 本文档
```

---

## 功能说明

### 用户功能

- 注册/登录
- 查看可用空间
- 预订工作空间
- 查看我的预订
- 账单管理

### 管理员功能

- 所有用户功能
- 用户管理
- 空间管理
- 预订管理
- 数据分析

---

## 开发团队

INFO 6205 - Fall 2025

---

## 许可证

MIT License
