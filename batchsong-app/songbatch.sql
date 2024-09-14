CREATE DATABASE songbatch;

CREATE TABLE Song (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

-- Song
INSERT INTO Song VALUES(1, 'Style');


-- Drop tables
DROP TABLE Song;








