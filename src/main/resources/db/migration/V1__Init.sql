create table users (
  id bigint comment '用户ID' auto_increment ,
  username varchar(100) comment '用户名',
  password varchar(100) comment '密码',
  password_salt varchar(100) comment '密码盐',
  constraint pk_users primary key (id)
) charset=utf8 engine=InnoDB;
create unique index idx_users_username on users(username);

create table user_roles(
  id bigint comment '用户角色ID' auto_increment,
  username varchar(100) comment '用户名',
  role_name varchar(100) comment '用户角色名',
  constraint pk_user_roles primary key (id)
) charset=utf8 engine=InnoDB;
create unique index idx_user_roles on user_roles(username, role_name);

create table roles_permissions(
  id bigint comment '用户权限ID' auto_increment,
  role_name varchar(100) comment '用户角色名',
  permission varchar(100) comment '用户权限名',
  constraint pk_roles_permissions primary key (id)
) charset=utf8 engine=InnoDB;
create unique index idx_roles_permissions on roles_permissions(role_name, permission);

-- Init User
insert into users (username, password) values ('Jack', '123456');