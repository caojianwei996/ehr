create table messages
(
    id       bigint      not null auto_increment comment '消息编号',
    code     varchar(64) not null comment '消息代码',
    language char(2)     not null comment '语言',
    text     text comment '文本',
    primary key (id),
    unique (code, language)
) comment '消息';
insert into messages (code, language, text)
values ('Information', 'zh', '个人信息'),
       ('Information', 'en', 'Information'),
       ('Information', 'ja', '個人情報'),
       ('Password', 'zh', '修改密码'),
       ('Password', 'en', 'Password'),
       ('Password', 'ja', 'パスワードの変更'),
       ('Attendance', 'zh', '考勤打卡'),
       ('Attendance', 'en', 'Attendance'),
       ('Attendance', 'ja', '勤務評定でカードを打つ.'),
       ('AttendanceApply', 'zh', '申请补签'),
       ('AttendanceApply', 'en', 'Attendance Apply'),
       ('AttendanceApply', 'ja', '欠勤補充'),
       ('AttendanceRecord', 'zh', '出勤记录'),
       ('AttendanceRecord', 'en', 'Attendance Record'),
       ('AttendanceRecord', 'ja', '出勤記録'),
       ('AttendanceApprove', 'zh', '补签审批'),
       ('AttendanceApprove', 'en', 'Attendance Approve'),
       ('AttendanceApprove', 'ja', '再署名承認'),
       ('VocationApply', 'zh', '申请请假'),
       ('VocationApply', 'en', 'Vocation Applies'),
       ('VocationApply', 'ja', '休暇を申請する'),
       ('VocationRecords', 'zh', '请假记录'),
       ('VocationRecords', 'en', 'Vocation Records'),
       ('VocationRecords', 'ja', '休暇届'),
       ('VocationApprove', 'zh', '请假审批'),
       ('VocationApprove', 'en', 'Vocation Approve'),
       ('VocationApprove', 'ja', '休暇の承認'),
       ('VocationType', 'zh', '假期类型'),
       ('VocationType', 'en', 'Vocation Type'),
       ('VocationType', 'ja', '休暇のタイプ'),
       ('Calendar', 'zh', '日历设定'),
       ('Calendar', 'en', 'Calendar'),
       ('Calendar', 'ja', 'カレンダー設定'),
       ('Schedule', 'zh', '排班设定'),
       ('Schedule', 'en', 'Schedule'),
       ('Schedule', 'ja', 'シフト設定'),
       ('ScheduleType', 'zh', '排班类型'),
       ('ScheduleType', 'en', 'Schedule Type'),
       ('ScheduleType', 'ja', 'シフトタイプ'),
       ('ManageEmployee', 'zh', '员工管理'),
       ('ManageEmployee', 'en', 'ManageEmployee'),
       ('ManageEmployee', 'ja', '従業員管理'),
       ('ManageDepartment', 'zh', '部门管理'),
       ('ManageDepartment', 'en', 'Manage Department'),
       ('ManageDepartment', 'ja', '部門管理'),
       ('ManagePosition', 'zh', '岗位管理'),
       ('ManagePosition', 'en', 'Manage Position'),
       ('ManagePosition', 'ja', 'ポジション管理'),
       ('SUCCESS', 'zh', '操作成功！'),
       ('SUCCESS', 'en', 'Operation successfully!'),
       ('SUCCESS', 'ja', '操作に成功しました！'),
       ('FAILURE', 'zh', '操作失败！'),
       ('FAILURE', 'en', 'Operation failed!'),
       ('FAILURE', 'ja', '操作に失敗しました！'),
       ('TOKEN_INCORRECT', 'zh', '令牌错误！'),
       ('TOKEN_INCORRECT', 'en', 'Token incorrect!'),
       ('TOKEN_INCORRECT', 'ja', 'トークンエラー'),
       ('ACCOUNT_NOT_EXIST', 'zh', '账号不存在！'),
       ('ACCOUNT_NOT_EXIST', 'en', 'Account not exist!'),
       ('ACCOUNT_NOT_EXIST', 'ja', 'アカウントが存在しません！'),
       ('PASSWORD_ERROR', 'zh', '密码错误！'),
       ('PASSWORD_ERROR', 'en', 'Password error!'),
       ('PASSWORD_ERROR', 'ja', 'パスワードエラー！'),
       ('PROPERTY_CONFLICT', 'zh', '属性冲突！'),
       ('PROPERTY_CONFLICT', 'en', 'Property conflict!'),
       ('PROPERTY_CONFLICT', 'ja', '属性の競合！'),
       ('HOLIDAY_NOT_ENOUGH', 'zh', '假期不足！'),
       ('HOLIDAY_NOT_ENOUGH', 'en', 'Holiday not enough!'),
       ('HOLIDAY_NOT_ENOUGH', 'ja', '休暇不足！');
