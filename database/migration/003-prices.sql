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

INSERT INTO scriptHistory (script_idx, script_name, createdon) VALUES ('003', 'prices.sql', datetime());