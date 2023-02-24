
INSERT INTO public.patient(id, age, name)
VALUES (1, 25, 'Manel');
INSERT INTO public.patient(id, age, name)
VALUES (2, 30, 'Joao');
INSERT INTO public.patient(id, age, name)
VALUES (3, 35, 'Pedro');
INSERT INTO public.patient(id, age, name)
VALUES (4, 40, 'Diogo');
INSERT INTO public.patient(id, age, name)
VALUES (5, 45, 'Fabio');


INSERT INTO public.pathology (id, pathology_id, name)
VALUES (1, 1, 'Cancer');
INSERT INTO public.pathology (id, pathology_id, name)
VALUES (2, 2, 'Heart disease');
INSERT INTO public.pathology (id, pathology_id, name)
VALUES (3, 3, 'Diabetes');
INSERT INTO public.pathology (id, pathology_id, name)
VALUES (4, 4, 'Stroke');
INSERT INTO public.pathology (id, pathology_id, name)
VALUES (5, 5, 'Arthritis');



INSERT INTO public.symptom (id, symptom_id, name)
VALUES (1, 1, 'cold');
INSERT INTO public.symptom (id, symptom_id, name)
VALUES (2, 2, 'Fever');
INSERT INTO public.symptom (id, symptom_id, name)
VALUES (3, 3, 'Fever');
INSERT INTO public.symptom (id, symptom_id, name)
VALUES (4, 4, 'Fever');
INSERT INTO public.symptom (id, symptom_id, name)
VALUES (5, 5, 'Fever');


INSERT INTO public.specialty(id, name)
VALUES (1, 'Dermatology');
INSERT INTO public.specialty(id, name)
VALUES (2, 'Ophthalmology');
INSERT INTO public.specialty(id, name)
VALUES (3, 'Radiology');
INSERT INTO public.specialty(id, name)
VALUES (4, 'Family Medicine');
INSERT INTO public.specialty(id, name)
VALUES (5, 'Pediatrics');



INSERT INTO public.doctor(id, name, specialty_id)
VALUES (1, 'Ferreira', 1);
INSERT INTO public.doctor(id, name, specialty_id)
VALUES (2, 'Pereira', 2);
INSERT INTO public.doctor(id, name, specialty_id)
VALUES (3, 'Pinheiro', 3);
INSERT INTO public.doctor(id, name, specialty_id)
VALUES (4, 'Costa', 4);
INSERT INTO public.doctor(id, name, specialty_id)
VALUES (5, 'Manel', 5);

INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (1, 1, 1);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (2, 2, 2);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (3, 3, 3);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (4, 4, 4);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (5, 5, 5);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (1, 2, 1);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (1, 3, 1);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (1, 5, 1);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (2, 5, 2);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (2, 4, 2);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (2, 3, 2);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (3, 5, 3);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (3, 4, 3);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (3, 3, 3);
INSERT INTO public.consultation(doctor_id, patient_id, specialty_id)
VALUES (3, 2, 3);


