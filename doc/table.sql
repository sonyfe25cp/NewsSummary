create table tac_sentences(
	id int NOT NULL AUTO_INCREMENT,
	date  varchar(10) NOT NULL ,
	sentences  text,
	termWeight  text,
	energy  text,
	summary  text,
	PRIMARY KEY (`id`)
);