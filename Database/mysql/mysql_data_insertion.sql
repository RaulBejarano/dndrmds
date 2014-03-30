-- Script to add data to database.
SET FOREIGN_KEY_CHECKS=0;

-- Elements (NONE, DROP, FLAME, THUNDER, NORMAL, LEAF, RARE)
DELETE FROM Element;

INSERT INTO Element (name) VALUES ('NONE');
INSERT INTO Element (name) VALUES ('RARE');
INSERT INTO Element (name) VALUES ('NORMAL');
INSERT INTO Element (name) VALUES ('FLAME');
INSERT INTO Element (name) VALUES ('DROP');
INSERT INTO Element (name) VALUES ('THUNDER');
INSERT INTO Element (name) VALUES ('LEAF');
INSERT INTO Element (name) VALUES ('COLD');
INSERT INTO Element (name) VALUES ('FIST');
INSERT INTO Element (name) VALUES ('VENOM');
INSERT INTO Element (name) VALUES ('WING');
INSERT INTO Element (name) VALUES ('MENTAL');
INSERT INTO Element (name) VALUES ('INSECT');
INSERT INTO Element (name) VALUES ('STONE');
INSERT INTO Element (name) VALUES ('IRON');

-- Element_Element
DELETE FROM Element_Element;

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'INSECT'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'IRON'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'FLAME'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'DROP'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'THUNDER'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'WING'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'IRON'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'DROP'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'WING'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'INSECT'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'COLD'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'WING'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'NORMAL'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'FIST'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'WING'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'MENTAL'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'INSECT'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'IRON'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'THUNDER'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'FIST'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'INSECT'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'FIST'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'VENOM'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'MENTAL'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'FIST'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'WING'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'MENTAL'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'FLAME'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'THUNDER'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'FIST'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'VENOM'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'THUNDER'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'COLD'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);


-- States (Note: add at least 3 more)
DELETE FROM State;

INSERT INTO State (name, abreviation) VALUES ('Normal', 'NOR');
INSERT INTO State (name, abreviation) VALUES ('Burn', 'BUR');
INSERT INTO State (name, abreviation) VALUES ('Confused', 'CON');
INSERT INTO State (name, abreviation) VALUES ('Electrized', 'ELE');
INSERT INTO State (name, abreviation) VALUES ('Poisoned','POI');


