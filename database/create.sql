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
	qteFoil integer not null,
	cond varchar(4) not null,
	cards_uuid varchar(36) not null,
	mcmUser_uuid varchar(36) not null,
	primary key (uuid),
	foreign key (mcmUser_uuid) references mcmUser(uuid)
);
