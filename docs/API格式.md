# 后端接口定义

## 基本格式

```java
{
  "success" : true/false,  // 本次请求是否成功
  "code" : 0,  // 返回码, 默认是0表示成功
  "message" : "xxx",  // 请求失败时, 返回错误信息; 请求成功时, 可以没有这个字段或为空
  "data" : {
    ...  // 具体的数据, 格式是根据前端约定好的, 不一定是个对象, 也可能是数组/字符串之类的
  },
  "total" : 11  // 服务端返回的数据数量, 用于分页之类的, 如果没用的话可以不返回
}
```

注意:
* 服务端接口在任何情况下都返回200, 通过success字段判断是否成功, 不要返回4xx/5xx之类的错误
* 一般禁止3xx重定向
* 所有请求, 如果要传参数的, 一般用post, 否则用get
* 如果没有特殊说明，请求的`Content-Type`默认是`application/json`

## 用户相关接口

### 获取当前登录用户

* 接口名：`/api/getCurrentUser`
* 说明：获得当前登录的用户名，是否登录完全由后端判断
* 是否必需：是
* 输入：不需要任何参数
* 输出：如果当前用户未登录则返回success=false，例如`{"code":10,"data":null,"message":"not login yet","success":false,"total":null}`；已登录则返回登录的用户名，例如`{"code":0,"data":"guest","message":"","success":true,"total":null}`

### 获取验证码

- 接口名：`/api/getAuthCode`
- 说明：获得验证码图片
- 是否必需：是
- 输入：不需要任何参数
- 输出：返回验证码图片和cookie

### 登录

* 接口名：`/api/login`
* 说明：校验用户的登录信息，成功的话返回当前登录的用户名
* 是否必需：是
* 输入：request body是一个表单(`Content-Type=application/x-www-form-urlencoded`)，包含三个参数`username`/`password`/`authcode`，后端根据token验证验证码，同时验证用户名和密码
* 输出：成功的话返回登录后的用户名，例如：`{"code":0,"data":"guest","message":"","success":true,"total":null}`，否则返回错误信息。

### 注册

* 接口名：`/api/signup`
* 说明：验证工号和姓名，存在就写入信息，不存在就返回错误信息
* 是否必需：是
* 输入：request body是一个表单(`Content-Type=application/x-www-form-urlencoded`)，包含多个参数，后端根据token验证验证码，同时验证工号和姓名是否存在，存在就写入信息
* 输出：工号和姓名存在的话返回成功，例如：`{"code":0,"data":"guest","message":"","success":true,"total":null}`，否则返回错误信息“工号或姓名不存在，请联系管理员”。

### 登出

* 接口名：`/api/logout`
* 说明：注销当前用户，用户点击注销时浏览器会直接跳转到这个地址，由服务端控制后续的重定向等等。这样设计是因为使用SSO时logout逻辑很难统一，还是让后端自己去实现比较好。
* 是否必需：是
* 输入：无
* 输出：无
