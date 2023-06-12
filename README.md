# ape-utils

## 介绍

工具包

## Maven依赖引入

```xml
<dependency>
    <groupId>com.ape9527</groupId>
    <artifactId>ape-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 工具类说明

### 图片验证码工具类：KaptchaUtil

**属性**

| 修饰符 & 类型           | 属性名             | 说明           | 默认值       |
| ----------------------- | ------------------ | -------------- | ------------ |
| `public static boolean` | `BORDER`           | 是否有边框     | `false`      |
| `public static String`  | `BORDER_COLOR`     | 边框颜色       | `105,179,90` |
| `public static String`  | `TEXT_FONT_COLOR`  | 验证码字体颜色 | `blue`       |
| `public static Integer` | `TEXT_FONT_SIZE`   | 验证码字体大小 | `30`         |
| `public static String`  | `TEXT_FONT_NAMES`  | 验证码字体样式 | `宋体,楷体`  |
| `public static Integer` | `TEXT_CHAR_LENGTH` | 验证码字符长度 | `4`          |
| `public static Integer` | `IMAGE_WIDTH`      | 图片宽度       | `140`        |
| `public static Integer` | `IMAGE_HEIGHT`     | 图片高度       | `40`         |

**方法**

| 修饰符 & 返回值                     | 方法名 & 参数    | 说明                                                         |
| ----------------------------------- | ---------------- | ------------------------------------------------------------ |
| `public static Map<String, String>` | `getImageCode()` | 获取图片验证码。<br />返回值中包含如下两个字段：<br />text：验证码文本信息；<br />base64：生成图片的base64编码 |

### 邮件发送工具类：SendMailUtil

**方法**

| 修饰符 & 返回值         | 方法名 & 参数                                | 说明                           |
| ----------------------- | -------------------------------------------- | ------------------------------ |
| `public static boolean` | `sendMail(Sender sender, Receiver receiver)` | 发送普通邮件，返回发送是否成功 |

**参数说明**

- Sender: 发件人信息
  - host: 邮件服务器，eg: smtp.163.com
  - username: 邮箱号，需要与邮件服务器对应，eg: xxxx@163.com
  - password: 邮箱第三方登录授权码，可在邮箱设置中查看
  - port: 发送端口，一般使用465
  - protocol: 发送协议，一般使用smtp（需要在邮箱设置中开启smtp服务）
  - signature: 邮件签名，会在发送时添加到邮件标题和内容前
- Receiver: 收件人信息
  - mail: 收件人邮箱号
  - title: 邮件标题
  - content: 邮件内容

### OpenAI请求工具类：OpenAiUtil

**属性**

| 修饰符 & 类型          | 属性名     | 说明                                                         | 默认值 |
| ---------------------- | ---------- | ------------------------------------------------------------ | ------ |
| `public static String` | `BASE_URL` | OpenAI接口的请求地址，一般为https://api.openai.com，也可以设置为转发到如上地址的url |        |
| `public static String` | `API_KEY`  | OpenAI api key                                               |        |
| `public static String` | `MODEL`    | OpenAI gpt模型                                               |        |

**方法**

| 修饰符 & 返回值              | 方法名 & 参数 | 说明 |
| ---------------------------- | ------------- | ---- |
| `public static ChatResponse` |               |      |
