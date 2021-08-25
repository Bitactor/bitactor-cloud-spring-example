# bitactor-cloud-spring-single-example
## 目录结构
```text
.
├─bitactor-cloud-spring-example-client                  # 示例测试客户端模块
├─bitactor-cloud-spring-example-cluster                 # 示例集群模块
│  │  
│  ├─bitactor-cloud-spring-example-cluster-gateway      # 示例集群模块的网关服
│  ├─bitactor-cloud-spring-example-cluster-service1     # 示例集群模块的服务1
│  └─bitactor-cloud-spring-example-cluster-service2     # 示例集群模块的服务2
├─bitactor-cloud-spring-example-proto                   # 示例通讯协议结构体（JSON）
└─bitactor-cloud-spring-example-single                  # 示例单服务模块
```
## 基础环境
* JDK 1.8+
* Maven3
* Springboot 2.5.2+

## 安装服务
* Nocos 2.0.0+ `如果仅测试单服务则不需要安装`

## 测试
### 注意
国内maven镜像有延迟，如果通过国内镜像无法下载相关依赖，可以直接访问中央仓库下载，也可以将 `bitactor-cloud-spring` clone 到本地安装
### 单服务测试流程
1. 启动 `启动服务[bitactor-cloud-spring-example-single]`
2. 启动 `启动服务[bitactor-cloud-spring-example-client ]`
3. 在 `bitactor-cloud-spring-example-client` 的控制台执行相关shell命令测试

### 集群测试流程
1. 启动 启动 nacos 服务
2. 启动 `启动服务[bitactor-cloud-spring-example-cluster]` 下的3个服务 无启动先后顺序
3. 启动 `启动服务[bitactor-cloud-spring-example-client ]`
4. 在 `bitactor-cloud-spring-example-client` 的控制台执行相关shell命令测试

### 测试shell
| 命令               | 描述           | 示例                       |
| ----------------- |:-------------: | -----------------------:  |
| connect [uid]     | 新建一个连接    | connect 1000              |
| connectors        | 展示连接信息    |   connectors              |
| add [uid] [a] [b] | 调用[uid]的连接 执行 a + b 的计算结果 |    add 1000 1 1|
| sub [uid] [a] [b] | 调用[uid]的连接 执行 a - b 的计算结果 |    sub 1000 1 1|
| mul [uid] [a] [b] | 调用[uid]的连接 执行 a * b 的计算结果 |    mul 1000 1 1|

