create table sys_users (
  id       bigint auto_increment
  comment '系统用户ID',
  username varchar(100) comment '系统用户名',
  password varchar(100) comment '系统用户密码',
  salt     varchar(100) comment '密码盐',
  locked   bool   default false
  comment '是否被锁定',
  constraint pk_sys_users primary key (id)
)
  charset = utf8
  ENGINE = InnoDB
  comment '系统用户表';
create unique index idx_sys_users_username
  on sys_users (username);

create table sys_roles (
  id          bigint auto_increment
  comment '系统角色ID',
  role        varchar(100) comment '系统角色名称',
  description varchar(100) comment '系统角色描述',
  available   bool   default false
  comment '是否可用',
  constraint pk_sys_roles primary key (id)
)
  charset = utf8
  ENGINE = InnoDB
  comment '系统角色表';
create unique index idx_sys_roles_role
  on sys_roles (role);

create table sys_permissions (
  id          bigint auto_increment
  comment '系统权限ID',
  permission  varchar(100) comment '系统权限名',
  description varchar(100) comment '系统权描述',
  available   bool   default false
  comment '是否可用',
  constraint pk_sys_permissions primary key (id)
)
  charset = utf8
  ENGINE = InnoDB
  comment '系统权限表';
create unique index idx_sys_permissions_permission
  on sys_permissions (permission);

create table sys_users_roles (
  user_id bigint comment '系统用户ID',
  role_id bigint comment '系统角色ID',
  constraint pk_sys_users_roles primary key (user_id, role_id)
)
  charset = utf8
  ENGINE = InnoDB
  comment '系统用户角色关联表';

create table sys_roles_permissions (
  role_id       bigint comment '系统角色ID',
  permission_id bigint comment '系统权限ID',
  constraint pk_sys_roles_permissions primary key (role_id, permission_id)
)
  charset = utf8
  ENGINE = InnoDB
  comment '系统角色权限关联表';