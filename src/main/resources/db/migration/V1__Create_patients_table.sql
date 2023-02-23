
CREATE TABLE public.patient (
                                id bigserial NOT NULL,
                                age int4 NULL,
                                "name" varchar(255) NULL,
                                CONSTRAINT patient_pkey PRIMARY KEY (id)
);

INSERT INTO patient (age, name) VALUES (30, 'John');
INSERT INTO patient (age, name) VALUES (25, 'Jane');
INSERT INTO patient (age, name) VALUES (40, 'Mary');
INSERT INTO patient (age, name) VALUES (50, 'Peter');
INSERT INTO patient (age, name) VALUES (28, 'Alice');
INSERT INTO patient (age, name) VALUES (32, 'Bob');
INSERT INTO patient (age, name) VALUES (45, 'Eve');
INSERT INTO patient (age, name) VALUES (37, 'Tom');
INSERT INTO patient (age, name) VALUES (42, 'Lisa');
INSERT INTO patient (age, name) VALUES (55, 'David');

CREATE TABLE public.specialty (
                                  id bigserial NOT NULL,
                                  "name" varchar(255) NULL,
                                  CONSTRAINT specialty_pkey PRIMARY KEY (id)
);


INSERT INTO specialty (name) VALUES ('Dermatology');
INSERT INTO specialty (name) VALUES ('Ophthalmology');
INSERT INTO specialty (name) VALUES ('Radiology');
INSERT INTO specialty (name) VALUES ('Family Medicine');
INSERT INTO specialty (name) VALUES ('Pediatrics');


CREATE TABLE public.pathology (
                                  id bigserial NOT NULL,
                                  pathology_id int8 NULL,
                                  "name" varchar(255) NULL,
                                  CONSTRAINT pathology_pkey PRIMARY KEY (id)
);



INSERT INTO public.pathology ( pathology_id, name) VALUES (100, 'Cancer');
INSERT INTO public.pathology ( pathology_id, name) VALUES (200, 'Heart disease');
INSERT INTO public.pathology ( pathology_id, name) VALUES (300, 'Diabetes');
INSERT INTO public.pathology ( pathology_id, name) VALUES (400, 'Stroke');
INSERT INTO public.pathology ( pathology_id, name) VALUES (500, 'Arthritis');


CREATE TABLE public.symptom (
                                id bigserial NOT NULL,
                                symptom_id int8 NULL,
                                "name" varchar(255) NULL,
                                CONSTRAINT symptom_pkey PRIMARY KEY (id)
);

INSERT INTO symptom (symptom_id, name) VALUES (10, 'Fever');
INSERT INTO symptom (symptom_id, name) VALUES (10, 'Fever');
INSERT INTO symptom (symptom_id, name) VALUES (10, 'Fever');
INSERT INTO symptom (symptom_id, name) VALUES (10, 'Fever');
INSERT INTO symptom (symptom_id, name) VALUES (10, 'Fever');


CREATE TABLE public.doctor (
                               id bigserial NOT NULL,
                               "name" varchar(255) NULL,
                               specialty_id int8 NULL,
                               CONSTRAINT doctor_pkey PRIMARY KEY (id)
);

INSERT INTO doctor(name, specialty_id) VALUES ('Joaquim',1);
INSERT INTO doctor(name, specialty_id) VALUES ('Joaquim',2);
INSERT INTO doctor(name, specialty_id) VALUES ('Joaquim',3);
INSERT INTO doctor(name, specialty_id) VALUES ('Joaquim',4);
INSERT INTO doctor(name, specialty_id) VALUES ('Joaquim',5);

