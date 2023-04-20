-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');


insert into developer(nome, fundacao) 
values('From Software', 1986); --1
insert into developer(nome, fundacao) 
values('Santa Monica Studio', 1999); --2
insert into developer(nome, fundacao) 
values('Nomada Studio', 2011); --3
insert into developer(nome, fundacao) 
values('Naughty Dog', 1984); --4
insert into developer(nome, fundacao)
values('Annapurna Interactive', 2016); --5
insert into developer(nome, fundacao) 
values('Team Cherry', 2014); --6



insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('PlayStation 4', 'Console da Sony', 2013, 1); -- 1
insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('PlayStation 5', 'Console da Sony', 2020, 1); -- 2
insert into plataforma(nome, descricao, anolancamento, fabricante)
values('Xbox One', 'Console da Microsoft', 2013, 2); -- 3
insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('Xbox Series S', 'Console da Microsoft', 2020, 2); --4
insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('Xbox Series X', 'Console da Microsoft', 2020, 2); --5
insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('Nintendo Switch', 'Console da Nintendo', 2017, 3); -- 6
insert into plataforma(nome, descricao, fabricante)  
values('PC', 'Windows', 2); -- 7


insert into produto(nome, descricao, preco, estoque)
values('Elden Ring', 'RPG de Ação', 279.99, 30);
insert into produto(nome, descricao, preco, estoque)
values('God of War Ragnarok', 'RPG de Ação', 299.99, 30);
insert into produto(nome, descricao, preco, estoque)
values('GRIS', 'Indie/Plataforma', 84.50, 30);
insert into produto(nome, descricao, preco, estoque)
values('The Last of Us Part II', 'Ação-aventura, Single Player', 199.99, 30);
insert into produto(nome, descricao, preco, estoque)
values('Outer Wilds', 'Indie/Exploração', 124.50, 30);
insert into produto(nome, descricao, preco, estoque)
values('Hollow Knight', 'Indie/Metroidvania', 62.50, 30);


insert into game(id, diretor, anolancamento, id_developer, tipogame) 
values(1, 'Hidetaka Miyazaki', 2022, 1, 1);
insert into plataformas_de_jogo(id_game, id_plataforma)
values(1, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(1, 2); -- PlayStation 5
insert into plataformas_de_jogo(id_game, id_plataforma)
values(1, 3); -- Xbox One
insert into plataformas_de_jogo(id_game, id_plataforma)
values(1, 4); -- Xbox Series S
insert into plataformas_de_jogo(id_game, id_plataforma)
values(1, 5); -- Xbox Series X
insert into plataformas_de_jogo(id_game, id_plataforma)
values(1, 7); -- PC


insert into game(id, diretor, anolancamento, id_developer, tipogame) 
values(2, 'Eric Williams', 2022, 2, 1);
insert into plataformas_de_jogo(id_game, id_plataforma)
values(2, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(2, 2); -- PlayStation 5


insert into game(id, diretor, anolancamento, id_developer, tipogame) 
values(3, 'Conrad Roset', 2018, 3, 2);
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 2); -- PlayStation 5
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 3); -- Xbox One
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 4); -- Xbox Series S
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 5); -- Xbox Series X
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 6); -- Nintendo Switch
insert into plataformas_de_jogo(id_game, id_plataforma)
values(3, 7); -- PC


insert into game(id, diretor, anolancamento, id_developer, tipogame) 
values(4, 'Neil Druckmann', 2020, 4, 1);
insert into plataformas_de_jogo(id_game, id_plataforma)
values(4, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(4, 2); -- PlayStation 5
insert into plataformas_de_jogo(id_game, id_plataforma)
values(4, 7); -- PC


insert into game(id, diretor, anolancamento, id_developer, tipogame) 
values(5, 'Alex Beachum', 2019, 5, 2);
insert into plataformas_de_jogo(id_game, id_plataforma)
values(5, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(5, 2); -- PlayStation 5
insert into plataformas_de_jogo(id_game, id_plataforma)
values(5, 3); -- Xbox One
insert into plataformas_de_jogo(id_game, id_plataforma)
values(5, 4); -- Xbox Series S
insert into plataformas_de_jogo(id_game, id_plataforma)
values(5, 5); -- Xbox Series X
insert into plataformas_de_jogo(id_game, id_plataforma)
values(5, 7); -- PC


insert into game(id, diretor, anolancamento, id_developer, tipogame) 
values(6, 'Christopher Larkin', 2017, 6, 2);
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 2); -- PlayStation 5
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 3); -- Xbox One
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 4); -- Xbox Series S
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 5); -- Xbox Series X
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 6); -- Nintendo Switch
insert into plataformas_de_jogo(id_game, id_plataforma)
values(6, 7); -- PC