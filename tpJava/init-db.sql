
DROP TABLE IF EXISTS personne;


CREATE TABLE personne(
	numero INTEGER ,
	prenom VARCHAR(64),
	nom VARCHAR(64),
	age INTEGER,
	PRIMARY KEY(numero));
	

INSERT INTO personne (numero,prenom,nom,age) VALUES (1,'alain', 'Therieur' , 35);

INSERT INTO personne (numero,prenom,nom,age) VALUES (2,'axelle', 'Aire' , 25 );



SELECT * FROM personne;
