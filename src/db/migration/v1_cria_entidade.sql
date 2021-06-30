create table entidade (
	ent_codigo bigint not null,
	ent_inativo boolean not null,
	ent_login character varying(20),
	ent_senha character varying(20),
	constraint entidade_pkey primary key(ent_codigo),
	constraint entidade_ent_login_key unique(ent_login)
);

create table entidade_acesso (
	ent_codigo bigint not null,
	esa_codigo character varying(70),
	constraint ent_codigo_fkey foreign key (ent_codigo)
		references entidade(ent_codigo)
		match simple on update no action on delete no action
);

insert into entidade values (1, false, 'admin', '123');
insert into entidade values (2, false, 'leo', '123');

insert into entidade_acesso values (1, 'ADMIN');
insert into entidade_acesso values (1, 'USER');
insert into entidade_acesso values (2, 'USER');