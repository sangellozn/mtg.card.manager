create table cardsImagery (
	uuid varchar(36) not null,
	url_normal varchar(250),
	url_small varchar(250),
	card_uuid uuid varchar(36) not null,
	primary key (uuid)
);
