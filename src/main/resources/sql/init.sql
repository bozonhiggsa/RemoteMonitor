-- noinspection SqlDialectInspectionForFile
INSERT INTO _user (id, username, password, enabled) VALUES (1,'admin', '1', true);

INSERT INTO authoritie (id, user_id, authority) VALUES (1,1, 'ROLE_ADMIN');
INSERT INTO authoritie (id, user_id, authority) VALUES (2,1, 'ROLE_USER');