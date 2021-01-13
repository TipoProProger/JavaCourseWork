INSERT INTO OKOPF(id, name, number, sysname) VALUES (0, 'okopf1', 11, 'sysNameOkopf1');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (1, 'okopf2', 12, 'sysNameOkopf2');
INSERT INTO OKOPF(id, name, number, sysname) VALUES (2, 'okopf3', 13, 'sysNameOkopf3');

INSERT INTO OKFS(id, name, number, sysname) VALUES (0, 'okfs1', 21, 'sysNameOkfs1');
INSERT INTO OKFS(id, name, number, sysname) VALUES (1, 'okfs2', 22, 'sysNameOkfs2');
INSERT INTO OKFS(id, name, number, sysname) VALUES (2, 'okfs3', 23, 'sysNameOkfs2');

INSERT INTO ROLE(id, name, sysname) VALUES (0, 'fuckingRole', 'systemFuckingRoleName');

INSERT INTO USERS(id, fio, telephoneNumber, email, post, role_id) VALUES (0, 'KEKSER KEKOVICh KEKOV', '123456789', 'fucking@mail.to', 'post', 0);

INSERT INTO approvement(id, number, info, user_id) VALUES (0, 'kek', 1, 0);

INSERT INTO BUSINESS(id, shortname, fullname, cost, busemail, okopf, okfs, inn, ogrn, okato, taxdebt, courtcases, okfs_id, okopf_id, approvement_id) VALUES (0, 'shortName', 'fullName', 0, 'email@mail.ru', '123', '234', '345', '456', '567', 10, 2, 1, 2, 0);