
# microservice-comb

## 前言
微服务已经在越来越多的企业开花。企业在享受微服务优势的同时，会产生一些问题。如随着企业的业务发展，相依的服务数量不断增加，服务调用关系越来越错综复杂。
本项目产生的意义就是为了收集和展示服务的调用关系，特别是服务中接口的调用关系。带来的价值为很好的避免了以往只能通过开发人员头脑记忆，要知道记忆是会减退的。所以利于准备评估后续需求开发涉及的影响面。
从而维护项目上线的稳定，增强服务可用性。所以，`微服务梳子`意在自动化理清微服务体系下的服务间关系调用

## 业务背景
微服务架构的流行，在业务分隔，服务复用，敏捷开发等方面带来了很大的飞跃。随着业务场景越来越丰富，业务领域越来越广泛，服务数量越来越庞大。随之，服务间调用越来越错综复杂。

## 微服务带来的问题
所以，当服务数量越来越庞大时，服务间调用越来越错综复杂。举一个很实际的例子：一个服务从诞生开始，随着业务的不断发展，服务接口数量和调用方都在不断增加，而开发人员又在不断变化。慢慢的，当前服务接口的被使用方有哪些就无人能说清了。
具体说，假设服务A有一个接口a(其实a被3个上游其他的服务调用)，但是当前维护a接口的人员可能是新来的，或是时间太过久远而不一定能准确的知道调用a接口的上游服务。这就带来了诸多不确定性风险

## 本项目能带来什么
为了准确的知道服务及其接口的调用方信息，本项目为此而出现。通过本项目，你可以准确的、实时的获取服务及其接口的被调用(使用)情况，从而帮助你准备的评估迭代需求的工作量，及时统计出更改所影响的上游服务方，拔高说增强了服务的可维护性和稳定性，提高了可用性。

## 核心目标
本项目意在提供简单快速的使用教程，真正的协助微服务架构模式下的服务维护成本，减少RD们对服务接口调动错乱的记忆，当然也告别前人留下的大坑。

## 架构图
![架构图](https://raw.githubusercontent.com/yaoyuanyy/MarkdownPhotos/master/img/20200116114413.png)

## 组件依赖
本项目基于spring cloud openfeign(目前版本:2.1.0.RELEASE)实现

## 实现原理
本项目分为三部分，一是调用信息收集及发送，二是接收并存储调用信息，三是图表展示微服务调用关系。调用信息的传递(发送和接收)采用的是`CQRS模型`

1. 调用信息收集及发送
这部分功能由`microservice-comb-infrastructure`模块负责。具体原理为调用信息收集采用的是动态代理，通过代理`LoadBalancerFeignClient`类，使得有机会在服务调用时收集调用信息；并通过`kafka`发送调用信息给接收方。收集的逻辑通过自定义的`InvocationHandler：LoadBalancerFeignClientInvocationHandler`完成，发送调用信息通过`MessageSender`类完成。

2. 接收并存储调用信息
这部分功能由`microservice-comb-server`模块负责。使用kafka接收调用信息，随之将信息持久化到硬盘，当前是保存到mysql数据库，后期会采用动态切换，支持mongodb等方式。

3. 图表展示微服务调用关系
这部分功能由`microservice-comb-admin`模块负责。使用js等图表组件展示服务调用关系图，通过检索服务名/接口名可以知道：A服务的a接口的调用方信息，什么时间调的，一段时间内调用的次数等。但由于本人还不熟悉前端技术，暂时搁置这个模块。


## 项目结构
|module|description|
|--|--|
|microservice-comb-admin|调用信息图表展示,核心组件|
|microservice-comb-base|基础工具包,核心组件|
|microservice-comb-infrastructure|调用信息收集及发送,超核心组件|
|microservice-comb-server|接收并保存调用信息,核心组件|
|microservice-comb-example|使用例子，模拟业务方服务|


特别说明：
```
microservice-comb-example包含三个module。
microservice-comb-server-a 模拟业务方服务，此模块引用microservice-comb-server-b-sdk
microservice-comb-server-b 模拟业务方服务
microservice-comb-server-b-sdk 模拟业务方服务sdk，此模块引用microservice-comb-infrastructure
```



## 使用实例

## 使用方式
使用很简单

总体分两步：
- 资源准备 
- 引用sdk

### 资源准备 
1. kafka。调用信息发送和接收采用的kafka，所以你需要准备好运行着的`kafka server`，并把`kafka`的配置信息换成你的`kafka server信息

2. mysql。你需要有`mysql数据库`。`database: server_info`，并建表
```
CREATE TABLE `server_invocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，自增',
  `from_application` varchar(100) NOT NULL DEFAULT '' COMMENT '服务调用方',
  `to_application` varchar(100) NOT NULL DEFAULT '' COMMENT '服务被调用方',
  `from_path` varchar(100) NOT NULL DEFAULT '' COMMENT '服务调用方接口路径path',
  `to_path` varchar(100) NOT NULL DEFAULT '' COMMENT '服务被调用方接口路径path',
  `method` varchar(32) NOT NULL DEFAULT '' COMMENT '请求方式: GET POST',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `utime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作人id',
  `create_name` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务调用信息表';
```
3. eureka
本项目是基于spring cloud体系，所以eureka是需要的

### 引用sdk
import源码后，通过mvn将microservice-comb-infrastructure打jar包, 然后引入到应用project中。将原来引用`spring-cloud-starter-openfeign`的模块或服务换成引用`microservice-comb-infrastructure`，如下
```
<dependency>
    <groupId>com.skyler.cobweb</groupId>
    <artifactId>microservice-comb-infrastructure</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```


## 模拟实战
为方便使用和接入。microservice-comb-example模块提供了模拟实际公司中的微服务架构模式，microservice-comb-example包含三个子模块，整体关系为
```
microservice-comb-server-a依赖microservice-comb-server-b-sdk，因为microservice-comb-server-a需要调用sdk的feign接口
microservice-comb-server-b依赖microservice-comb-server-b-sdk，因为microservice-comb-server-b的controller实现sdk的feign接口
microservice-comb-server-b-sdk依赖microservice-comb-infrastructure
```

1. 运行服务

依次运行如下服务
```
microservice-comb-server-a
microservice-comb-server-b
microservice-comb-server
```

2. 访问microservice-comb-server-a接口

`skyler@192 ~  curl -X GET 'localhost:9090/combo/getById?id=10'`

3. 查看数据库数据
如图所示

![20200315002513.png](https://raw.githubusercontent.com/yaoyuanyy/MarkdownPhotos/master/img/20200315002513.png)

## 模拟企业实际应用
microservice-comb-admin的功能负责展示数据库的服务间调用关系图。由于本人没有学习前端技术。效果暂时通过查询sql展示

### 查看微服务下多个服务间调用关系
![20200315002437.png](https://raw.githubusercontent.com/yaoyuanyy/MarkdownPhotos/master/img/20200315002437.png)


- 查询microservice-comb-server-b调用了哪些服务及接口
```
SELECT * FROM server_invocation WHERE from_application = 'microservice-comb-server-b' 
```
结果如下图，
![20200314235524.png](https://raw.githubusercontent.com/yaoyuanyy/MarkdownPhotos/master/img/20200314235524.png)

Note: 支持展示精确到调用方法上，附加条件查询就好

- 查询哪些服务及接口调用了microservice-comb-server-b
```
SELECT * FROM server_invocation WHERE to_application = 'microservice-comb-server-b' 
```
结果如下图
![20200314235605.png](https://raw.githubusercontent.com/yaoyuanyy/MarkdownPhotos/master/img/20200314235605.png)