create table menus
(
    id        bigint       not null auto_increment comment '菜单编号',
    name      varchar(54)  not null comment '菜单名称',
    path      varchar(256) not null comment '菜单路径',
    authority tinyint      not null default 0 comment '权限级别:0.低;1.中;2.高;',
    primary key (id),
    unique (path)
) comment '菜单';
insert into menus(name, path, authority)
values ('Information', 'information', 0),
       ('Password', 'password', 0),
       ('Attendance', 'attendance', 0),
       ('AttendanceApply', 'attendance-applies', 0),
       ('AttendanceRecord', 'attendance-record', 0),
       ('AttendanceApprove', 'attendance-approve', 1),
       ('VocationApply', 'vocation-applies', 0),
       ('VocationRecords', 'vocation-records', 0),
       ('VocationApprove', 'vocation-approve', 1),
       ('VocationType', 'vocation-type', 2),
       ('Calendar', 'calendar', 2),
       ('Schedule', 'schedule', 2),
       ('ScheduleType', 'schedule-type', 2),
       ('ManageEmployee', 'manage-employee', 2),
       ('ManageDepartment', 'manage-department', 2),
       ('ManagePosition', 'manage-position', 2);
create table departments
(
    id          bigint      not null auto_increment comment '部门编号',
    name        varchar(64) not null comment '部门名称',
    preparation bigint      not null comment '部门编制',
    status      tinyint     not null default 0 comment '部门状态:0.正常;1.关闭;',
    create_at   date        not null comment '成立日期',
    parent      bigint comment '上级部门',
    leader      bigint comment '部门领导',
    primary key (id),
    unique (name),
    index (parent),
    index (leader)
) comment '部门';
insert into departments (name, preparation, status, create_at, parent, leader)
values ('开发部', 100, 0, '2010-01-01', null, 1),
       ('第一开发部', 100, 0, '2010-01-01', 1, 2),
       ('第二开发部', 125, 0, '2010-01-01', 1, 3),
       ('第三开发部', 100, 0, '2010-01-01', 1, 4),
       ('第四开发部', 125, 0, '2010-01-01', 1, 5);
create table positions
(
    id     bigint      not null auto_increment comment '岗位编号',
    name   varchar(64) not null comment '岗位名称',
    level  tinyint     not null comment '岗位级别',
    status tinyint     not null default 0 comment '岗位状态:0.正常;1.关闭;',
    primary key (id),
    unique (name)
) comment '岗位';
insert into positions (name, level, status)
values ('初级开发工程师', 1, 0),
       ('中级开发工程师', 2, 0),
       ('高级开发工程师', 3, 0);
create table employees
(
    id                 bigint      not null auto_increment comment '员工编号',
    name               varchar(64) not null comment '员工姓名',
    gender             tinyint     not null comment '员工性别',
    birthday           date        not null comment '员工生日',
    email              varchar(64) not null comment '员工邮箱',
    password           char(60)    not null comment '员工密码',
    authority          tinyint     not null default 0 comment '员工权限:1.普通;2.领导;3.管理;',
    induction          date        not null comment '入职时间',
    salary             bigint      not null comment '员工工资',
    transfer_vocations tinyint     not null default 0 comment '调休假',
    status             tinyint     not null default 0 comment '员工状态:0.在职;1.离职;',
    work_type          bigint      not null default 1 comment '员工工作类型',
    department         bigint comment '员工部门',
    position           bigint comment '员工岗位',
    leader             bigint comment '员工领导',
    primary key (id),
    unique (name),
    index (work_type),
    index (department),
    index (position),
    index (leader)
) comment '员工';
insert into employees (name, gender, birthday, email, password, authority, induction, salary, transfer_vocations,
                       status, work_type, department, position, leader)
values ('E0', 0, '1990-01-01', 'e0@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 2,
        '2012-02-01', 50000, 30, 0, 1, null, null, null),
       ('E1', 1, '1990-01-01', 'e1@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 1,
        '2013-03-01', 35000, 20, 0, 1, 1, 3, 1),
       ('E2', 0, '1990-01-01', 'e2@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 1,
        '2014-04-01', 20000, 10, 0, 1, 2, 2, 2),
       ('E3', 1, '1990-01-01', 'e3@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 1,
        '2015-05-01', 20000, 10, 0, 1, 3, 2, 2),
       ('E4', 0, '1990-01-01', 'e4@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 1,
        '2016-06-01', 20000, 10, 0, 1, 4, 2, 2),
       ('E5', 1, '1990-01-01', 'e5@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 1,
        '2017-07-01', 20000, 10, 0, 1, 5, 2, 2),
       ('E6', 0, '1990-01-01', 'e6@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 1,
        '2018-08-01', 20000, 5, 1, 1, 6, 2, 3),
       ('E7', 1, '1990-01-01', 'e7@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 0,
        '2019-09-01', 10000, 5, 0, 1, 5, 1, 4),
       ('E8', 0, '1990-01-01', 'e8@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 0,
        '2020-10-01', 10000, 5, 1, 1, 6, 1, 5),
       ('E9', 1, '1990-01-01', 'e9@neusoft.com', '$2a$10$gQkw6nhAtat.mlJY92oo4.vfCo5sm7lmzwTaXxk//p6974lYxkxqi', 0,
        '2021-11-01', 10000, 5, 0, 1, 6, 1, 6);
