# 员工管理系统

## 系统要求

### 项目说明

“员工管理系统”是为小型公司提供的一套关于员工信息，组织信息，职级信息和时间管理的一个信息化系统。该系统会部署在公司的内部网络中。

### 技术要求

- 架构：前后端分离
- 后端：SpringBoot整合Mybatis。
- 前端：Vue3

## 需求说明

- 员工登录
- 主页
- 查看个人信息
- 修改密码
- 时间管理
    - 考勤打卡
    - 考勤补签
    - 补签审核
    - 休假申请
    - 休假记录
    - 休假审批
    - 出勤记录
    - 日历设定
    - 审批人设定
    - 人员排班设定
    - 排班类型设定
    - 假期类型设定
- 员工信息管理
    - 新增员工
    - 修改员工
    - 调转员工
- 部门信息管理
    - 新增部门
    - 修改部门
    - 查看部门
- 职位信息管理
    - 新增职位
    - 修改职位
    - 查看职位

## 模块说明

- Application
    - 程序主口
- Common
    - 通用代码
- Generator
    - 代码生成
- System
    - 系统管理

## 接口文档

### 权限控制:

```http request
* /**
Authorization: jwt
```

### 员工登录

```http request
POST /api/users/login
Content-Type: application/json

{
  "data": {
    "username": "number&email",
    "password": "jakejake"
  }
}
```

- Authentication:`false`
- Required:`username` `password`
- Return:`User`
- Throws:`用户不存在` `密码错误`

### 重置密码

```http request
POST /api/users/reset
Content-Type: application/json

{
  "data": {
    "email": "email"
  }
}
```

- Authentication:`true`
- Required:`email`
- Throws:`用户不存在`

### 获取菜单

```http request
GET /api/menus
```

- Authentication:`true`
- Return:`Menu[]`

### 查看基本信息

```http request
GET /api/employees/basic
```

- Authentication:`true`
- Return:`Employee`

### 查看履历信息

```http request
GET /api/employees/resume
```

- Authentication:`true`
- Return:`Resume`

### 修改密码

```http request
PUT /api/users
Content-Type: application/json

{
  "data": {
    "oldPassword": "oldPassword",
    "newPassword": "oldPassword"
  }
}
```

- Authentication:`true`
- Required:`oldPassword` `newPassword`
- Return:`User`
- Throws:`密码错误`

### 获取时间

```http request
GET /api/attendances/time
```

- Authentication:`false`
- Return:`Attendance`

### 上班打卡

```http request
POST /api/attendances
Content-Type: application/json

{
  "data": {
    "time": "yyyy-MM-dd HH:mm:ss"
  }
}
```

- Authentication:`true`
- Required:`time`
- Return:`Record`
- Throws:`打卡时间异常`

### 下班打卡

```http request
PUT /api/attendances
Content-Type: application/json

{
  "data": {
    "time": "yyyy-MM-dd HH:mm:ss"
  }
}
```

- Authentication:`true`
- Required:`time`
- Return:`record`
- Throws:`打卡时间异常` `频繁打卡`

### 获取缺勤记录

```http request
GET /api/attendances
```

- Authentication:`true`
- Return:`Record[]`

### 考勤补签

```http request
POST /api/attendance
Content-Type: application/json

{
  "data": {
    "upTime": "yyyy-MM-dd HH:mm:ss",
    "downTime": "yyyy-MM-dd HH:mm:ss",
    "reason": ""
  }
}
```

- Authentication:`true`
- Required:`upTime` `downTime` `reason`
- Return:`record`
- Throws:`打卡时间异常`

### 获取补签申请

```http request
GET /api/attendances/apply
```

- Authentication:`true`
- Return:`Apply[]`

### 审批补签

```http request
POST /api/attendances/apply
Content-Type: application/json

{
  "data": {
    "agree": true,
    "list": []
  }
}
```

- Authentication:`true`
- Required:`agree` `list`

### 获取假期

```http request
GET /api/vacations
```

- Authentication:`true`
- Return:`number`

### 申请休假

```http request
POST /api/vacations/apply
Content-Type: application/json

{
  "data": {
    "type": number,
    "start": "yyyy-MM-dd",
    "end": "yyyy-MM-dd"
  }
}
```

- Authentication:`true`
- Required:`type` `start` `end`
- Throws:`假期不足`

### 审批休假

```http request
POST /api/vacations/apply
Content-Type: application/json

{
  "data": {
    "agree": true,
    "list": []
  }
}
```

- Authentication:`true`
- Required:`agree` `list`

### 月出勤记录

```http request
GET /api/attendances/month
```

- Authentication:`true`
- Return:`AbsenceRecord[]`

### 获取部门信息

```http request
GET /api/departments?limit=number&page=bumber
```

- Authentication:`true`
- Return:`Department[]`

### 新增部门

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "preparation": "preparation"
  }
}
```

- Authentication:`true`
- Required:`name` `preparation`
- Return:`Department[]`
- Throws:`名称冲突`

### 修改部门

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "leader: number,
    "preparation": "preparation",
    "status": number
  }
}
```

- Authentication:`true`
- Required:`name` `leader` `preparation` `status`
- Return:`Department[]`
- Throws:`名称冲突`

### 新增岗位

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "preparation": "preparation"
  }
}
```

- Authentication:`true`
- Required:`name` `preparation`
- Return:`Department[]`
- Throws:`名称冲突`

### 修改岗位

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "leader: number,
    "preparation": "preparation",
    "status": number
  }
}
```

- Authentication:`true`
- Required:`name` `leader` `preparation` `status`
- Return:`Department[]`
- Throws:`名称冲突`