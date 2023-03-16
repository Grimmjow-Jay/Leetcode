create table menu
(
    `id`          bigint      not null auto_increment primary key,
    `name`        varchar(32) not null unique key,
    `parent_id`   bigint
);


-- 首页
--    系统管理
--       复杂系统
--       一般系统
--       简单系统
--    业务管理
--       重要业务
--       无所谓业务
-- 其他页
--    关于页
--    感谢页

insert into menu(`name`, `parent_id`) values ('首页', null);
insert into menu(`name`, `parent_id`) select '系统管理', id from menu where `name` = '首页';
insert into menu(`name`, `parent_id`) select '复杂系统', id from menu where `name` = '系统管理';
insert into menu(`name`, `parent_id`) select '一般系统', id from menu where `name` = '系统管理';
insert into menu(`name`, `parent_id`) select '简单系统', id from menu where `name` = '系统管理';
insert into menu(`name`, `parent_id`) select '业务管理', id from menu where `name` = '首页';
insert into menu(`name`, `parent_id`) select '重要业务', id from menu where `name` = '业务管理';
insert into menu(`name`, `parent_id`) select '无所谓业务', id from menu where `name` = '业务管理';
insert into menu(`name`, `parent_id`) values ('其他页', null);
insert into menu(`name`, `parent_id`) select '关于页', id from menu where `name` = '其他页';
insert into menu(`name`, `parent_id`) select '感谢页', id from menu where `name` = '其他页';


with recursive recursive_menu as (
    select * from menu where `name` = '首页'
    union all
    select m.* from menu m inner join recursive_menu rm on m.parent_id = rm.id
)
select *
from recursive_menu;

-- 凭空创建数据
with recursive t_date (i, date) as (
    select 1, current_date
    union all
    select i + 1, date_sub(date, interval 1 day) from t_date where i < 7
)
select *
from t_date;