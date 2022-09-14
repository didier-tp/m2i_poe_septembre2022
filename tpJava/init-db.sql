
DROP TABLE IF EXISTS personne;


CREATE TABLE personne(
	numero bigint AUTO_INCREMENT,
	prenom VARCHAR(64),
	nom VARCHAR(64),
	age INTEGER,
	PRIMARY KEY(numero));
	

INSERT INTO personne (prenom,nom,age) VALUES ('alain', 'Therieur' , 35);

INSERT INTO personne (prenom,nom,age) VALUES ('axelle', 'Aire' , 25 );


SELECT * FROM personne;
