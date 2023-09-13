create unique index cardsImagery_card_uuid on cardsImagery (card_uuid);

create index cards_setCode on cards(setCode);

INSERT INTO scriptHistory (script_idx, script_name, createdon) VALUES ('002', 'indexes.sql', datetime());