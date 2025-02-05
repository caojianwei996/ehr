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
    - 程序主入口
- Attendance
    - 出勤管理
- Calendar
    - 日历管理
- Common
    - 通用代码
- Department
    - 部门管理
- Employee
    - 职工管理
- Generator
    - 代码生成器
- Position
    - 岗位管理
- System
    - 系统菜单管理
    - 国际化消息管理
- Vocation
    - 假期管理

## 接口文档

### 权限控制:

```http request
* /**
Authorization: Bearer jwt
```

### 获取当天考勤记录

```http request
GET /api/attendances/day
```

- Authentication:`true`
- Return:`AbsenceRecord`

### 获取当月出勤记录

```http request
GET /api/attendances/month
```

- Authentication:`true`
- Return:`AbsenceRecord[]`

### 考勤补签

```http request
POST /api/attendances/apply
Content-Type: application/json

{
  "data": {
    "upTime": "ISO 8601",
    "downTime": "ISO 8601",
    "reason": "string"
  }
}
```

- Authentication:`true`
- Required:`upTime` `downTime` `reason`
- Throws:`非法操作`

### 获取补签申请

```http request
GET /api/attendances/apply
```

- Authentication:`true`
- Return:`Apply[]`

### 审批补签

```http request
PUT /api/attendances/apply
Content-Type: application/json

{
  "data": {
    "id": "number",
    "agree": "boolean"
  }
}
```

- Authentication:`true`
- Required:`id` `agree`

### 获取时间

```http request
GET /api/sign/time
```

- Authentication:`false`
- Return:`Attendance`

### 上班打卡

```http request
POST /api/sign/on
Content-Type: application/json

{
  "data": "ISO 8601"
}
```

- Authentication:`true`
- Throws:`非法操作`

### 下班打卡

```http request
POST /api/sign/off
Content-Type: application/json

{
  "data": "ISO 8601"
}
```

- Authentication:`true`
- Throws:`非法操作`

### 获取日历

```http request
GET /api/calendar?start=yyyy-MM-dd&end=yyyy-MM-dd
```

- Authentication:`true`
- Return:`Page<Calandar>`

### 新建日历

```http request
POST /api/calendar
Content-Type: application/json

{
    "data": "yyyy-MM-dd"
}
```

- Authentication:`true`

### 修改日历

```http request
PUT /api/calendar
Content-Type: application/json

{
    "data": {
        "id": "yyyy-MM-dd",
        "type": "number"
    }
}
```

- Authentication:`true`

### 获取排班类型

```http request
GET /api/schedules?page=number&size=number
```

- Authentication:`true`
- Return:`Page<WorkType>`

### 新建排班类型

```http request
POST /api/schedules
Content-Type: application/json

{
    "data": {
        "name": "string",
        "onTime": "HH:mm:ss.SSS",
        "offTime": "HH:mm:ss.SSS"
    }
}
```

- Authentication:`true`

### 删除排班类型

```http request
DELETE /api/schedules/{id}
```

- Authentication:`true`

### 获取部门员工

```http request
GET /api/departments/employees/{departmentId}?page=number&limita=number
```

- Authentication:`true`
- Return:`Page<Employee>`

### 获取部门列表

```http request
GET /api/departments/search?name=string&status=number&page=number&limita=number
```

- Authentication:`true`
- Return:`Page<Department>`

### 获取部门信息

```http request
GET /api/departments/{id}
```

- Authentication:`true`
- Return:`Department`

### 新增部门

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "preparation": "number"
  }
}
```

- Authentication:`true`
- Required:`name` `preparation`
- Throws:`属性冲突`

### 修改部门

```http request
PUT /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "leader": "number",
    "preparation": "number",
    "status": "number"
  }
}
```

- Authentication:`true`
- Required:`name` `leader` `preparation` `status`
- Throws:`属性冲突`

### 新增员工

```http request
POST /api/employee
Content-Type: application/json

{
  "data": {
    "name": "string",
    "email": "email",
    "gender": "number",
    "birthday": "yyyy-MM-dd",
    "salary": "number",
    "authority": "number",
    "department": "number",
    "position": "number",
    "attendance": "number"
  }
}
```

- Authentication:`true`
- Required:`name` `email` `gender` `birthday` `salary` `authority` `department` `position` `attendance`
- Throws:`属性冲突`

### 修改员工

```http request
PUT /api/employee
Content-Type: application/json

