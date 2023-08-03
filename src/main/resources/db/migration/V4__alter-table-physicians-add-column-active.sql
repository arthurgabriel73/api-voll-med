alter table physicians add active tinyint;
update physicians set active = 1;
alter table physicians modify column active tinyint not null;
