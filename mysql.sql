create table menus
(
    id        bigint       not null auto_increment comment '菜单编号',
    name      varchar(54)  not null comment '菜单名称',
    path      varchar(256) not null comment '菜单路径',
    authority tinyint      not null default 0 comment '权限级别:0.低;1.中;2.高;',
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
       (15, 'PASSWORD_ERROR', 3, 3, 'パスワードエラー！'),
       (16, 'PROPERTY_CONFLICT', 1, 1, '属性冲突！'),
       (17, 'PROPERTY_CONFLICT', 2, 2, 'Property conflict!'),
       (18, 'PROPERTY_CONFLICT', 3, 3, '属性の競合！'),
       (19, 'HOLIDAY_NOT_ENOUGH', 1, 1, '假期不足！'),
       (20, 'HOLIDAY_NOT_ENOUGH', 2, 2, 'Holiday not enough!'),
       (21, 'HOLIDAY_NOT_ENOUGH', 3, 3, '休みが足りない！');
create view view_messages as
select messages.id    as id,
       messages.code  as code,
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
    preparation bigint      not null comment '部门编制',
    status      tinyint     not null default 0 comment '部门状态:0.正常;1.关闭;',
    create_at   date        not null comment '成立日期',
    parent       bigint comment '上级部门',
    leader      bigint comment '部门领导',
    primary key (id),
    unique (name),
    index (parent),
    index (leader)
) comment '部门';
insert into departments (id, name, preparation, status, create_at, parent, leader)
values (1, '开发部', 100, 0, '2010-01-01', null, 1),
       (2, '人力部', 125, 0, '2010-01-01', null, 2),
       (3, '法务部', 100, 0, '2010-01-01', null, 3),
       (4, '财务部', 75, 0, '2010-01-01', null, 4),
       (5, '第一开发部', 100, 0, '2010-01-01', 1, 5),
       (6, '第二开发部', 125, 0, '2010-01-01', 1, 6);
create table positions
(
    id     bigint      not null auto_increment comment '岗位编号',
    name   varchar(64) not null comment '岗位名称',
    level  tinyint     not null comment '岗位级别',
    status tinyint     not null default 0 comment '岗位状态:0.正常;1.关闭;',
    primary key (id),
    unique (name)
) comment '岗位';
insert into positions (id, name, level, status)
values (1, '初级开发工程师', 1, 0),
       (2, '中级开发工程师', 2, 0),
       (3, '高级开发工程师', 3, 0);
create table genders
(
    id   bigint      not null auto_increment comment '性别编号',
    name varchar(64) not null comment '性别名称',
    primary key (id),
    unique (name)
) comment '性别';
insert into genders (id, name)
values (1, '男'),
       (2, '女');
