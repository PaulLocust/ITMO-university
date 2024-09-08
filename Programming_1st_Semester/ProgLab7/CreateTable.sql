DROP TABLE IF EXISTS group_admin CASCADE;
DROP TABLE IF EXISTS study_groups CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users(
    id serial PRIMARY KEY,
    login text NOT NULL,
    password text NOT NULL);

CREATE TABLE study_groups(
    id serial PRIMARY KEY,
    name text NOT NULL,
    x_coordinate bigint,
    y_coordinate double precision NOT NULL,
    creation_date DATE NOT NULL,
    students_count bigint check ( students_count > 0 ),
    transferred_students bigint check ( transferred_students > 0 ),
    average_mark bigint check ( average_mark > 0 ),
    semester text NOT NULL,
    admin_name text NOT NULL,
    admin_height float NOT NULL check ( admin_height > 0),
    admin_eye_color text NOT NULL,
    admin_hair_color text,
    admin_nationality text,
    admin_x_location_coordinate double precision NOT NULL,
    admin_y_location_coordinate double precision NOT NULL,
    admin_z_location_coordinate bigint NOT NULL,
    admin_location_name text,
    user_id integer NOT NULL );
