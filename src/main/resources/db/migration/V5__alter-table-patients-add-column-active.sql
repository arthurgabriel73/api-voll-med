alter table patients add active tinyint;
update patients set active = 1;
alter table patients modify column active tinyint not null;
