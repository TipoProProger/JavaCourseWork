INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Коммерческая корпоративная организация', 1, 'commercial_corparate_organisation');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'ИП', 5, 'individual_enterpreneur');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Коммерческая унитарная организация', 6, 'commercial_unitary_organisation');

INSERT INTO OKFS(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Частная собственность', 16, 'private_property');
INSERT INTO OKFS(id, name, number, sysname) VALUES (nextval('hibernate_sequence'), 'Собственность российских граждан проживающих за границей', 18, 'property_of_russian_citizens_living_abroad');

INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Модератор', 'admin-user');
INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Эксперт', 'expert-user');
INSERT INTO ROLE(id, name, sysname) VALUES (nextval('hibernate_sequence'), 'Пользователь', 'simple-user');

INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'Малышева Алина Алексеевна', '89109675954', 'tsareva.alina@gmail.com', 'Тестировщик в БИВе', 7);
INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (nextval('hibernate_sequence'), 'fucker kekovich', '1234567890', 'fucking@mail.com', '_NONE', 8);

INSERT INTO approvement(id, number, info, scanTaxsApr, scanCourtApr, user_id) VALUES (nextval('hibernate_sequence'), 'kek','ok', 0, 0, 9);
INSERT INTO approvement(id, number, info, scanTaxsApr, scanCourtApr, user_id) VALUES (nextval('hibernate_sequence'), 'kek','zaeb ok', 0, 0, 9);

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

INSERT INTO approvement(id, number, info, scanTaxsApr, scanCourtApr, user_id) VALUES (nextval('hibernate_sequence'), 'kek', 'ok', 0, 0, 9);
INSERT INTO approvement(id, number, info, scanTaxsApr, scanCourtApr, user_id) VALUES (nextval('hibernate_sequence'), 'kek', 'ok', 0, 0, 9);

INSERT INTO BUSINESS(id, shortname, fullname, cost, busemail, okopf, okfs, inn, ogrn, okato, taxdebt, courtcases, okfs_id, okopf_id, approvement_id)
VALUES (nextval('hibernate_sequence'), 'РозаМимоза', 'Общество с ограниченной ответственностью "РозаМимоза"', 10000000, 'tyty@ty.bambam', '123', '164', '345', '456', '567', 10, 2, 4, 2, 21);

INSERT INTO BUSINESS(id, shortname, fullname, cost, busemail, okopf, okfs, inn, ogrn, okato, taxdebt, courtcases, okfs_id, okopf_id, approvement_id)
VALUES (nextval('hibernate_sequence'), 'БульБульКарасики', 'Общество с ограниченной ответственностью "БульБульКарасики"', 30000000, 'ty@ty.babam', '12356', '16467', '34556', '4564', '567', 10, 2, 4, 2, 22);

INSERT INTO BUSINESS_EXTENDED(id, telephone, business_id) 
VALUES(nextval('hibernate_sequence'), '89109767676', 23);
INSERT INTO BUSINESS_EXTENDED(id, telephone, kpp, business_id) 
VALUES(nextval('hibernate_sequence'), '89099099900', '8989898910',24);

INSERT INTO advertisement(id, status, likes, dislikes, user_id, business_id) 
VALUES (nextval('hibernate_sequence'), 'на проверке эксперта', 0, 0, 10, 23);
INSERT INTO advertisement(id, status, likes, dislikes, user_id, business_id) 
VALUES (nextval('hibernate_sequence'), 'на модерации', 0, 0, 10, 24);