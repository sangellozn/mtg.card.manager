create table pricesHistory (
	setCode varchar(8) not null,
	val_eur decimal(10,2),
	val_usd decimal(10,2),
	date_history date not null,
	primary key (setCode, date_history)
);

INSERT INTO scriptHistory (script_idx, script_name, createdon) VALUES ('004', 'prices_history.sql', datetime());
