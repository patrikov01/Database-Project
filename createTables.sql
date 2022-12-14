SET SCHEMA FN72097;
CREATE TABLE TEAM(
  NAME CHAR(20) NOT NULL  PRIMARY KEY,
  CITY VARCHAR(20) NOT NULL,
  STADIUM VARCHAR(20) NOT NULL,
  POINTS INT NOT NULL CHECK(POINTS>=0),
  MATCHES INT NOT NULL CHECK(MATCHES>=0),
  AMOUNT_OF_FOOTBALLERS INT NOT NULL CHECK(AMOUNT_OF_FOOTBALLERS >=5)
);
CREATE TABLE FOOTBALLER(
NAME CHAR(30) NOT NULL PRIMARY KEY,
NATIONALITY VARCHAR(20) NOT NULL,
AGE INT NOT NULL CHECK(AGE>0),
NUMBER INT NOT NULL CHECK(NUMBER>0),
MATCHES INT NOT NULL CHECK(MATCHES>=0),
GOALS INT NOT NULL CHECK(GOALS>=0),
ASSISTS INT NOT NULL CHECK(ASSISTS>=0),
POSITION VARCHAR(10) NOT NULL CHECK (POSITION IN ('Goalkeeper','Defender','Midfielder','Striker')),
TEAM_NAME CHAR(20) REFERENCES TEAM(NAME)
);

CREATE TABLE COACH(
  NAME CHAR(30) NOT NULL PRIMARY KEY,
  BIRTHDATE DATE NOT NULL,
  NATIONALITY VARCHAR(25) NOT NULL,
  TEAM_NAME CHAR(20) REFERENCES TEAM(NAME)
);
CREATE TABLE REFEREE(
  NAME CHAR(30) NOT NULL PRIMARY KEY,
  WORK_NUMBER INT NOT NULL CHECK(WORK_NUMBER>0),
  PHONE_NUMBER CHAR(10) NOT NULL,
  CITY VARCHAR(20) NOT NULL
);
CREATE TABLE MATCH(
  ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
  RESULT CHAR(3) NOT NULL CHECK(RESULT LIKE '%_:_%'),
  DATE DATE NOT NULL,
  TEAM_NAME1 CHAR(20) REFERENCES TEAM(NAME),
  TEAM_NAME2 CHAR(20) REFERENCES TEAM(NAME),
  REFEREE_NAME CHAR(30) REFERENCES REFEREE(NAME)
);


