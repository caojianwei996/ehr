create table menus
(
    id        bigint       not null auto_increment comment '菜单编号',
    name      varchar(54)  not null comment '菜单名称',
    path      varchar(256) not null comment '菜单路径',
    authority tinyint      not null comment '权限级别:0.低;1.中;2.高;',
    primary key (id),
    unique (path)
) comment '菜单';
insert into menus
values (1, 'Index', '/', 0),
       (2, 'Information', '/information', 0),
       (3, 'Password', '/password', 0),
       (4, 'Attendance', '/attendance', 0),
       (5, 'Attendance-Applies', '/attendance/apply', 0),
       (6, 'Attendance-Record', '/attendance/record', 0),
       (7, 'Attendance-Approve', '/attendance/approve', 1),
       (8, 'Vocation-Applies', '/vocation/apply', 0),
       (9, 'Vocation-Records', '/vocation/record', 0),
       (10, 'Vocation-Approve', '/vocation/approve', 1),
       (11, 'Vocation-Type', '/vocation/type', 2),
       (12, 'Calendar', '/calendar', 2),
       (13, 'Schedule', '/schedule', 2),
       (14, 'Schedule-Type', '/schedule/type', 2),
       (15, 'Manage-Employee', '/manage/employee', 2),
       (16, 'Manage-Department', '/manage/department', 2),
       (17, 'Manage-Position', '/manage/position', 2);
create table languages
(
    id   bigint  not null auto_increment comment '语言编号',
    name char(2) not null comment '语言名称',
    primary key (id)
) comment '语言';
insert into languages (id, name)
values (1, 'zh'),
       (2, 'en'),
       (3, 'ja');
create table countries
(
    id   bigint  not null auto_increment comment '国家编号',
    name char(2) not null comment '国家名称',
    primary key (id)
) comment '国家';
insert into countries (id, name)
values (1, 'CN'),
       (2, 'US'),
       (3, 'JP');
create table messages
(
    id       bigint      not null auto_increment comment '消息编号',
    code     varchar(64) not null comment '消息代码',
    language bigint      not null comment '语言',
    country  bigint comment '国家',
    text     text comment '文本',
    primary key (id),
    unique (code, language, country),
    index (language),
    index (country)
) comment '消息';
insert into messages (id, code, language, country, text)
values (1, 'SUCCESS', 1, 1, '操作成功！'),
       (2, 'SUCCESS', 2, 2, 'Operation successfully!'),
       (3, 'SUCCESS', 3, 3, '操作に成功しました！'),
       (4, 'FAILURE', 1, 1, '操作失败！'),
       (5, 'FAILURE', 2, 2, 'Operation failed!'),
       (6, 'FAILURE', 3, 3, '操作に失敗しました！'),
       (7, 'TOKEN_INCORRECT', 1, 1, '令牌错误！'),
       (8, 'TOKEN_INCORRECT', 2, 2, 'Token incorrect!'),
       (9, 'TOKEN_INCORRECT', 3, 3, 'トークンエラー'),
       (10, 'ACCOUNT_NOT_EXIST', 1, 1, '账号不存在！'),
       (11, 'ACCOUNT_NOT_EXIST', 2, 2, 'Account not exist!'),
       (12, 'ACCOUNT_NOT_EXIST', 3, 3, 'アカウントが存在しません！'),
       (13, 'PASSWORD_ERROR', 1, 1, '密码错误！'),
       (14, 'PASSWORD_ERROR', 2, 2, 'Password error!'),
       (15, 'PASSWORD_ERROR', 3, 3, 'パスワードエラー！');
create view view_messages as
select messages.code  as code,
       languages.name as language,
       countries.name as country,
       messages.text  as text
from messages
         left outer join languages on messages.language = languages.id
         left outer join countries on messages.language = countries.id;
create table departments
(
    id          bigint      not null auto_increment comment '部门编号',
    name        varchar(64) not null comment '部门名称',
    leader      bigint      not null comment '部门领导',
    preparation bigint      not null comment '部门编制',
    status      tinyint     not null comment '部门状态',
    primary key (id),
    unique (name),
    index (leader)
) comment '部门';
create table positions
(
    id     bigint      not null auto_increment comment '岗位编号',
    name   varchar(64) not null comment '岗位名称',
    level  tinyint     not null comment '岗位级别',
    status tinyint     not null comment '岗位状态',
    primary key (id),
    unique (name)
) comment '岗位';
create table genders
(
    id   bigint      not null auto_increment comment '性别编号',
    name varchar(64) not null comment '性别名称',
    primary key (id),
    unique (name)
) comment '性别';
create table employees
(
    id                 bigint      not null auto_increment comment '员工编号',
    name               varchar(64) not null comment '员工姓名',
    email              varchar(64) not null comment '员工邮箱',
    password           char(60)    not null comment '员工密码',
    authority          tinyint     not null comment '员工权限',
    induction          date        not null comment '入职时间',
    transfer_vocations tinyint     not null comment '调休假',
    work_type          bigint      not null comment '员工工作类型',
    gender             bigint      not null comment '员工性别',
    department         bigint      not null comment '员工部门',
    position           bigint      not null comment '员工岗位',
    leader             bigint comment '员工领导',
    primary key (id),
    unique (name),
    index (work_type),
    index (gender),
    index (department),
    index (position),
    index (leader)
) comment '员工';
create view view_account as
select id, email, password
from employees;
create view view_departments as
select departments.id          as department_id,
       departments.name        as department_name,
       employees.name          as department_leader,
       departments.preparation as department_preparation,
       departments.status      as department_status
