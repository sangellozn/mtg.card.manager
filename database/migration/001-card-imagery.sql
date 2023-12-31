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

INSERT INTO scriptHistory (script_idx, script_name, createdon) VALUES ('001', 'card-imagery.sql', datetime());