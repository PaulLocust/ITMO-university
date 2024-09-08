#!/bin/bash

# OPEN
psql -h pg -d studs


create TABLE profession (id SERIAL PRIMARY KEY, name TEXT NOT NULL, description TEXT);
create TABLE person (id SERIAL PRIMARY KEY, name TEXT NOT NULL, profession_id BIGINT NOT NULL REFERENCES profession(id));
create TABLE cyber_evolution (id SERIAL PRIMARY KEY, variation INTEGER NOT NULL, description TEXT NOT NULL);
create TABLE supercomputer (id SERIAL PRIMARY KEY, efficiency_index SMALLINT NOT NULL DEFAULT 100);
create TABLE robotic_construction (id SERIAL PRIMARY KEY, weight_kg INTEGER NOT NULL, material TEXT NOT NULL DEFAULT 'Adamantine', supercomputer_id BIGINT NOT NULL REFERENCES supercomputer(id), cyber_evolution_id BIGINT NOT NULL REFERENCES cyber_evolution(id));
create TABLE planet (id SERIAL PRIMARY KEY, name TEXT NOT NULL, age BIGINT NOT NULL, galaxy TEXT NOT NULL);
create TABLE location (id SERIAL PRIMARY KEY, name TEXT NOT NULL, planet_id BIGINT NOT NULL REFERENCES planet(id), robotic_construction_id BIGINT NOT NULL REFERENCES robotic_construction(id));
create TABLE expedition (id SERIAL PRIMARY KEY, location_id BIGINT NOT NULL REFERENCES location(id), person_id BIGINT NOT NULL REFERENCES person(id), description TEXT DEFAULT 'Expedition journal is empty');

# Заполение таблицы profession
INSERT INTO profession VALUES (DEFAULT, 'Scientist', 'Famous scientist from faraway galaxy'); # 1
INSERT INTO profession VALUES (DEFAULT, 'Head hunter', 'Famous head hunter from faraway galaxy'); # 2
INSERT INTO profession VALUES (DEFAULT, 'Colonizer', 'Famous colonizer from faraway galaxy'); # 3
INSERT INTO profession VALUES (DEFAULT, 'Trader', 'Famous trader from faraway galaxy'); # 4

# Заполнение таблицы person
INSERT INTO person VALUES (DEFAULT, 'Olvin', 1);

# Заполнение таблицы cyber_evolution
INSERT INTO cyber_evolution VALUES (DEFAULT, 1, 'variation-number-1: (science) updated Operational System and AI, nebular batteries');
INSERT INTO cyber_evolution VALUES (DEFAULT, 2, 'variation-number-2: (guardian) extended weaponry, missile systems and armor, dark matter reactor');
INSERT INTO cyber_evolution VALUES (DEFAULT, 3, 'variation-number-3: (production) high quality laser tools, nebular batteries');

# Заполнение таблицы supercomputer
INSERT INTO supercomputer VALUES (DEFAULT);

# Заполнение таблицы robotic_construction
INSERT INTO robotic_construction VALUES (DEFAULT, 50000, 'Titan', 1, 1);
INSERT INTO robotic_construction VALUES (DEFAULT, 250000, DEFAULT, 1, 2);
INSERT INTO robotic_construction VALUES (DEFAULT, 100000, 'Composite', 1, 3);

# Заполнение таблицы planet
INSERT INTO planet VALUES (DEFAULT, 'Earth', 5540000000, 'Milky Way');

# Заполнение таблицы location
INSERT INTO location VALUES (DEFAULT, 'Ancient Cave', 1, 3);

# Заполнение таблицы expedition
INSERT INTO expedition VALUES (DEFAULT, 1, 1, DEFAULT);