from departments
         left outer join employees on departments.leader = employees.id;
create table work_types
(
    id       bigint      not null auto_increment comment '工作类型编号',
    name     varchar(16) not null comment '工作类型名称',
    on_time  time        not null comment '上班时间',
    off_time time        not null comment '下班时间',
    primary key (id)
) comment '工作类型';
create view view_employees as
select employees.name      as employee_name,
       employees.email     as employee_email,
       employees.authority as employee_authority,
       work_types.name     as employee_work_type,
       genders.name        as employee_gender,
       departments.name    as employee_department,
       positions.name      as employee_position,
       leader.name         as employee_leader
from employees
         left outer join work_types on employees.work_type = work_types.id
         left outer join genders on employees.gender = genders.id
         left outer join departments on employees.department = departments.id
         left outer join positions on employees.position = positions.id
         left outer join employees as leader on employees.leader = leader.id;
create table attendances
(
    id        bigint not null auto_increment comment '考勤编号',
    employee  bigint not null comment '考勤员工',
    clock_in  datetime comment '出勤时间',
    clock_out datetime comment '退勤时间',
    primary key (id),
    index (employee),
    index (clock_in)
) comment '考勤';
create view view_attendances_day as
select employee, clock_in, clock_out
from attendances
where date(clock_in) = curdate();
create view view_attendances_month as
select employees.name        as employee,
       attendances.clock_in  as clock_in,
       attendances.clock_out as clock_out
from attendances
         left outer join employees on attendances.employee = employees.id
where year(clock_in) = year(curdate())
  and month(clock_in) = month(curdate());
create table supplements
(
    id        bigint not null auto_increment comment '补签编号',
    employee  bigint not null comment '补签员工',
    clock_in  datetime comment '新出勤时间',
    clock_out datetime comment '新退勤时间',
    status    tinyint comment '补签状态:0.已申请;1.已批准;',
    primary key (id),
    index (employee),
    index (clock_in),
    index (status)
) comment '补签';
create view view_supplements as
select employees.name        as name,
       attendances.clock_in  as clock_in_old,
       supplements.clock_in  as clock_in_new,
       attendances.clock_out as clock_out_old,
       supplements.clock_out as clock_out_new
from supplements
         left outer join employees on supplements.employee = employees.id
         left outer join attendances on supplements.employee = attendances.employee
where status = 0;
create table vocation_types
(
    id            bigint      not null auto_increment comment '假期类型编号',
    name          varchar(16) not null comment '假期类型名称',
    is_restricted tinyint(1)  not null comment '假期受限',
    primary key (id),
    index (is_restricted)
) comment '假期类型';
create table vocations
(
    id       bigint not null auto_increment comment '休假编号',
    employee bigint not null comment '休假员工',
    type     bigint not null comment '假期类型',
    start    date   not null comment '开始时间',
    end      date   not null comment '结束时间',
    length   bigint not null comment '休假长度',
    status   tinyint comment '休假状态:0.已申请;1.已批准;2.休假中;3.已结束;',
    primary key (id),
    index (employee),
    index (type)
) comment '休假';
create view view_vocations as
select employees.name                     as name,
       case
           when employees.induction > date_sub(curdate(), interval 1 year) then 0
           when employees.induction > date_sub(curdate(), interval 10 year) then 5
           when employees.induction > date_sub(curdate(), interval 20 year) then 10
           else 15 end * if(month(curdate()) >= 3, 2, 3) + employees.transfer_vocations -
       coalesce(sum(vocations.length), 0) as last
from vocations
         left outer join employees on vocations.employee = employees.id
         left outer join vocation_types on vocations.type = vocation_types.id
where vocations.status = 3
  and vocations.start between makedate(year(curdate()) - if(month(curdate() >= 3), 1, 2), 1) and curdate()
  and vocation_types.is_restricted = true
group by vocations.employee;
create table calendar
(
    id   date    not null comment '日历日期',
    type tinyint not null comment '日期类型:1.法定假日;2.法定调休日;3.普通休息日;4.普通工作日;',
    primary key (id),
    index (type)
) comment '日历';