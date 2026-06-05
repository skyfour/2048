# 2048 Number Game（控制台版）

这是一个基于 Java 的命令行数字消除小游戏（5x5 棋盘），核心逻辑在 `Work` 类中。

## 项目结构

- `/tmp/workspace/skyfour/2048/src/main/java/com/game/number/NumberApplication.java`：程序入口、回合流程、输入处理
- `/tmp/workspace/skyfour/2048/src/main/java/com/game/number/work/Work.java`：连通块查找、消除、下落、补全逻辑
- `/tmp/workspace/skyfour/2048/src/main/java/com/game/number/work/Utils.java`：初始化与随机数工具

## 运行环境

- JDK 8
- Maven Wrapper（项目已自带 `mvnw`）

## 快速开始

在仓库根目录执行：

```bash
cd /tmp/workspace/skyfour/2048
./mvnw test
./mvnw -DskipTests compile
java -cp target/classes com.game.number.NumberApplication
```

## 玩法说明

1. 游戏启动后会生成一个 `5 x 5` 的数字棋盘。
2. 系统会优先自动查找“上下左右连通且数字相同”的区域（数量至少为 3）。
3. 找到后会执行：
   - 清零该连通区域
   - 在其中一个位置生成更大的数字（+1）
   - 数字下落填补空位，再随机补全
   - 增加分数与可操作次数
4. 如果当前没有可自动消除的区域，玩家需要输入坐标：`i j`（例如 `1 3`）：
   - 对应位置数字 +1
   - 可操作次数 -1
5. 当可操作次数减为 0 时，游戏结束。

## 规则细节（按当前实现）

- 坐标是从 `0` 开始的行列索引。
- 分数按“消除基数 × 连通块大小”累加。
- 自动补全数字的范围与初始化范围略有差异（由代码中的参数控制）。