-- Dandremid Base. These Dandremid_Bases are all the Dandremid types in the game
DELETE FROM Dandremid_Base;

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Adreto', 	(SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'NONE'), 	6, 4, 5, 10, 36,'This is Adreto\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Lasentu', (SELECT id FROM Element WHERE name = 'FIST'), 	(SELECT id FROM Element WHERE name = 'NONE'), 	4, 6, 5, 10, 36,'This is Lasentu\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Norti', 	(SELECT id FROM Element WHERE name = 'WING'), 	(SELECT id FROM Element WHERE name = 'NONE'), 	5, 4, 6, 10, 36,'This is Norti\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Tenko', 	(SELECT id FROM Element WHERE name = 'LEAF'), 	(SELECT id FROM Element WHERE name = 'NONE'), 	4, 6, 5, 10, 36,'This is Tenko\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Topa', 	(SELECT id FROM Element WHERE name = 'FLAME'), 	(SELECT id FROM Element WHERE name = 'NONE'), 	6, 4, 5, 10, 36,'This is Topa\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Trippo', 	(SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'NONE'), 	7, 9, 7, 10, 36,'This is Trippo\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Hakun', 	(SELECT id FROM Element WHERE name = 'DROP'), 	(SELECT id FROM Element WHERE name = 'VENOM'), 	8, 8, 7, 10, 36,'This is Hakun\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Icarot', 	(SELECT id FROM Element WHERE name = 'THUNDER'),(SELECT id FROM Element WHERE name = 'VENOM'), 	6, 4, 5, 10, 36,'This is Icarot\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Sasan', 	(SELECT id FROM Element WHERE name = 'LEAF'), 	(SELECT id FROM Element WHERE name = 'NONE'), 	6, 4, 5, 10, 36,'This is Sasan\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Wannir', 	(SELECT id FROM Element WHERE name = 'FLAME'), 	(SELECT id FROM Element WHERE name = 'FIST'), 	6, 4, 5, 10, 36,'This is Wannir\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Argontos',(SELECT id FROM Element WHERE name = 'LEAF'), 	(SELECT id FROM Element WHERE name = 'INSECT'), 9, 10, 10, 10, 36,'This is Argontos\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Toru', 	(SELECT id FROM Element WHERE name = 'VENOM'), 	(SELECT id FROM Element WHERE name = 'INSECT'), 10, 9, 9, 10, 36,'This is Toru\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Monkako', (SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'NONE'), 	11, 11, 10, 10, 36,'This is Monkako\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Drotem', 	(SELECT id FROM Element WHERE name = 'DROP'), 	(SELECT id FROM Element WHERE name = 'RARE'), 	8, 9, 9, 10, 36,'This is Drotem\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Firtem', 	(SELECT id FROM Element WHERE name = 'FLAME'), 	(SELECT id FROM Element WHERE name = 'RARE'), 	8, 9, 9, 10, 36,'This is Firtem\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Letem', 	(SELECT id FROM Element WHERE name = 'LEAF'), 	(SELECT id FROM Element WHERE name = 'RARE'), 	8, 9, 9, 10, 36,'This is Letem\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Nurtem', 	(SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'RARE'), 	8, 9, 9, 10, 36,'This is Nurtem\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Protem', 	(SELECT id FROM Element WHERE name = 'THUNDER'),(SELECT id FROM Element WHERE name = 'RARE'), 	8, 9, 9, 10, 36,'This is Protem\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Sangel', 	(SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'RARE'), 	10, 11, 10, 10, 36,'This is Sangel\'s description');
INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) VALUES ('Semon', 	(SELECT id FROM Element WHERE name = 'FLAME'), 	(SELECT id FROM Element WHERE name = 'RARE'), 	10, 11, 10, 10, 36,'This is Semon\'s description');



