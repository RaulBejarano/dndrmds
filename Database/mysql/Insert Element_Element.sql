DELETE FROM Element_Element;

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'FLAME'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'LEAF'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'NORMAL'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'INSECT'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FLAME'), (SELECT id FROM Element WHERE name = 'IRON'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'FLAME'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'DROP'), (SELECT id FROM Element WHERE name = 'IRON'), 1);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'FLAME'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'DROP'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'THUNDER'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'WING'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'STONE'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'THUNDER'), (SELECT id FROM Element WHERE name = 'IRON'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'DROP'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'WING'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'INSECT'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'LEAF'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'COLD'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'WING'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'COLD'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'NORMAL'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'FLAME'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'LEAF'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'FIST'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'WING'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'MENTAL'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'INSECT'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'FIST'), (SELECT id FROM Element WHERE name = 'IRON'), 2);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'FLAME'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'VENOM'), (SELECT id FROM Element WHERE name = 'IRON'), 1);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'FLAME'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'THUNDER'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'FIST'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'INSECT'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'STONE'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'WING'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'FLAME'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'LEAF'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'FIST'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'VENOM'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'MENTAL'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'STONE'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'MENTAL'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'THUNDER'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'LEAF'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'COLD'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'FIST'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'VENOM'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'WING'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'MENTAL'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'STONE'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'INSECT'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'FLAME'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'DROP'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'THUNDER'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'LEAF'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'FIST'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'VENOM'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'STONE'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'STONE'), (SELECT id FROM Element WHERE name = 'IRON'), 1);

INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'NORMAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'FLAME'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'DROP'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'THUNDER'), 0.5);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'LEAF'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'COLD'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'FIST'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'VENOM'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'WING'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'MENTAL'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'INSECT'), 1);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'STONE'), 2);
INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ((SELECT id FROM Element WHERE name = 'IRON'), (SELECT id FROM Element WHERE name = 'IRON'), 0.5);
