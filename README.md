# SteveAnimation Mod

> **SteveAnimation** 是一个基于 **Minecraft 1.21.1 Fabric** 和 [GeckoLib](https://github.com/bernie-g/geckolib) 的自定义玩家动画播放模组。该模组允许在特定条件下，服务端向客户端发送动画指令，客户端根据指令播放对应的玩家动画。

## 简介

**SteveAnimation** 模组展示了如何在 **Fabric** 环境下，利用 **GeckoLib** 为玩家实体添加和播放自定义动画。通过服务端与客户端之间的网络同步，模组能够在满足特定条件时触发动画效果，提升游戏的互动性和视觉表现。

主要功能包括：

- **动画同步**：服务端根据条件向客户端发送动画指令，客户端接收后播放对应动画。
- **渲染替换**：通过 **Mixin** 技术拦截原生玩家渲染逻辑，替换为自定义的动画渲染。
- **多动画支持**：支持多种动画状态，如 `IDLE`、`DAMAGED_BY_MACE`、`DAMAGED_BY_TNT` 等。
- **任务管理**：使用任务管理器控制动画的播放时长，并在动画结束后恢复玩家的正常渲染。

---

## 功能特性

1. **动画同步**
    - 服务端在特定条件下发送 `AnimationSyncPayload` 包含 `playerId` 和 `state` 到客户端。
    - 客户端根据接收到的 `state` 更新对应玩家的动画状态。

2. **玩家渲染替换**
    - 使用 `MixinPlayerEntityRenderer` 拦截原生 `PlayerEntityRenderer` 的 `render` 方法。
    - 判断玩家是否有动画需要播放，若是，则取消原渲染，转而渲染 `FakePlayerEntity`。

3. **多动画类型**
    - 支持多种动画状态，每种状态具有不同的动画时长和关联特效。
    - 当前支持的动画状态包括：
        - `IDLE`：默认待机动画。
        - `DAMAGED_BY_MACE`：被锤子攻击时的动画。
        - `DAMAGED_BY_TNT`：被TNT攻击时的动画。

4. **动画时长与恢复**
    - 通过 `TaskManager` 和 `PlayerPlayAnimTask` 控制动画的播放时长。
    - 动画结束后，自动恢复玩家的正常渲染状态。

---

## 安装与使用

### 1. 环境需求

- **Minecraft** 版本：1.21.1
- **Fabric Loader**：与 Minecraft 1.21.1 兼容的最新版本
- **Fabric API**：确保在 `mods` 目录下放置对应版本的 Fabric API
- **GeckoLib**：版本 4.7

### 2. 获取源码

```bash
git clone https://github.com/sanseyooyea/SteveAnimation.git
cd SteveAnim
```

### 3. 编译与运行

```bash
./gradlew build
````
然后将生成的 `SteveAnimation-1.0.0.jar` 文件以及 `Geckolib` 放置到 Minecraft 的 `mods` 目录下，启动游戏即可。

## 代码结构
``` bash
└─ main
    ├─ java
    │  └─ work
    │      └─ microhand
    │          └─ steveanim
    │              ├─ client
    │              │  ├─ model
    │              │  │  └─ entity
    │              │  ├─ network
    │              │  └─ renderer
    │              │      └─ entity
    │              ├─ common
    │              │  ├─ entity
    │              │  ├─ handler
    │              │  ├─ manager
    │              │  ├─ model
    │              │  ├─ network
    │              │  └─ task
    │              └─ mixin
    └─ resources
        └─ assets
            └─ steveanim
                ├─ animations
                │  └─ entity
                ├─ geo
                │  └─ entity
                └─ textures
                    └─ entity
```

目录说明：
- client
    - model：客户端模型类，用于加载和渲染模型。
    - network：客户端网络类，用于接收服务端发送的动画同步包。
    - renderer：客户端渲染类，用于替换原生玩家渲染逻辑。
- common
    - entity：实体类，包括 `FakePlayerEntity` 和 `PlayerEntity`。
    - handler：事件处理器，用于处理服务端和客户端的事件。
    - manager：任务管理器，用于管理动画播放任务。
    - model：模型类，用于加载和渲染模型。
    - network：网络类，用于定义和注册网络包。
    - task：任务类，用于定义动画播放任务。
- mixin：混淆类，用于拦截原生渲染逻辑。
- assets
    - animations：动画文件，用于定义实体的动画效果。
    - geo：模型文件，用于定义实体的模型结构。
    - textures：纹理文件，用于定义实体的贴图效果。

---

## 工作原理

![流程图.png](%E6%B5%81%E7%A8%8B%E5%9B%BE.png)

1. 服务端触发动画同步

    - 当满足特定条件（如玩家被攻击），服务端构建 AnimationSyncPayload 包含 playerId 和 state，并发送到客户端。

2. 客户端接收并处理同步包

    - 客户端接收到 AnimationSyncPayload 后，通过 PlayerAnimStateManager 更新对应玩家的动画状态。

3. Mixin 拦截渲染逻辑

    - MixinPlayerEntityRenderer 拦截 PlayerEntityRenderer 的 render 方法。
    - 判断玩家是否有动画需要播放：
        - 若有，取消原生渲染，渲染 FakePlayerEntity 及相关特效（如 TNTEntity、MaceEntity）。
        - 通过 TaskManager 设置动画结束后的回调，恢复玩家的正常渲染状态。

4. 动画播放与恢复

    - FakePlayerEntityRenderer 使用 GeckoLib 播放指定动画。
    - 动画播放完成后，任务管理器恢复玩家的正常渲染。

---    

## 常见问题

1. 为什么使用假玩家实体？
   - 直接修改玩家本体的渲染逻辑复杂且易出错，使用 FakePlayerEntity 替代渲染能够更简洁地实现动画效果，同时保留原生玩家的其他渲染特性（如盔甲、披风等）。

2. 多人服务器如何确保动画同步？
    - 动画同步依赖于服务端向客户端发送 AnimationSyncPayload。确保服务端在触发动画时正确发送同步包，客户端接收并处理后播放动画，即可实现多人同步。

3. 如何添加更多动画状态？
    - 在 PlayerAnimationState 枚举中添加新的动画状态。
    - 更新 AnimationSyncPayload 发送对应的 state。
    - 在 FakePlayerEntity 中为新状态添加对应的动画资源和播放逻辑。

---

## 贡献与许可

### 贡献
欢迎任何形式的贡献，包括但不限于：
- 提出问题（Issues）
- 提交改进建议或功能请求
- 创建 Pull Request 贡献代码

在贡献代码前，请确保遵循项目的编码规范，并详细描述你的更改内容和原因。

### 许可
本项目采用 GNU Affero General Public License Version 3 许可协议。
另声明，本项目只可用于非商业项目，如果需要商用，请向本人申请许可

---

## 致谢

感谢[AnECanSaiTin](https://github.com/AnECanSaiTin)提供的技术支持和指导

---

## 联系方式
如果您对 SteveAnim 模组有任何问题、建议或反馈，欢迎通过以下方式联系：
- QQ：1187586838
- Email：1187586838@qq.com
- [Github issue](https://github.com/sanseyooyea/SteveAnimation/issues)

感谢您对 SteveAnim 模组的关注与支持！
