drop table dishIngr;
drop table ordDish;
drop table usersOrders;
drop table dish;
drop table ingredient;
drop table orders;
drop table users;
drop table dishesToBeMade;

create table dish(
dishname varchar(255),
price int not null, 
constraint pk1 primary key(dishname)); 

create table dishIngr(
dishname varchar(255),
ingredientname varchar(255),
constraint dishIngr_PK primary key(dishname, ingredientname));

create table ingredient(
ingredientname varchar(255),
description varchar(255),
constraint pk2 primary key(ingredientname)
);

create table users(
usernr integer primary key generated always as identity,
email varchar(255) not null,
firstName varchar(255),
surName varchar(255),
username varchar(255) unique not null,
password varchar(255) not null,
isEmployee smallInt);

create table orders(
ordernr integer primary key generated always as identity,
adress varchar(255) not null,
postnr int not null);

create table usersOrders(
ordernr integer,
usernr integer, 
constraint userOrders_PK primary key(ordernr, usernr));

create table ordDish(
ordernr integer,
dishname varchar(255),
dishesordered integer not null,
constraint ordDish_PK primary key(ordernr, dishname)); 

create table dishesToBeMade(
ordernr integer,
dishname varchar(255),
dishesordered integer not null,
constraint dishesMade primary key(ordernr, dishname));

alter table dishIngr add constraint 
fk1 foreign key (ingredientname)
references ingredient(ingredientname);

alter table dishIngr add constraint 
fk2 foreign key (dishname)
references dish(dishname);

alter table ordDish add constraint fk3
foreign key (dishname)
references dish(dishname);

alter table ordDish add constraint fk4
foreign key (ordernr)
references orders(ordernr);

alter table usersOrders add constraint fk5
foreign key (usernr)
references users(usernr);

alter table usersOrders add constraint fk6
foreign key (ordernr)
references orders(ordernr);

insert into ingredient values('Potatoes','These are self-explanatory');
insert into ingredient values('Carrots','These are self-explanatory');
insert into ingredient values('Tomatoes','These are self-explanatory');
insert into ingredient values('Beans','These are self-explanatory');

insert into dish values('Potato Soup', 100);
insert into dish values('Carrot Soup', 100);
insert into dish values('Bean Soup', 100);

insert into dishIngr values('Potato Soup', 'Potatoes');
insert into dishIngr values('Potato Soup', 'Beans');

insert into dishIngr values('Carrot Soup', 'Carrots');
insert into dishIngr values('Carrot Soup', 'Tomatoes');
-- select * from dish;
insert into dishIngr values('Bean Soup', 'Beans');
insert into dishIngr values('Bean Soup', 'Tomatoes');

insert into users values(default, 'glenn.skar@haugnett.no', 'Glenn', 'Skår', 'glennsk', 'passord', 1);
insert into users values(default, 'bacon@spam.com','Bacon','Spam', 'OMNOMNOM','passord',0);

select * from ordDish;
select count(*)"count" from orders;

--select * from ordDish where orders.ORDERNR = ordDish.ORDERNR;
select * from ingredient;
select * from dish;
select * from dishIngr;
select * from ordDish;
select * from users;
select dish.dishname, price from ordDish, dish where dish.DISHNAME = ordDish.DISHNAME;
select orddish.ORDERNR, orddish.DISHNAME, orddish.DISHESORDERED from ordDish, orders, usersorders where orddish.ORDERNR = orders.ORDERNR and usersorders.ORDERNR
= orders.ORDERNR and usersorders.USERNR = 1;

select dishesToBeMade.* from dishesToBeMade;
--delete from users where username = 'glennsk';
select * from orders;