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

create table cardsImagery (
	uuid varchar(36) not null,
	url_normal varchar(250),
	url_small varchar(250),
	card_uuid uuid varchar(36) not null,
	primary key (uuid)
);

create table scriptHistory (
	script_idx varchar(5) not null,
	script_name varchar(150) not null,
	createdon timestamp not null,
	primary key (script_idx)
);

create table cardsPrices (
	uuid varchar(36) not null,
	card_uuid varchar(36) not null, 
	val_eur decimal(10,2),
	val_eur_foil decimal(10,2),
	val_usd decimal(10,2),
	val_usd_foil decimal(10,2),
	last_updated timestamp not null,
	primary key (uuid)
);
