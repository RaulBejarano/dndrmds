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

-- States (Note: add at least 3 more)
DELETE FROM State;

INSERT INTO State (name, abreviation) VALUES ('Normal', 'NOR');
INSERT INTO State (name, abreviation) VALUES ('Burn', 'BUR');
INSERT INTO State (name, abreviation) VALUES ('Confused', 'CON');
INSERT INTO State (name, abreviation) VALUES ('Electrized', 'ELE');


-- Dandremid Base. These Dandremid_Bases are all the Dandremid types in the game
DELETE FROM Dandremid_Base;

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) 
VALUES ('Adreto', (SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'NONE'), 6, 4, 5, 10, 34,
'This is Adreto\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) 
VALUES ('Lasentu', (SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'NONE'), 4, 6, 5, 10, 32,
'This is Lasentu\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) 
VALUES ('Norti', (SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'NONE'), 5, 4, 6, 10, 37,
'This is Norti\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) 
VALUES ('Tenko', (SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'NONE'), 4, 6, 5, 10, 33,
'This is Tenko\'s description');

INSERT INTO Dandremid_Base (name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description) 
VALUES ('Topa', (SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'NONE'), 6, 4, 5, 10, 36,
'This is Topa\'s description');


-- Attack
DELETE FROM Attack;

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 1', (SELECT id FROM Element WHERE name = 'DROP'), 10, 0, 1);

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 2', (SELECT id FROM Element WHERE name = 'DROP'), 9, 0, 2);

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 3', (SELECT id FROM Element WHERE name = 'DROP'), 8, 1, 3);

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 4', (SELECT id FROM Element WHERE name = 'DROP'), 7, 0, 4);

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 5', (SELECT id FROM Element WHERE name = 'FLAME'), 6, 4, 1);

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 6', (SELECT id FROM Element WHERE name = 'THUNDER'), 5, 0, 1);

INSERT INTO Attack (name, Element_id, strike, heal, minimumLevel) 
VALUES ('Ataque 7', (SELECT id FROM Element WHERE name = 'LEAF'), 4, 4, 1);


-- The part downhere must be deleted

-- Users
DELETE FROM User;

INSERT INTO User (playerName, password, email, name, surname, birth, gender, level, exp, expNextLevel) 
VALUES ('a', 'a', 'parry907@gmail.com', 'Raúl', 'Bejarano Parrilla', '17/04/1990', 'Male', 2, 10, 30);


-- Dandremid
DELETE FROM Dandremid;

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Adreto', 1, 0, 100, -1, 6, 4, 5, 30, 30, 1, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Adreto'));

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Lasentu', 1, 0, 100, -1, 6, 4, 5, 30, 30, 1, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Lasentu'));

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Norti', 1, 0, 100, -1, 6, 4, 5, 30, 30, 1, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Norti'));

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Tenko', 1, 0, 100, -1, 6, 4, 5, 30, 30, 1, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Tenko'));

INSERT INTO Dandremid (name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, life, maxLife, happiness, User_id, Dandremid_Base_id) 
VALUES ('Topa', 1, 0, 100, -1, 6, 4, 5, 30, 30, 1, 100, 100, 90, (SELECT id FROM User WHERE playerName = 'a'), (SELECT id FROM Dandremid_Base WHERE name = 'Topa'));



-- Dandremid_Attack
DELETE FROM Dandremid_Attack;

INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Attack WHERE name = 'Ataque 1'), (SELECT id FROM Dandremid WHERE name = 'aDrEtO'), 1, 0, 10);

INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Attack WHERE name = 'Ataque 2'), (SELECT id FROM Dandremid WHERE name = 'aDrEtO'), 1, 0, 10);

INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses) 
VALUES ((SELECT id FROM Attack WHERE name = 'Ataque 3'), (SELECT id FROM Dandremid WHERE name = 'aDrEtO'), 1, 0, 10);


INSERT INTO Dandremid_State(Dandremid_id, State_id) VALUES ((SELECT id FROM Dandremid WHERE name = 'aDrEtO'),(SELECT id FROM State WHERE name = 'Burn'));


-- Delete till here

SET FOREIGN_KEY_CHECKS=1;
