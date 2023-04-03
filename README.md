# Mirai机器人sd-webui api调用插件


## 使用方法
- 已经部署了[stable-diffusion-webui](https://github.com/AUTOMATIC1111/stable-diffusion-webui)，使用`--api`启动，确认 [api](https://github.com/AUTOMATIC1111/stable-diffusion-webui/wiki/API) 可以访问
- 安装 [chat-command](https://github.com/project-mirai/chat-command) 插件（用于聊天时调用命令）
- 将本插件放入plugin中，启动，配置`/config/com.coderxi.mirai-sdwebui-client/config.yml`，如果你修改了配置，执行`/sd reload`
- 赋予一个账号命令使用权限 `/permission permit u账号 com.coderxi.mirai-sdwebui-client:command.sd` （[权限控制参考](https://docs.mirai.mamoe.net/console/Permissions.html#%E8%A2%AB%E8%AE%B8%E5%8F%AF%E4%BA%BA-id)）
- 输入`/sd ?`查看帮助

## 基本命令

### 输出可设置参数 args

```properties
/sd args txt2img
```
这会输出txt2img方法下所有可以设置的参数的 `参数名称 / 参数别名1 / 参数别名2 / ... （类型）`，如下
```txt
prompt / 正向提示词 / 正提示词 / 正向 / 正 / 阳（java.lang.String）
negative_prompt / negative_prompts / negative / negative prompts / 反提示词 / 反向提示词 / 反向 / 反 / 阴（java.lang.String）
steps / step / 步数 / 渲染步数 / 迭代步数 / 步长 / 渲染步长 / 迭代步长（java.lang.Integer）
```

### 文生图 txt2img

```properties
/sd txt2img
prompt=1girl,cute
negative=EasyNegative
steps=20
width=800
height=600
sampler=DPM++ 2M Karras
batch_size=2
```
参数名添加了别名，所以可以使用中文
```properties
/sd 文生图
正提示词=1girl,cute
反提示词=EasyNegative
渲染步数=20
图片宽度=800
图片高度=600
采样方法=DPM++ 2M Karras
渲染个数=2
```
**注**：采样方法列表可以通过`/sd samplers`或`/sd 采样方法列表`获取

### 图生图 img2img
和`txt2img`基本一样，唯一不同的是你需要贴个初始图片
```properties
/sd 图生图
[图片]
正提示词=1girl,cute
反提示词=EasyNegative
```
- `[图片]`可以加在任意位置
- 如果你是手机不方便的话，可以先不提供`[图片]`而直接发送命令和参数，机器人会提示你补充图片

### 模型管理 model (list/load)

- 查看当前模型：`/sd model` 或 `/sd 当前模型`
  ```
  anything-v4.0.ckpt [3b26c9c497]
  ```
- 查看模型列表：`/sd model list` 或 `/sd 模型列表`
  ```
  【0】anything-v4.0.ckpt [3b26c9c497]
  【1】anything-v4.5.ckpt [dd29dc980c]
  【2】cetusMix_Codaedition.safetensors [bd518b9aee]
  【3】chilloutmix_NiPrunedFp32Fix.safetensors [fc2511737a]
  ```
- **加载模型**：`/sd model load <模型名称>` 或 `/sd 加载模型 <模型名称>`
  ```
  /sd 加载模型 anything-v4.0.ckpt [3b26c9c497]
  ```
  **注意**：模型名称要使用 `名称 md5简写`，可以从 `/sd 模型列表` 复制
