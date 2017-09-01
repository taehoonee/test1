create sequence kakao_text_contents_seq;
create table tbl_kakao_text_contents (
	txt_id integer primary key default nextval('kakao_text_contents_seq'),
	txt_content text not null,
	create_date timestamp default now(),
	modify_date timestamp default now()
);


create sequence kakao_reg_contents_seq;
create table tbl_kakao_reg_contents (
	reg_id integer primary key default nextval('kakao_reg_contents_seq'),
	reg_content text not null,
	create_date timestamp default now(),
	modify_date timestamp default now()
);


create table tbl_kakao_text_regular_link (
	txt_id integer,
	reg_id integer
	constraint tbl_kakao_text_regular_link_pk primary key(txt_id, reg_id)
);