{
  "data": {
    "id": "number"
    "name": "string",
    "email": "email",
    "gender": "number",
    "birthday": "yyyy-MM-dd",
    "salary": "number",
    "authority": "number",
    "attendance": "number",
    "status": "number"
  }
}
```

- Authentication:`true`
- Required:`id`
- Throws:`属性冲突`

### 查看基本信息

```http request
GET /api/employees/basic
```

- Authentication:`true`
- Return:`Employee`

### 查看部门履历信息

```http request
GET /api/employees/resume/department
```

- Authentication:`true`
- Return:`Resume`

### 查看职位履历信息

```http request
GET /api/employees/resume/position
```

- Authentication:`true`
- Return:`Resume`

### 员工调转部门

```http request
POST /api/employee/reverse/department
Content-Type: application/json

{
  "data": "string"
}
```

- Authentication:`true`
- Required:`id`

### 员工调转岗位

```http request
POST /api/employee/reverse/position
Content-Type: application/json

{
  "data": "string"
}
```

- Authentication:`true`
- Required:`id`

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
  "data": "email"
}
```

- Authentication:`false`
- Throws:`用户不存在`

### 修改密码

```http request
POST /api/users/update
Content-Type: application/json

{
  "data": {
    "oldPassword": "oldPassword",
    "newPassword": "newPassword"
  }
}
```

### 获取岗位列表

```http request
GET /api/positions?name=string&status=number&page=number&limita=number
```

- Authentication:`true`
- Return:`Page<Position>`

### 获取岗位员工

```http request
GET /api/positions/employees/{departmentId}?page=number&limita=number
```

- Authentication:`true`
- Return:`Page<Employee>`

### 获取岗位信息

```http request
GET /api/positions/{id}
```

- Authentication:`true`
- Return:`Position`


### 新增岗位

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "preparation": "number"
  }
}
```

- Authentication:`true`
- Required:`name` `preparation`
- Throws:`属性冲突`

### 修改岗位

```http request
POST /api/departments
Content-Type: application/json

{
  "data": {
    "name": "string",
    "leader": "number",
    "preparation": "number",
    "status": "number"
  }
}
```

- Authentication:`true`
- Required:`name` `leader` `preparation` `status`
- Throws:`属性冲突`

### 获取菜单

```http request
GET /api/menus
```

- Authentication:`true`
- Return:`Menu[]`

- Authentication:`true`
- Required:`oldPassword` `newPassword`
- Throws:`密码错误`

### 获取假期类型

```http request
GET /api/vacations/types/{id}
```

- Authentication:`true`
- Return:`Leave`

### 获取假期类型列表

```http request
GET /api/vacations/types
```

- Authentication:`true`
- Return:`Leave[]`

### 新增假期类型

```http request
POST /api/vacations/types
Content-Type: application/json

{
    "data": {
        "name": "string",
        "restricted": "boolean"
    }
}
```

- Authentication:`true`

### 修改假期类型

```http request
PUT /api/vacations/types
Content-Type: application/json

{
    "data": {
        "id": "number",
        "name": "string",
        "restricted": "boolean"
    }
}
```

- Authentication:`true`

### 删除假期类型

```http request
DELETE /api/vacations/types?id=number
```

- Authentication:`true`

### 获取剩余假期

```http request
GET /api/vacations/last
```

- Authentication:`true`
- Return:`number`

### 获取假期

```http request
GET /api/vacations?page=number&size=number
```

- Authentication:`true`
- Return:`Apply[]`

### 申请休假

```http request
POST /api/vacations/apply
Content-Type: application/json

{
  "data": {
    "type": "number",
    "start": "ISO 8601",
    "end": "ISO 8601"
  }
}
```

- Authentication:`true`
- Required:`type` `start` `end`
- Throws:`假期不足`

### 获取休假申请

```http request
GET /api/vacations/apply
```

- Authentication:`true`
- Return:`Apply[]`

### 审批休假

```http request
PUT /api/vacations/apply
Content-Type: application/json

{
  "data": {
    "id": "number",
    "agree": "boolean"
  }
}
```

- Authentication:`true`
- Required:`id` `agree`
