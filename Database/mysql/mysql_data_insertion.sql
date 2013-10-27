-- Script to add data to database.
SET FOREIGN_KEY_CHECKS=0;

-- Elements (NONE, DROP, FLAME, THUNDER, NORMAL, LEAF, RARE)
DELETE FROM Element;

INSERT INTO Element (name) VALUES ('NONE');
INSERT INTO Element (name) VALUES ('DROP');
INSERT INTO Element (name) VALUES ('FLAME');
INSERT INTO Element (name) VALUES ('THUNDER');
INSERT INTO Element (name) VALUES ('NORMAL');
INSERT INTO Element (name) VALUES ('LEAF');
INSERT INTO Element (name) VALUES ('RARE');

-- States (Note: add at least 3 more)
DELETE FROM State;

INSERT INTO State (name, abreviation) VALUES ('Normal', 'NOR');
INSERT INTO State (name, abreviation) VALUES ('Burn', 'BUR');
INSERT INTO State (name, abreviation) VALUES ('Confused', 'CON');
INSERT INTO State (name, abreviation) VALUES ('Electrized', 'ELE');


-- Creature Base. These Creature_Bases are all the creature types in the game
DELETE FROM Creature_Base;

INSERT INTO Creature_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, starveSpeed, maxLife) 
VALUES ('Antock', (SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'NONE'), 1, 1, 1, 100, 10, 100);

INSERT INTO Creature_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, starveSpeed, maxLife) 
VALUES ('Glont', (SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'RARE'), 1, 1, 1, 100, 10, 100);

INSERT INTO Creature_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, starveSpeed, maxLife) 
VALUES ('Mursh', (SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'NONE'), 1, 1, 1, 100, 10, 100);

INSERT INTO Creature_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, starveSpeed, maxLife) 
VALUES ('Tatoon', (SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'RARE'), 1, 1, 1, 100, 10, 100);

INSERT INTO Creature_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, starveSpeed, maxLife) 
VALUES ('Lonta', (SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'NONE'), 1, 1, 1, 100, 10, 100);


-- Attack
DELETE FROM Atack;

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 1', (SELECT id FROM Element WHERE name = 'DROP'), 10, 0, 1);

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 2', (SELECT id FROM Element WHERE name = 'DROP'), 9, 0, 2);

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 3', (SELECT id FROM Element WHERE name = 'DROP'), 8, 1, 3);

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 4', (SELECT id FROM Element WHERE name = 'DROP'), 7, 0, 4);

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 5', (SELECT id FROM Element WHERE name = 'FLAME'), 6, 4, 1);

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 6', (SELECT id FROM Element WHERE name = 'THUNDER'), 5, 0, 1);

INSERT INTO Atack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 7', (SELECT id FROM Element WHERE name = 'LEAF'), 4, 4, 1);




-- The part downhere must be deleted

-- Users
DELETE FROM User;

INSERT INTO User (playerName, password, email, name, surname, birth, gender, level, exp, expNextLevel) 
VALUES ('Agarosh', 'bejarano', 'parry907@gmail.com', 'Raúl', 'Bejarano Parrilla', '17/04/1990', 'Male', 2, 10, 30);


-- Creature
DELETE FROM Creature;

INSERT INTO Creature (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, life, maxLife, happiness, User_id, Creature_Base_id) 
VALUES ('Antock1', 1, 0, 100, FALSE, 1, 1, 1, 200, 200, 10, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'Agarosh'), (SELECT id FROM Creature_Base WHERE name = 'Antock'));


-- Creature_Atack
DELETE FROM Creature_Atack;

INSERT INTO Creature_Atack (Atack_id, Creature_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Atack WHERE name = 'Ataque 1'), (SELECT id FROM Creature WHERE name = 'Antock1'), 1, 0, 10);

INSERT INTO Creature_Atack (Atack_id, Creature_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Atack WHERE name = 'Ataque 2'), (SELECT id FROM Creature WHERE name = 'Antock1'), 1, 0, 10);

INSERT INTO Creature_Atack (Atack_id, Creature_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Atack WHERE name = 'Ataque 3'), (SELECT id FROM Creature WHERE name = 'Antock1'), 1, 0, 10);


-- Delete till here

SET FOREIGN_KEY_CHECKS=1;
