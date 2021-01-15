INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Коммерческая корпоративная организация', 1, 'commercial_corparate_organisation');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'ИП', 5, 'individual_enterpreneur');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Коммерческая унитарная организация', 6, 'commercial_unitary_organisation');

INSERT INTO OKFS(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Частная собственность', 16, 'private_property');
INSERT INTO OKFS(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Собственность российских граждан проживающих за границей', 18, 'property_of_russian_citizens_living_abroad');

INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Модератор', 'moderator');
INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Эксперт', 'expert');
INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Пользователь', 'simple_user');

INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'Малышева Алина Алексеевна', '89109675954', 'tsareva.alina@gmail.com', 'Тестировщик в БИВе', 7);
INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'KEKSER KEKOVICh KEKOV', '123456789', 'fucking@mail.to', '_NONE', 6);

INSERT INTO approvement(id, number, info, user_id) VALUES (nextval('hibernate_sequence'), 'kek', '', 9);

INSERT INTO BUSINESS(id, shortname, fullname, cost, busemail, okopf, okfs, inn, ogrn, okato, taxdebt, courtcases, okfs_id, okopf_id, approvement_id) 
    VALUES (nextval('hibernate_sequence'), 'shortName', 'fullName', 0, 'email@mail.ru', '123', '234', '345', '456', '567', 10, 2, 4, 2, 11);

INSERT INTO BUSINESS_EXTENDED(id, telephone, business_id) VALUES(nextval('hibernate_sequence'), 'fucking number', 12);

INSERT INTO advertisement(id, status, likes, dislikes, user_id, business_id) VALUES (nextval('hibernate_sequence'), 'not ok', 1, 1, 9, 12);