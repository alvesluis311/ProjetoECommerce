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



insert into fabricante(nome)
values('Sony');

insert into fabricante(nome)
values('Microsoft');

insert into fabricante(nome)
values('Nintendo');




insert into plataforma(nome, descricao, anolancamento, id_fabricante) 
values('PlayStation 4', 'O PS4  é um console de jogos da Sony, que foi um grande sucesso e vendeu mais de 
115 milhões de unidades em todo o mundo. Apresentou um grande salto em relação ao PS3', 2013, 1); -- 1

insert into plataforma(nome, descricao, anolancamento, id_fabricante) 
values('PlayStation 5', 'O PS5 é a mais recente versão do console de jogos da Sony é o sucessor do PS4 e 
apresenta melhorias em relação ao seu antecessor.', 2020, 1); -- 2

insert into plataforma(nome, descricao, anolancamento, id_fabricante)
values('Xbox One', 'O Xbox One é um console de jogos da Microsoft, que competiu diretamente com o PS4 da Sony. 
Apresentou uma grande melhoria em relação ao Xbox 360,', 2013, 2); -- 3

insert into plataforma(nome, descricao, anolancamento, id_fabricante) 
values('Xbox Series S', 'O Xbox Series S é um console de jogos da Microsoft. É uma versão mais acessível do console
Xbox Serie X, projetado para jogadores que desejam jogar jogos de próxima geração sem gastar tanto dinheiro', 2020, 2); --4

insert into plataforma(nome, descricao, anolancamento, id_fabricante) 
values('Xbox Series X', 'O Xbox Series X é um console de jogos da Microsoft, juntamente com o Xbox Series S. É a versão mais
poderosa do console, projetada para jogadores que desejam desempenho máximo e gráficos de última geração.', 2020, 2); --5

insert into plataforma(nome, descricao, anolancamento, id_fabricante) 
values('Nintendo Switch', 'O Nintendo Switch é um console de jogos híbrido criado pela Nintendo. É um console versátil 
que pode ser jogado tanto no modo de TV quanto no modo portátil.', 2017, 3); -- 6

insert into plataforma(nome, descricao, id_fabricante)  
values('PC', 'O Windows é uma das plataformas de jogos mais populares no mundo dos games. Ele oferece uma grande 
variedade de jogos, incluindo jogos de diferentes gêneros.', 2); -- 7



insert into genero(nome)
values('Ação'); -- 1
insert into genero(nome)
values('Aventura'); -- 2
insert into genero(nome)
values('RPG'); -- 3
insert into genero(nome)
values('Estratégia'); -- 4
insert into genero(nome)
values('Simulação'); -- 5
insert into genero(nome)
values('Puzzle'); -- 6
insert into genero(nome)
values('Esportes'); -- 7
insert into genero(nome)
values('Corrida'); -- 8
insert into genero(nome)
values('Luta'); -- 9
insert into genero(nome)
values('Tiro'); -- 10
insert into genero(nome)
values('Plataforma'); -- 11
insert into genero(nome)
values('Sobrevivência'); -- 12
insert into genero(nome)
values('Horror'); -- 13
insert into genero(nome)
values('Mundo Aberto'); -- 14
insert into genero(nome)
values('Música'); -- 15
insert into genero(nome)
values('Souslike'); -- 16
insert into genero(nome)
values('Exploração'); -- 17
insert into genero(nome)
values('Mistério'); -- 18
insert into genero(nome)
values('Metroidvania'); -- 19
insert into genero(nome)
values('Roguelike'); -- 20



insert into produto(nome, descricao, preco, estoque)
values('Elden Ring', 'RPG de ação desenvolvido pela FromSoftware em parceria com George R.R. Martin.
 Exploração de um vasto mundo aberto com sistema de habilidades, combate intenso e chefes desafiadores.', 279.99, 30);

insert into produto(nome, descricao, preco, estoque)
values('God of War Ragnarok', 'Sequência do aclamado jogo de ação da Sony Santa Monica. Continuação da história
 de Kratos e Atreus em Midgard, com batalhas épicas, novo sistema de equipamentos e progressão de personagem.', 299.99, 30);

insert into produto(nome, descricao, preco, estoque)
values('GRIS', 'Jogo Indie de plataforma e quebra-cabeça com belos visuais e trilha sonora emocional. A jornada de uma jovem 
garota através de um mundo surreal e catártico em busca da superação de suas próprias dificuldades emocionais.', 84.50, 30);

insert into produto(nome, descricao, preco, estoque)
values('The Last of Us Part II', 'Continuação da premiada saga pós-apocalíptica da Naughty Dog. História emocionalmente 
intensa de Ellie e sua jornada em um mundo infectado por um fungo, com ênfase no combate furtivo e decisões morais difíceis
.', 199.99, 30);

insert into produto(nome, descricao, preco, estoque)
values('Outer Wilds', 'Jogo indie de exploração espacial com elementos de mistério e quebra-cabeça. Um universo vivo e interativo,
com uma mecânica de "loop temporal" que desvenda os segredos de um sistema solar em constante evolução.', 124.50, 30);

insert into produto(nome, descricao, preco, estoque)
values('Hollow Knight', 'Jogo indie de plataforma e ação com atmosfera sombria e desafiadora. Exploração de um vasto mundo
 interconectado, com combate preciso, chefes desafiadores e uma história rica em lore.', 62.50, 30);



insert into game(id, diretor, anolancamento, id_developer) 
values(1, 'Hidetaka Miyazaki', 2022, 1);

insert into generos_do_jogo(id_game, id_genero)
values(1, 1); -- Ação
insert into generos_do_jogo(id_game, id_genero)
values(1, 2); -- Aventura
insert into generos_do_jogo(id_game, id_genero)
values(1, 3); -- RPG
insert into generos_do_jogo(id_game, id_genero)
values(1, 14); -- Mundo Aberto
insert into generos_do_jogo(id_game, id_genero)
values(1, 16); -- Souslike
insert into generos_do_jogo(id_game, id_genero)
values(1, 17); -- Exploração

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



insert into game(id, diretor, anolancamento, id_developer) 
values(2, 'Eric Williams', 2022, 2);

insert into generos_do_jogo(id_game, id_genero)
values(2, 1); -- Ação
insert into generos_do_jogo(id_game, id_genero)
values(2, 2); -- Aventura
insert into generos_do_jogo(id_game, id_genero)
values(2, 3); -- RPG
insert into generos_do_jogo(id_game, id_genero)
values(2, 9); -- Luta
insert into generos_do_jogo(id_game, id_genero)
values(2, 14); -- Mundo Aberto

insert into plataformas_de_jogo(id_game, id_plataforma)
values(2, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(2, 2); -- PlayStation 5



insert into game(id, diretor, anolancamento, id_developer) 
values(3, 'Conrad Roset', 2018, 3);

insert into generos_do_jogo(id_game, id_genero)
values(3, 2); -- Aventura
insert into generos_do_jogo(id_game, id_genero)
values(3, 6); -- Puzzle
insert into generos_do_jogo(id_game, id_genero)
values(3, 11); -- Plataforma
insert into generos_do_jogo(id_game, id_genero)
values(3, 15); -- Música
insert into generos_do_jogo(id_game, id_genero)
values(3, 17); -- Exploração

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



insert into game(id, diretor, anolancamento, id_developer) 
values(4, 'Neil Druckmann', 2020, 4);

insert into generos_do_jogo(id_game, id_genero)
values(4, 1); --Ação
insert into generos_do_jogo(id_game, id_genero)
values(4, 2); --Aventura
insert into generos_do_jogo(id_game, id_genero)
values(4, 9); --Luta
insert into generos_do_jogo(id_game, id_genero)
values(4, 12); -- Sobrevivência
insert into generos_do_jogo(id_game, id_genero)
values(4, 17); -- Exploração

insert into plataformas_de_jogo(id_game, id_plataforma)
values(4, 1); -- PlayStation 4
insert into plataformas_de_jogo(id_game, id_plataforma)
values(4, 2); -- PlayStation 5
insert into plataformas_de_jogo(id_game, id_plataforma)
values(4, 7); -- PC



insert into game(id, diretor, anolancamento, id_developer) 
values(5, 'Alex Beachum', 2019, 5);

insert into generos_do_jogo(id_game, id_genero)
values(5, 2); --Aventura
insert into generos_do_jogo(id_game, id_genero)
values(5, 6); --Puzzle
insert into generos_do_jogo(id_game, id_genero)
values(5, 17); -- Exploração
insert into generos_do_jogo(id_game, id_genero)
values(5, 18); -- Mistério

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



insert into game(id, diretor, anolancamento, id_developer) 
values(6, 'Christopher Larkin', 2017, 6);

insert into generos_do_jogo(id_game, id_genero)
values(6, 1); --Ação
insert into generos_do_jogo(id_game, id_genero)
values(6, 2); --Aventura
insert into generos_do_jogo(id_game, id_genero)
values(6, 9); --Luta
insert into generos_do_jogo(id_game, id_genero)
values(6, 17); --Exploração
insert into generos_do_jogo(id_game, id_genero)
values(6, 19); --Metroidvania

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



insert into estado (nome, sigla) values ('Acre', 'AC');
insert into estado (nome, sigla) values ('Amazonas', 'AM');
insert into estado (nome, sigla) values ('Goiás', 'GO');
insert into estado (nome, sigla) values ('Pará', 'PA');
insert into estado (nome, sigla) values ('Tocantins', 'TO');



insert into municipio (nome, id_estado) values ('Manaus', 2);
insert into municipio (nome, id_estado) values ('Palmas', 5);
insert into municipio (nome, id_estado) values ('Guaraí', 5);
insert into municipio (nome, id_estado) values ('Belém', 4);
insert into municipio (nome, id_estado) values ('Goiânia', 3);
--
-- insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 12', 'Quadra 708 Sul', 'lote 10', '77082-012', 2);
-- insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('avenida Bernado Sayão', 'Setor Aeroporto', 'número 3564', '77700-001', 3);
-- insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('rua Piauí', 'Quadra 301 Norte', 'numero 102', '77030-030', 1);
-- insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 08', 'Quadra 1200 Sul', 'numero 092', '77092-839', 3);
-- insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 13', 'Setor Bueno', 'lote 18', '77903-029', 1);
-- insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 5', 'Quadra 201 Norte', 'numero 12', '77010-840', 2);
--
-- insert into usuario (login, senha, id_telefone_opcional)
-- values (1, 'JoaoA', 'ZXChMgzI4VI5Jx+KKCL0AnuRaug9sWorJdV7iCDgWIDNVms7vyhaZeXP+5x26q6uDWKJmyQySZzE8hvoncjgCA==', 1, 2, 1);
--
-- insert into usuario (id_pessoa_fisica, login, senha, id_endereco, id_telefone_principal)
-- values (2, 'mfernanda', 'x6JkviFo/CZc/dYoTsn+KjkyXu9rqbOwZ89vC1horO3B+ZT2N9nhquEvkFxm2WZahBpo5wgui91vSF00c1BYPA==', 3, 3);
--
-- insert into usuario (id_pessoa_fisica, login, senha, id_endereco, id_telefone_principal, id_telefone_opcional)
-- values (3, 'pgamer', 'EDCT26TOqyKJg1i5rpN/tOkmr8RSjKfPP1qdPhjlj+sA3Wd++oZFkG5YChaMMRndKipiyVxfL12CUYWybBk+aA==', 2, 4, 5);
--
-- insert into usuario (id_pessoa_fisica, login, senha, id_endereco, id_telefone_principal, id_telefone_opcional)
-- values (4, 'andregustavo', 'RfXkjGmkLte9wfbrO9237FdQiMKPjlKk3soCKh7zYvhiZToYC/424oO8HClVYVrEvIZTwd5OVZsIj4C+q9k21w==', 5, 6, 7);
--
-- insert into usuario (id_pessoa_fisica, login, senha, id_endereco, id_telefone_principal)
-- values (5, 'marcelinho', 'Wp7loHTF7tvklCuczvAnLXJGWSBVrOS2VnsB6ZJvl4ysfhoYiH+U4Dl/DT9w2XQCqLV+uoQ/R7myAiT4sIEjew==', 4, 8);
--
-- insert into usuario (id_pessoa_fisica, login, senha, id_endereco, id_telefone_principal)
-- values (6, 'mapereira', 'ccD5mRYxf9XgnDZUKjOq/Fx123hMiHQCX+nDp6j2iztugbW5q36cqqtxJy2dimLNpd6ZQHvDUKXQenN3Y5yiDQ==', 4, 9);
--
insert into usuario (login, senha, cpf, email, nome )
values ('JohnDev', '89ud9FUF967ZPp2GxHJ6ITVrXHnVfA0uf1AsYZ0V0SYuA0OCjSKXEgH72aTLeGBaQr3m7WuVsgWlx76WK/gWuA==', '05714832167', 'email@gmail.com', 'nome');
-- -- joao1234
-- -- senha1234
-- -- pa1000ulo
-- -- andrezinho123
-- -- password
-- -- mrnprr45
-- -- JohnDev
--
insert into perfis (usuario_id, perfil)
values (1, 'Admin');
insert into perfis (usuario_id, perfil)
values (1, 'User');
-- insert into perfis (id_usuario, perfil) values (1, 'User');
-- insert into perfis (id_usuario, perfil) values (2, 'User');
-- insert into perfis (id_usuario, perfil) values (3, 'User');
-- insert into perfis (id_usuario, perfil) values (4, 'User');
-- insert into perfis (id_usuario, perfil) values (5, 'User');
-- insert into perfis (id_usuario, perfil) values (6, 'User');
-- insert into perfis (id_usuario, perfil) values (7, 'User_Basic');


-- insert into lista_desejo (id_usuario, id_produto) values (6, 4);
-- insert into lista_desejo (id_usuario, id_produto) values (2, 3);
-- insert into lista_desejo (id_usuario, id_produto) values (3, 5);
-- insert into lista_desejo (id_usuario, id_produto) values (2, 2);
-- insert into lista_desejo (id_usuario, id_produto) values (4, 1);

insert into endereco(principal, id_municipio, usuario_id, bairro, cep, complemento, logradouro, numero)
values (true, 1, 1, 'Quadra 708 Sul', 'lote 10', '77082-012', 'alameda 12', '1');

insert into telefone (codigoarea, numero, principal, usuario_id)
values (63, '985156666', true, 1),(63,'3366-1766', false, 1);

-- insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
-- values ('Melhor jogo que já vi da FromSoftware. Basicamente Dark Souls 4', '2023-01-22', 5, 1, 1);
--
-- insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
-- values ('Gostei da gameplay, mas a história acaba destoando dos games clássicos', '2022-11-09', 3, 2, 2);
--
-- insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
-- values ('Esse é o jogo mais lindo que já vi na minha vida', '2023-02-08', 5, 3, 3);
--
-- insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
-- values ('Que jogo bom, simplesmente The Last of Us. só não gostei da parte do taco de golfe', '2023-03-08', 4, 4, 4);
--
-- insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
-- values ('muito bom, quero comprar a DLC agora', '2023-01-18', 4, 5, 5);
--
-- insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
-- values ('Uma das melhores experiências que ja vivi na minha vida foi jogando esse jogo', '2023-02-25', 5, 6, 6);
--
-- insert into avaliacao (data, estrela, id_produto, id_usuario)
-- values ('2022-10-28', 5, 1, 3);
--
-- insert into pagamento (valor, confirmacaoPagamento, dataConfirmacaoPagamento) values (511, true, '2023-06-10');
-- insert into pagamento (valor, confirmacaoPagamento, dataConfirmacaoPagamento) values (1228.25, true, '2023-06-15');
--
-- insert into pix (nome, cpf, dataExpiracaoTokenPix, id) values ('Maria Fernanda', '89114182345', '2023-06-11', 1);
-- insert into boletoBancario (id, nome, cpf, dataGeracaoBoleto, dataVencimento)
--             values (2, 'Maria Fernanda', '89114182345', '2023-06-15', '2023-06-25');
--
-- insert into compra (dataCompra, totalCompra, ifConcluida, id_endereco, id_pagamento, id_usuario)
--             values ('2023-06-10', 511, true, 3, 1, 2);
--
-- insert into compra (dataCompra, totalCompra, ifConcluida, id_endereco, id_pagamento, id_usuario)
--             values ('2023-06-15', 1228.25, true, 3, 2, 2);
--
-- insert into itemCompra (id_compra, quantidade, precoUnitario, id_produto) values (1, 10, 34.95, 1);
-- insert into itemCompra (id_compra, quantidade, precoUnitario, id_produto) values (1, 5, 32.30, 2);
-- insert into itemCompra (id_compra, quantidade, precoUnitario, id_produto) values (2, 15, 34.95, 1);
-- insert into itemCompra (id_compra, quantidade, precoUnitario, id_produto) values (2, 2, 29.00, 3);
-- insert into itemCompra (id_compra, quantidade, precoUnitario, id_produto) values (2, 20, 32.30, 2);

