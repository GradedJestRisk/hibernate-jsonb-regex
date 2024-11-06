DROP TABLE IF EXISTS item;
CREATE TABLE item (id SERIAL PRIMARY KEY, content JSONB);
INSERT INTO item (content) VALUES ('{"reference": "a"}');
INSERT INTO item (content) VALUES ('{"reference": "a[b]"}');
INSERT INTO item (content) VALUES ('{"reference": "a[c]"}');
