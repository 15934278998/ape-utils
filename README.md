# ape-utils

## 前言

本文档仅介绍工具包引入方式和工具类所在包及名称。详细文档请访问：https://docs.ape9527.com/ape-utils/1.0.1/

## Maven依赖引入

```xml
<dependency>
    <groupId>com.ape9527</groupId>
    <artifactId>ape-utils</artifactId>
    <version>1.0.1</version>
</dependency>
```

## 工具类说明

| 所在包                      | 类名                  | 说明                                          |
| --------------------------- | --------------------- | --------------------------------------------- |
| `com.ape9527.utils.kaptcha` | `KaptchaUtil`         | 图片验证码工具类                              |
| `com.ape9527.utils.mail`    | `SendMailUtil`        | 邮件发送工具类                                |
| `com.ape9527.utils.openai`  | `OpenAiUtil`          | OpenAI工具类                                  |
| `com.ape9527.utils.oss`     | `MinioUtil`           | minio存储工具类                               |
| `com.ape9527.utils.oss`     | `AliOssUtil`          | 阿里云oss存储工具类                           |
| `com.ape9527.utils.oss`     | `QiNiuUtil`           | 七牛云存储工具类                              |
| `com.ape9527.utils.sms`     | `TencentSmsUtil`      | 腾讯云短信发送工具类                          |
| `com.ape9527.utils.sign`    | `AesUtil`             | AES加解密工具类                               |
| `com.ape9527.utils.sign`    | `Base64Util`          | Base64加解密工具类                            |
| `com.ape9527.utils.sign`    | `Md5Util`             | MD5加密工具类                                 |
| `com.ape9527.utils.date`    | `DateUtil`            | 时间工具类                                    |
| `com.ape9527.utils.file`    | `FileTypeUtil`        | 文件类型工具类                                |
| `com.ape9527.utils.http`    | `HttpsUtil`           | Https请求工具类                               |
| `com.ape9527.utils.http`    | `HttpUtil`            | Http请求工具类                                |
| `com.ape9527.utils.ip`      | `IpUtil`              | 获取IP工具类                                  |
| `com.ape9527.utils.ip`      | `AddressUtil`         | 获取地址工具类                                |
| `com.ape9527.utils.json`    | `JsonUtil`            | Json解析工具类                                |
| `com.ape9527.utils.math`    | `DoubleOperationUtil` | 浮点数运算工具类                              |
| `com.ape9527.utils.random`  | `AtomicCounterUtil`   | 原子计数工具类                                |
| `com.ape9527.utils.random`  | `RandomUtil`          | 随机数工具类                                  |
| `com.ape9527.utils.random`  | `SnowFlakeUtil`       | 雪花算法工具类                                |
| `com.ape9527.utils.random`  | `UuidUtil`            | UUID工具类                                    |
| `com.ape9527.utils.servlet` | `ServletUtil`         | 客户端工具类                                  |
| `com.ape9527.utils.spring`  | `SpringUtil`          | spring工具类 方便在非spring管理环境中获取bean |
| `com.ape9527.utils.string`  | `StringUtil`          | 字符串工具类                                  |