-- Attack
DELETE FROM Attack;

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Bolazo de nieve',(SELECT id FROM Element WHERE name = 'COLD'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Bolazo de hielo',(SELECT id FROM Element WHERE name = 'COLD'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Chorro',(SELECT id FROM Element WHERE name = 'DROP'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Oleaje',(SELECT id FROM Element WHERE name = 'DROP'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Manotazo',(SELECT id FROM Element WHERE name = 'FIST'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Puño Duro',(SELECT id FROM Element WHERE name = 'FIST'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Brasas',(SELECT id FROM Element WHERE name = 'FLAME'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Llamarada',(SELECT id FROM Element WHERE name = 'FLAME'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Picadura',(SELECT id FROM Element WHERE name = 'INSECT'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Superpicadura',(SELECT id FROM Element WHERE name = 'INSECT'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Amartillar',(SELECT id FROM Element WHERE name = 'IRON'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Golpe acero',(SELECT id FROM Element WHERE name = 'IRON'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Golpe rama',(SELECT id FROM Element WHERE name = 'LEAF'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Hoja dura',(SELECT id FROM Element WHERE name = 'LEAF'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Tensión',(SELECT id FROM Element WHERE name = 'MENTAL'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Psicosis',(SELECT id FROM Element WHERE name = 'MENTAL'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Placaje',(SELECT id FROM Element WHERE name = 'NONE'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Embestir',(SELECT id FROM Element WHERE name = 'NONE'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Pisotón',(SELECT id FROM Element WHERE name = 'NORMAL'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Estampida',(SELECT id FROM Element WHERE name = 'NORMAL'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Mov. Especial 1',(SELECT id FROM Element WHERE name = 'RARE'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Mov. Especial 2',(SELECT id FROM Element WHERE name = 'RARE'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Golpe roca',(SELECT id FROM Element WHERE name = 'STONE'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Avalancha',(SELECT id FROM Element WHERE name = 'STONE'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Calambrazo',(SELECT id FROM Element WHERE name = 'THUNDER'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Rayo',(SELECT id FROM Element WHERE name = 'THUNDER'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Esporas',(SELECT id FROM Element WHERE name = 'VENOM'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Intoxicar',(SELECT id FROM Element WHERE name = 'VENOM'), 8, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Picotazo',(SELECT id FROM Element WHERE name = 'WING'), 5, 0, 0);
INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) VALUES ('Ataque ala',(SELECT id FROM Element WHERE name = 'WING'), 8, 0, 0);


-- Object
DELETE FROM Object;

INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Dandresello',		1,0,0,0,0,0,1,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Super Dandresello',	2,0,0,0,0,0,1,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Ultra Dandresello',	3,0,0,0,0,0,1,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Mega Dandresello',	4,0,0,0,0,0,1,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Dandresello supremo',10,0,0,0,0,0,1,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Fuertetrón',		1,0,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Super Fuertetrón',	2,0,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Ultra Fuertetrón',	3,0,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Mega Fuertetrón',	4,0,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Defensitrón',		0,1,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Super Defensitrón',	0,2,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Ultra Defensitrón',	0,3,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Mega Defensitrón',	0,4,0,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Velocitrón',		0,0,1,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Super Velocitrón',	0,0,2,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Ultra Velocitrón',	0,0,3,0,0,0,0,'COMBAT',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Mega Velocitrón',	0,0,4,0,0,0,0,'COMBAT',0);

INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Poción',			0,0,0,25,0,0,0,'COMBAT-CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Super Poción',		0,0,0,50,0,0,0,'COMBAT-CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Ultra Poción',		0,0,0,100,0,0,0,'COMBAT-CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Mega Poción',		0,0,0,200,0,0,0,'COMBAT-CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Cura Máxima',		0,0,0,1000000,0,0,0,'COMBAT-CARE',0);

INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Manzana',		0,0,0,0,10,0,0,'CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Galleta',		0,0,0,0,20,0,0,'CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Shushi',		0,0,0,0,30,0,0,'CARE',0);
INSERT INTO Object (name,strength,defense,speed,life,feed,happiness,trap,type,price) VALUES ('Pastel',		0,0,0,0,40,0,0,'CARE',0);


-- The part downhere must be deleted

-- Users
DELETE FROM User;

INSERT INTO User (playerName, password, email, name, surname, birth, gender, level, exp, expNextLevel,gold) 
VALUES ('a', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'parry907@gmail.com', 'Raúl', 'Bejarano Parrilla', '17/04/1990', 'Male', 2, 10, 30,412);

--  Objects
DELETE FROM User_Object;

INSERT INTO User_Object (Object_id, User_id, quantity) VALUES ((SELECT id FROM Object WHERE name = "Dandresello"),(SELECT id FROM User WHERE playerName = "a"),3);
INSERT INTO User_Object (Object_id, User_id, quantity) VALUES ((SELECT id FROM Object WHERE name = "Poción"),(SELECT id FROM User WHERE playerName = "a"),3);
INSERT INTO User_Object (Object_id, User_id, quantity) VALUES ((SELECT id FROM Object WHERE name = "Cura Máxima"),(SELECT id FROM User WHERE playerName = "a"),3);
INSERT INTO User_Object (Object_id, User_id, quantity) VALUES ((SELECT id FROM Object WHERE name = "Super Defensitrón"),(SELECT id FROM User WHERE playerName = "a"),3);

-- Dandremid
DELETE FROM Dandremid;

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Lasentu', 5, 0, 100, -1, 14, 16, 15, 300, 300, 234, 234, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Lasentu'));

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Tenko', 1, 0, 100, -1, 6, 4, 5, 30, 30, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Tenko'));

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Topa', 1, 0, 100, -1, 6, 4, 5, 30, 30, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Topa'));



-- Dandremid_Attack
DELETE FROM Dandremid_Attack;

INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Attack WHERE name = 'Puño Duro'), (SELECT id FROM Dandremid WHERE name = 'Lasentu'), 1, 0, 10);

INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Attack WHERE name = 'Hoja Dura'), (SELECT id FROM Dandremid WHERE name = 'Tenko'), 1, 0, 10);

INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Attack WHERE name = 'Llamarada'), (SELECT id FROM Dandremid WHERE name = 'Topa'), 1, 0, 10);



-- Delete till here

SET FOREIGN_KEY_CHECKS=1;
