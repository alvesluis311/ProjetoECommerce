-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');


insert into console(nome, descricao, anolancamento) 
values('PlayStation 4', 'Console da Sony', 2013);
insert into console(nome, descricao, anolancamento) 
values('PlayStation 5', 'Console da Sony', 2020);
insert into console(nome, descricao, anolancamento)
values('Xbox One', 'Console da Microsoft', 2013);
insert into console(nome, descricao, anolancamento) 
values('Xbox Series S', 'Console da Microsoft', 2020);
insert into console(nome, descricao, anolancamento) 
values('Xbox Series X', 'Console da Microsoft', 2020);
insert into console(nome, descricao, anolancamento) 
values('Nintendo Switch', 'Console da Nintendo', 2017);


insert into produto(nome, descricao, preco, estoque)
values('Elden Ring', 'RPG de Ação', 299.99, 30);
insert into produto(nome, descricao, preco, estoque)
values('God of War Ragnarok', 'RPG de Ação', 299.99, 30);
insert into produto(nome, descricao, preco, estoque)
values('FIFA 23', 'Esportes', 399.99, 30);


insert into game(id, diretor, anolancamento, developer) 
values(1, 'Hidetaka Miyazaki', 2022, 1);
insert into game(id, diretor, anolancamento, developer) 
values(2, 'Eric Williams', 2022, 8);
insert into game(id, diretor, anolancamento, developer) 
values(3, '???', 2022, 2);