create table employees
(
    id                 bigint      not null auto_increment comment '员工编号',
    name               varchar(64) not null comment '员工姓名',
    birthday           date        not null comment '员工生日',
    email              varchar(64) not null comment '员工邮箱',
    password           char(60)    not null comment '员工密码',
    authority          tinyint     not null default 0 comment '员工权限:1.普通;2.领导;3.管理;',
    induction          date        not null comment '入职时间',
    salary             bigint      not null comment '员工工资',
    transfer_vocations tinyint     not null default 0 comment '调休假',
    status             tinyint     not null default 0 comment '员工状态:0.在职;1.离职;',
    work_type          bigint      not null default 1 comment '员工工作类型',
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
insert into employees (id, name, birthday, email, password, authority, induction, salary, transfer_vocations, status,
                       work_type, gender, department, position, leader)
values (1, 'E1', '1990-01-01', 'e1@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 10000, 10, 0, 1, 1, 2, 1, null),
       (2, 'E2', '1990-01-01', 'e2@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 20000, 20, 0, 2, 2, 3, 2, null),
       (3, 'E3', '1990-01-01', 'e3@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 10000, 10, 1, 1, 1, 2, 1, null),
       (4, 'E4', '1990-01-01', 'e4@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 30000, 30, 0, 3, 2, 3, 3, null),
       (5, 'E5', '1990-01-01', 'e5@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 10000, 10, 0, 1, 1, 2, 1, null),
       (6, 'E6', '1990-01-01', 'e6@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 20000, 20, 1, 2, 2, 3, 2, null),
       (7, 'E7', '1990-01-01', 'e7@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 10000, 10, 0, 1, 1, 2, 1, null),
       (8, 'E8', '1990-01-01', 'e8@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2010-01-01', 30000, 30, 0, 3, 2, 3, 3, null);
create view view_departments as
select departments.id          as department_id,
       departments.name        as department_name,
       departments.preparation as department_preparation,
       departments.status      as department_status,
       departments.create_at   as create_at,
       parent.id                as parent_id,
       parent.name              as parent_name,
       employees.name          as leader
from departments
         left outer join departments as parent on departments.parent = parent.id
         left outer join employees on departments.leader = employees.id;
create table work_types
(
    id       bigint      not null auto_increment comment '工作类型编号',
    name     varchar(16) not null comment '工作类型名称',
    on_time  time        not null comment '上班时间',
    off_time time        not null comment '下班时间',
    primary key (id)
) comment '工作类型';
insert into work_types (id, name, on_time, off_time)
values (1, '8:30->17:30', '8:30', '17:30');
create table attendances
(
    id        bigint  not null auto_increment comment '考勤编号',
    employee  bigint  not null comment '考勤员工',
    clock_in  datetime comment '出勤时间',
    clock_out datetime comment '退勤时间',
    status    tinyint not null default 0 comment '考勤状态:1.考勤中;2.出勤;3.缺勤;4.审批;5.补签;6.矿工;',
    primary key (id),
    index (employee),
    index (clock_in)
) comment '考勤';
create view view_attendances_day as
select attendances.id        as id,
       employees.id          as employee_id,
       employees.name        as employee_name,
       attendances.clock_in  as clock_in,
       attendances.clock_out as clock_out,
       attendances.status    as status
from attendances
         left outer join employees on attendances.employee = employees.id
where date(clock_in) = curdate();
create view view_attendances_month as
select attendances.id        as id,
       employees.id          as employee_id,
       employees.name        as employee_name,
       attendances.clock_in  as clock_in,
       attendances.clock_out as clock_out,
       attendances.status    as status
from attendances
         left outer join employees on attendances.employee = employees.id
where year(clock_in) = year(curdate())
  and month(clock_in) = month(curdate());
create table supplements
(
    id          bigint      not null auto_increment comment '补签编号',
    employee    bigint      not null comment '补签员工',
    clock_in    datetime    not null comment '新出勤时间',
    clock_out   datetime    not null comment '新退勤时间',
    reason      varchar(16) not null comment '补签原因',
    is_filtered tinyint(1)  not null default false comment '补签状态:0.未处理;1.已处理;',
    primary key (id),
    index (employee),
    index (clock_in),
    index (is_filtered)
) comment '补签';
create view view_supplements as
select supplements.id        as id,
       employees.id          as employee_id,
       employees.name        as employee_name,
       attendances.clock_in  as clock_in_old,
       supplements.clock_in  as clock_in_new,
       attendances.clock_out as clock_out_old,
       supplements.clock_out as clock_out_new,
       supplements.reason    as reason
from supplements
         left outer join employees on supplements.employee = employees.id
         left outer join attendances on supplements.employee = attendances.employee
where supplements.is_filtered = false;
create table leaves
(
    id            bigint      not null auto_increment comment '假期类型编号',
    name          varchar(16) not null comment '假期类型名称',
    is_restricted tinyint(1)  not null comment '假期受限',
    primary key (id),
    index (is_restricted)
) comment '假期类型';
insert into leaves (id, name, is_restricted)
values (1, '病假', false),
       (2, '事假', true);
create table vocations
(
    id       bigint  not null auto_increment comment '休假编号',
    employee bigint  not null comment '休假员工',
    type     bigint  not null comment '假期类型',
    start    date    not null comment '开始时间',
    end      date    not null comment '结束时间',
    length   bigint  not null comment '休假长度',
    status   tinyint not null default 0 comment '休假状态:0.已申请;1.已批准;2.已拒绝;3.休假中;4.已结束;',
    primary key (id),
    index (employee),
    index (type)
) comment '休假';
create view view_vocations as
select vocations.id     as id,
       employees.id     as employee_id,
       employees.name   as employee_name,
       vocations.type   as type,
       vocations.start  as start,
       vocations.end    as end,
       vocations.length as length
from vocations
         left outer join employees on vocations.employee = employees.id
         left outer join work_types on vocations.type = work_types.id
where vocations.status = 0;
create table calendar
(
    id   date    not null comment '日历日期',
    type tinyint not null comment '日期类型:1.法定假日;2.法定调休日;3.普通休息日;4.普通工作日;',
    primary key (id),
    index (type)
) comment '日历';
create view view_employees as
select employees.id                                                                                      as id,
       employees.name                                                                                    as name,
       employees.email                                                                                   as email,
       employees.authority                                                                               as authority,
       work_types.name                                                                                   as work_type,
       genders.name                                                                                      as gender,
       departments.name                                                                                  as department,
       positions.name                                                                                    as position,
       leader.name                                                                                       as leader,
       case
           when employees.induction > date_sub(curdate(), interval 1 year) then 0
           when employees.induction > date_sub(curdate(), interval 10 year) then 5
           when employees.induction > date_sub(curdate(), interval 20 year) then 10
           else 15 end * if(month(curdate()) >= 3, 2, 3) + employees.transfer_vocations -
       coalesce(sum(if(leaves.is_restricted and vocations.status in (1, 3, 4), vocations.length, 0)), 0) as last
from employees
         left outer join work_types on employees.work_type = work_types.id
         left outer join genders on employees.gender = genders.id
         left outer join departments on employees.department = departments.id
         left outer join positions on employees.position = positions.id
         left outer join employees as leader on employees.leader = leader.id
         left outer join vocations on employees.id = vocations.employee
         left outer join leaves on vocations.type = leaves.id
group by employees.id;