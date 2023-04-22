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
values('PlayStation 4', 'O PS4  é um console de jogos da Sony, que foi um grande sucesso e vendeu mais de 
115 milhões de unidades em todo o mundo. Apresentou um grande salto em relação ao PS3', 2013, 1); -- 1

insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('PlayStation 5', 'O PS5 é a mais recente versão do console de jogos da Sony é o sucessor do PS4 e 
apresenta melhorias em relação ao seu antecessor.', 2020, 1); -- 2

insert into plataforma(nome, descricao, anolancamento, fabricante)
values('Xbox One', 'O Xbox One é um console de jogos da Microsoft, que competiu diretamente com o PS4 da Sony. 
Apresentou uma grande melhoria em relação ao Xbox 360,', 2013, 2); -- 3

insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('Xbox Series S', 'O Xbox Series S é um console de jogos da Microsoft. É uma versão mais acessível do console
Xbox Serie X, projetado para jogadores que desejam jogar jogos de próxima geração sem gastar tanto dinheiro', 2020, 2); --4

insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('Xbox Series X', 'O Xbox Series X é um console de jogos da Microsoft, juntamente com o Xbox Series S. É a versão mais
poderosa do console, projetada para jogadores que desejam desempenho máximo e gráficos de última geração.', 2020, 2); --5

insert into plataforma(nome, descricao, anolancamento, fabricante) 
values('Nintendo Switch', 'O Nintendo Switch é um console de jogos híbrido criado pela Nintendo. É um console versátil 
que pode ser jogado tanto no modo de TV quanto no modo portátil.', 2017, 3); -- 6

insert into plataforma(nome, descricao, fabricante)  
values('PC', 'O Windows é uma das plataformas de jogos mais populares no mundo dos games. Ele oferece uma grande 
variedade de jogos, incluindo jogos de diferentes gêneros.', 2); -- 7



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



insert into estado (nome, sigla) values ('Acre', 'AC');
insert into estado (nome, sigla) values ('Amazonas', 'AM');
insert into estado (nome, sigla) values ('Pará', 'PA');
insert into estado (nome, sigla) values ('Tocantins', 'TO');



insert into municipio (nome, id_estado) values ('Manaus', 2);
insert into municipio (nome, id_estado) values ('Palmas', 4);
insert into municipio (nome, id_estado) values ('Guaraí', 4);
insert into municipio (nome, id_estado) values ('Belém', 3);



insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 12', 'Quadra 708 Sul', 'lote 10', '77082-012', 2);
insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('avenida Bernado Sayão', 'Setor Aeroporto', 'número 3564', '77700-001', 3);
insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('rua Piauí', 'Quadra 301 Norte', 'numero 102', '77030-030', 1);
insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 13', 'Quadra 605 Sul', 'lote 1', '77042-002', 2);
insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 13', 'Quadra 408 norte', 'número 34', '77060-001', 2);
insert into endereco (logradouro, bairro, numero, cep, id_municipio) values ('alameda 5', 'Quadra 201 Norte', 'numero 12', '77010-840', 2);



insert into telefone (codigoarea, numero) values ('011', '98456-7812');
insert into telefone (codigoarea, numero) values ('061', '99901-5842');
insert into telefone (codigoarea, numero) values ('061', '99933-0572');
insert into telefone (codigoarea, numero) values ('063', '99933-0572');
insert into telefone (codigoarea, numero) values ('078', '98203-3301');
insert into telefone (codigoarea, numero) values ('063', '99602-9825');
insert into telefone (codigoarea, numero) values ('011', '99378-6412');
insert into telefone (codigoarea, numero) values ('011', '99749-1046');
insert into telefone (codigoarea, numero) values ('063', '99317-7436');
insert into telefone (codigoarea, numero) values ('063', '98832-1250');



insert into usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal, id_telefone_opcional)
values ('João Aguiar', 'joao_aguiar@gmail.com', 'joao1234', '24568941318', 1, 2, 1);

insert into usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal)
values ('Maria Fernanda', 'mariaF@gmail.com', 'senha1234', '08723462371', 3, 3);

insert into usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal, id_telefone_opcional)
values ('Paulo Vitor', 'paulo_gamer@gmail.com', 'pa1000ulo', '56712389045', 2, 4, 5);

insert into usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal, id_telefone_opcional)
values ('André Gustavo', 'andreg@gmail.com', 'andrezinho123', '01234567890', 5, 6, 7);

insert into usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal, id_telefone_opcional)
values ('Marcelo da Silva', 'marcilva@gmail.com', 'password', '44455566699', 6, 8, 9);

insert into usuario (nome, email, senha, cpf, id_endereco, id_telefone_principal) 
values ('Marina Pereira', 'mpereira@gmail.com', 'mrnprr45', '98765432102', 4, 10);


insert into avaliacao (comentario, data, estrela, id_produto, id_usuario) 
values ('Melhor jogo que já vi da FromSoftware. Basicamente Dark Souls 4', '2023-01-22', 5, 1, 1);

insert into avaliacao (comentario, data, estrela, id_produto, id_usuario)
values ('Gostei da gameplay, mas a história acaba destoando dos games clássicos', '2022-11-09', 3, 2, 2);

insert into avaliacao (comentario, data, estrela, id_produto, id_usuario) 
values ('Esse é o jogo mais lindo que já vi na minha vida', '2023-02-08', 5, 3, 3);

insert into avaliacao (comentario, data, estrela, id_produto, id_usuario) 
values ('Que jogo bom, simplesmente The Last of Us. só não gostei da parte do taco de golfe', '2023-03-08', 4, 4, 4);

insert into avaliacao (comentario, data, estrela, id_produto, id_usuario) 
values ('muito bom, quero comprar a DLC agora', '2023-01-18', 4, 5, 5);

insert into avaliacao (comentario, data, estrela, id_produto, id_usuario) 
values ('Uma das melhores experiências que ja vivi na minha vida foi jogando esse jogo', '2023-02-25', 5, 6, 6);