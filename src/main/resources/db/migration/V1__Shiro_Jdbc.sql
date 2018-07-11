create table users (
  id       bigint comment '用户ID' auto_increment,
  username varchar(100) comment '用户名',
  password varchar(100) comment '密码',
  constraint pk_users primary key (id)
)
  charset = utf8
  engine = InnoDB
  comment '用户表';
create unique index idx_users_username
  on users (username);

-- Init User
insert into users (username, password) values ('Jack', '123456');