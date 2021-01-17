INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Коммерческая корпоративная организация', 1, 'commercial_corparate_organisation');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'ИП', 5, 'individual_enterpreneur');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Коммерческая унитарная организация', 6, 'commercial_unitary_organisation');

INSERT INTO OKFS(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Частная собственность', 16, 'private_property');
INSERT INTO OKFS(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Собственность российских граждан проживающих за границей', 18, 'property_of_russian_citizens_living_abroad');

INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Модератор', 'moderator');
INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Эксперт', 'expert');
INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Пользователь', 'simple_user');

INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'Малышева Алина Алексеевна', '89109675954', 'tsareva.alina@gmail.com', 'Тестировщик в БИВе', 7);
INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'fucker kekovich', '1234567890', 'fucking@mail.com', '_NONE', 8);

INSERT INTO approvement(id, number, info, user_id) VALUES (nextval('hibernate_sequence'), 'kek', 'ok', 9);
INSERT INTO approvement(id, number, info, user_id) VALUES (nextval('hibernate_sequence'), 'kek', 'zaeb ok', 9);

INSERT INTO BUSINESS(id, shortname, fullname, cost, busemail, okopf, okfs, inn, ogrn, okato, taxdebt, courtcases, okfs_id, okopf_id, approvement_id) 
    VALUES (nextval('hibernate_sequence'), 'shortName', 'fullName', 0, 'email@mail.ru', '123', '234', '345', '456', '567', 10, 2, 4, 2, 11);

INSERT INTO BUSINESS(id, shortname, fullname, cost, busemail, okopf, okfs, inn, ogrn, okato, taxdebt, courtcases, okfs_id, okopf_id, approvement_id) 
    VALUES (nextval('hibernate_sequence'), 'РожкиДаНожки', 'Общество с ограниченной ответственностью "РожкиДаНожки"', 20000000, 'tyty@ty.bebe', '123', '234', '345', '456', '567', 10, 2, 4, 2, 12);

INSERT INTO BUSINESS_EXTENDED(id, telephone, business_id) VALUES(nextval('hibernate_sequence'), 'fucking number', 13);
INSERT INTO BUSINESS_EXTENDED(id, telephone, business_id) VALUES(nextval('hibernate_sequence'), 'fucking number telephone', 14);

INSERT INTO advertisement(id, status, likes, dislikes, placeDate, user_id, business_id) VALUES (nextval('hibernate_sequence'), 'размещено', 1, 1, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 10, 13);
INSERT INTO advertisement(id, status, likes, dislikes, placeDate, user_id, business_id) VALUES (nextval('hibernate_sequence'), 'размещено', 1, 0, TO_DATE('18/12/2015', 'DD/MM/YYYY'), 10, 14);

INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'Модератор пользователь', '1234567890', 'moderator@mail.com', 'Модератор в БизнесВита', 6);
INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'Эксперт пользователь', '1234567890', 'expert@mail.com', 'Эксперт в БизнесВита', 7);