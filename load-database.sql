DROP TABLE IF EXISTS item;
CREATE TABLE item (id SERIAL PRIMARY KEY, content JSONB);
INSERT INTO item (content) VALUES ('{"reference": "a"}');
INSERT INTO item (content) VALUES ('{"reference": "ab"}');
INSERT INTO item (content) VALUES ('{"reference": "a[ab]"}');
INSERT INTO item (content) VALUES ('{"reference": "a[bc]"}');
INSERT INTO item (content) VALUES ('{"reference": "a.a[ab]"}');
INSERT INTO item (content) VALUES ('{"reference": "a.a[bc]"}');
INSERT INTO item (content) VALUES ('{"reference": "a.a"}');
