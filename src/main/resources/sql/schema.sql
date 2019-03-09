create table if not exists users (
  username varchar(50) not null primary key,
  email varchar(50),
  password varchar(500),
  activated boolean default false,
  activationkey varchar(50) default null,
  resetpasswordkey varchar(50) default null
);

create table if not exists authorities (
  name varchar(50) not null primary key
);

create table if not exists users_authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username),
    foreign key (authority) references authorities (name)
);

create index if not exists users_authorities_idx_1 on users_authorities (username, authority);


create table if not exists oauth_access_tokens (
  token_id varchar(256) default null,
  token bytea,
  authentication_id varchar(256) default null,
  user_name varchar(256) default null,
  client_id varchar(256) default null,
  authentication bytea,
  refresh_token varchar(256) default null
);

create table if not exists oauth_refresh_tokens (
  token_id varchar(256) default null,
  token bytea,
  authentication bytea
);

create table if not exists  oauth_client_details (
  client_id varchar(255) primary key,
  resource_ids varchar(255),
  client_secret varchar(255),
  scope varchar(255),
  authorized_grant_types varchar(255),
  web_server_redirect_uri varchar(255),
  authorities varchar(255),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information varchar(4096),
  autoapprove varchar(255)
);

-- 
-- https://gist.github.com/fernandomantoan/3ff4b90d7e9eae4a5d1e
--
-- create table oauth_client_details (
--   client_id VARCHAR(256) PRIMARY KEY,
--   resource_ids VARCHAR(256),
--   client_secret VARCHAR(256),
--   scope VARCHAR(256),
--   authorized_grant_types VARCHAR(256),
--   web_server_redirect_uri VARCHAR(256),
--   authorities VARCHAR(256),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additional_information VARCHAR(4096),
--   autoapprove VARCHAR(256)
-- );
-- 
create table oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

create table oauth_code (
  code VARCHAR(256), authentication bytea
);

create table oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);