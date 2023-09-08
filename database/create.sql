create table mcmUser (
	uuid varchar(36) not null,
	login varchar(50) not null, 
	name varchar(150) not null,
	primary key (uuid)
);


create table mcmUser_sets (
	mcmUser_uuid varchar(36) not null,
	sets_code varchar(8) not null,
	primary key (mcmUser_uuid, sets_code),
	foreign key (mcmUser_uuid) references mcmUser(uuid),
	foreign key (sets_code) references sets(code)
);

create table mcmUserCard (
	uuid varchar(36) not null,
	qte integer not null,
	cond varchar(10) not null,
	card_type varchar(25) not null,
	cards_uuid varchar(36) not null,
	mcmUser_uuid varchar(36) not null,
	primary key (uuid),
	foreign key (mcmUser_uuid) references mcmUser(uuid)
);

create table mcmCondition (
	id integer not null,
	val varchar(10) not null,
	primary key (id)
);

create table mcmCardType (
	id integer not null,
	val varchar(100) not null,
	code varchar(25) not null,
	primary key (id)
);