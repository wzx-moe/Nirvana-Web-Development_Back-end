README.md# 后端接口定义

## 基本格式

```json
{
  "success": true/false,
  // Request success 
  "code": 0,
  // Return code, 0 for success
  "message": "xxx",
  // Error message when failed; empty when success
  "data": {
    ...
    // Data, json or string or something
  },
  "total": 11
  // Data count, empty when unecessary
}
```

Attention:

* Server always return 200, 4xx or 5xx
* Prevent 3xx
* Only post and get, and only post when handle data
* request`Content-Type` is `application/json` by default

## User API

### 获取当前登录用户

* 接口名：`/api/getCurrentUser`
* 说明：获得当前登录的用户名，是否登录完全由后端判断
* 是否必需：是
* 输入：不需要任何参数
*

输出：如果当前用户未登录则返回success=false，例如`{"code":10,"data":null,"message":"not login yet","success":false,"total":null}`
；已登录则返回登录的用户名，例如`{"code":0,"data":"guest","message":"","success":true,"total":null}`

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
* 输入：request body是一个表单(`Content-Type=application/json`)，包含三个参数`username`/`password`
  /`authcode`
  ，后端根据token验证验证码，同时验证用户名和密码
* 输出：成功的话返回登录后的用户名，例如：`{"code":0,"data":"guest","message":"","success":true,"total":null}`，否则返回错误信息。

### 登出

* 接口名：`/api/logout`
* 说明：注销当前用户，用户点击注销时浏览器会直接跳转到这个地址，由服务端控制后续的重定向等等。这样设计是因为使用SSO时logout逻辑很难统一，还是让后端自己去实现比较好。
* 是否必需：是
* 输入：无
* 输出：无

### Get page content

* Name: `/api/page/{name}`
* Description: 
