delete from oauth_client_details;


-- password: 123456
insert into users (username, email, password, activated)
select * from (
    select 'admin', 'admin@admin.com', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true
) AS tmp
where not exists (
    select username from users where username = 'admin'
) limit 1;

insert into authorities (name)
select * from (select 'ROLE_USER') AS tmp
where not exists (
    select name from authorities WHERE name = 'ROLE_USER'
) limit 1;

insert into authorities (name)
select * from (select 'ROLE_ADMIN') AS tmp
where not exists (
    select name from authorities WHERE name = 'ROLE_ADMIN'
) limit 1;

insert into users_authorities (username, authority)
select * from (select 'admin', 'ROLE_USER') AS tmp
where not exists (
    select username, authority from users_authorities where username = 'admin' and authority = 'ROLE_USER'
) limit 1;

insert into users_authorities (username, authority)
select * from (select 'admin', 'ROLE_ADMIN') AS tmp
where not exists (
    select username, authority from users_authorities WHERE username = 'admin' and authority = 'ROLE_ADMIN'
) limit 1;