create view view_departments as
select departments.id          as department_id,
       departments.name        as department_name,
       departments.preparation as department_preparation,
       departments.status      as department_status,
       departments.create_at   as create_at,
       parent.id               as parent_id,
       parent.name             as parent_name,
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
insert into work_types (name, on_time, off_time)
values ('8:30->17:30', '8:30', '17:30');
create table attendances
(
    id        bigint  not null auto_increment comment '考勤编号',
    employee  bigint  not null comment '考勤员工',
    clock_in  datetime comment '出勤时间',
    clock_out datetime comment '退勤时间',
    status    tinyint not null default 0 comment '考勤状态:0.考勤中;1.出勤;2.缺勤;3.审批;4.补签;5.矿工;',
    primary key (id),
    index (employee),
    index (clock_in)
) comment '考勤';
insert into attendances (employee, clock_in, clock_out, status)
values (1, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (2, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (3, '2025-01-13 08:30:00', null, 2),
       (4, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (5, null, null, 3),
       (6, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (7, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (8, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (9, '2025-01-13 08:30:00', '2025-01-13 17:30', 1),
       (10, '2025-01-13 08:30:00', '2025-01-13 17:30', 1);
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
    attendance  bigint      not null comment '签到编号',
    clock_in    datetime    not null comment '新出勤时间',
    clock_out   datetime    not null comment '新退勤时间',
    reason      varchar(16) not null comment '补签原因',
    is_filtered tinyint(1)  not null default false comment '补签状态:0.未处理;1.已处理;',
    primary key (id),
    index (attendance),
    index (clock_in),
    index (is_filtered)
) comment '补签';
insert into supplements (attendance, clock_in, clock_out, reason, is_filtered)
values (5, '2025-01-13 08:30:00', '2025-01-13 17:30:00', '忘记打卡', false);
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
         left outer join attendances on supplements.attendance = attendances.id
         left outer join employees on attendances.employee = employees.id
where supplements.is_filtered = false;
create table leaves
(
    id            bigint      not null auto_increment comment '假期类型编号',
    name          varchar(16) not null comment '假期类型名称',
    is_restricted tinyint(1)  not null comment '假期受限',
    primary key (id),
    index (is_restricted)
) comment '假期类型';
insert into leaves (name, is_restricted)
values ('病假', false),
       ('事假', true);
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
insert into vocations (employee, type, start, end, length, status)
values (6, 1, '2025-01-14', '2025-01-17', '4', 0),
       (7, 2, '2025-01-14', '2025-01-17', '4', 1);
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
       employees.gender                                                                                  as gender,
       departments.id                                                                                    as department_id,
       departments.name                                                                                  as department,
       positions.id                                                                                      as position_id,
       positions.name                                                                                    as position,
       leader.name                                                                                       as leader,
       employees.induction                                                                               as induction,
       employees.birthday                                                                                as birthday,
       case
           when employees.induction > date_sub(curdate(), interval 1 year) then 0
           when employees.induction > date_sub(curdate(), interval 10 year) then 5
           when employees.induction > date_sub(curdate(), interval 20 year) then 10
           else 15 end * if(month(curdate()) >= 3, 2, 3) + employees.transfer_vocations -
       coalesce(sum(if(leaves.is_restricted and vocations.status in (1, 3, 4), vocations.length, 0)), 0) as last
from employees
         left outer join work_types on employees.work_type = work_types.id
         left outer join departments on employees.department = departments.id
         left outer join positions on employees.position = positions.id
         left outer join employees as leader on employees.leader = leader.id
         left outer join vocations on employees.id = vocations.employee
         left outer join leaves on vocations.type = leaves.id
group by employees.id;
create table department_resume
(
    id         bigint not null auto_increment comment '履历编号',
    employee   bigint not null comment '员工编号',
    start      date   not null comment '起始时间',
    end        date comment '终止时间',
    department bigint not null comment '部门编号',
    primary key (id),
    index (department)
) comment = '部门履历';
insert into department_resume(employee, start, end, department)
values (2, '2024-12-01', '2024-12-31', 5),
       (2, '2025-01-01', null, 1);
create table position_resume
(
    id       bigint not null auto_increment comment '职位编号',
    employee bigint not null comment '员工编号',
    start    date   not null comment '起始时间',
    end      date comment '终止时间',
    position bigint not null comment '职位编号',
    primary key (id),
    index (position)
) comment = '职位履历';
insert into position_resume(employee, start, end, position)
values (2, '2024-12-01', '2024-12-26', 2),
       (2, '2024-12-28', null, 1);
create view view_department_resume as
select department_resume.id    as id,
       employees.id            as em_id,
       employees.name          as name,
       departments.name        as department,
       department_resume.start as START,
       department_resume.end   as END
from department_resume
         left outer join employees on department_resume.employee = employees.id
         left outer join departments on department_resume.department = departments.id;
create view view_position_resume as
SELECT position_resume.id    as id,
       employees.id          as em_id,
       employees.name        as name,
       positions.name        as position,
       position_resume.start as start,
       position_resume.end   as end
FROM position_resume
         left outer join employees on position_resume.employee = employees.id
         left outer join positions on position_resume.position = positions.id;
