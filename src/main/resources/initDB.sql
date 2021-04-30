drop table if exists users cascade;
create table users(
                      id serial primary key,
                      name varchar(40),
                      email varchar(240),
                      phone_number varchar(13),
                      password varchar(40)
);

insert into users(name, email, phone_number, password) values ('Test1', 'test1@gmail.com', '+380973643131', 'Test1Test');
insert into users(name, email, phone_number, password) values ('Test2', 'test2@gmail.com', '+380973643132', 'Test2Test');
insert into users(name, email, phone_number, password) values ('Test3', 'test3@gmail.com', '+380973643133', 'Test3Test');
insert into users(name, email, phone_number, password) values ('Test4', 'test4@gmail.com', '+380973643134', 'Test4Test');
insert into users(name, email, phone_number, password) values ('Test5', 'test5@gmail.com', '+380973643135', 'Test5Test');
insert into users(name, email, phone_number, password) values ('Test6', 'test6@gmail.com', '+380973643136', 'Test6Test');
insert into users(name, email, phone_number, password) values ('Test7', 'test7@gmail.com', '+380973643137', 'Test7Test');
insert into users(name, email, phone_number, password) values ('Test8', 'test8@gmail.com', '+380973643138', 'Test8Test');

drop table if exists orders cascade;
create table orders(
                       id serial primary key,
                       name varchar(40),
                       price int
);

insert into orders(name, price) values ('Test Order 1', 111);
insert into orders(name, price) values ('Test Order 2', 222);
insert into orders(name, price) values ('Test Order 3', 333);
insert into orders(name, price) values ('Test Order 4', 444);
insert into orders(name, price) values ('Test Order 5', 555);
insert into orders(name, price) values ('Test Order 6', 666);
insert into orders(name, price) values ('Test Order 7', 777);
insert into orders(name, price) values ('Test Order 8', 888);
insert into orders(name, price) values ('Test Order 9', 999);