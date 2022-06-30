drop table LOGIN;
drop table ORDERS;
drop table OPTIONS;
drop table CART;
drop table MEMBER;
drop table FILES;
drop table PRODUCT;
drop table CATEGORY;
drop sequence MEMBER_SEQ;
drop sequence ORDERS_SEQ;
drop sequence FILE_SEQ;
drop sequence CART_SEQ;
purge recyclebin;



create table MEMBER(
	MID varchar2(15),
	MPW varchar2(15) not null,
	MNO number(4) not null,
	MNAME varchar2(15) not null,
	MPHNO varchar2(15) not null,
	MPOINT number(6),
	constraint MEMBER_PK primary key(MID)
);

create table CATEGORY(
	CNO number(3),
	CNAME varchar2(50) NOT NULL,
	constraint CATEGORY_PK primary key(CNO)
);

create table PRODUCT(
	PNO number,
	PNAME varchar2(50) NOT null,
	PSAL number(5) Not null,
	PSTATE number(1) DEFAULT 1 not null, -- 1은 판매중 2는 솔드아웃
	CNO number(3) not null,
	constraint PRODUCT_PK primary key(PNO),
	constraint PRODUCT_FK foreign key(CNO) references CATEGORY(CNO) on delete cascade
);

create table OPTIONS(
	OPD number(2),
	OPP number(4) not null,
	constraint OPTIONS_PK primary key(OPD)
);

create table CART(
	CDNO number(3),
	MID varchar2(15) not null,
	PNO number NOT null,
	COP1 number(2),
	COP2 number(2),
	COP3 number(2),
	COP4 number(2),
	COP5 number(2),
	constraint CART_PK primary key(CDNO),
	constraint CART_FK1 foreign key(MID) references MEMBER(MID) on delete cascade,
	constraint CART_FK2 foreign key(PNO) references PRODUCT(PNO) on delete cascade
);

create table ORDERS(
	ONO varchar2(15),
	MID varchar2(15) not null,
	PNO number not null,
	ODNO number not null,
	ODATE date not null,
	OSTATE number(1) DEFAULT 1, --1은 주문대기 2는 주문확정
	PSAL number(5) not null,
	COP1 number(2) not null,
	COP2 number(2) not null,
	COP3 number(2) not null,
	COP4 number(2) not null,
	COP5 number(2) not null,
	constraint ORDERS_PK primary key(ONO),
	constraint ORDERS_FK foreign key(MID) references MEMBER(MID) on delete cascade,
	constraint ORDERS_FK2 foreign key(PNO) references PRODUCT(PNO) on delete cascade 
);

create table LOGIN(
    MID varchar2(10), 
    MPWD varchar2(10) NOT NULL, 
    constraint LOGIN_PK primary key(MID), 
    constraint LOGIN_FK foreign key(MID) references MEMBER(MID) on delete cascade
);

create table FILES(
	FNO number,
	PNO number not null,
	FPATH varchar2(300) not null,
	FEXT varchar2(30) not null,
	constraint FILES_PK primary key(FNO),
	constraint FILES_KF foreign key(PNO) references PRODUCT(PNO) on delete cascade
);

--시퀀스

create sequence MEMBER_SEQ increment by 1 start with 1 nocache;
create sequence ORDERS_SEQ increment by 1 start with 1 nocache;
create sequence FILE_SEQ increment by 1 start with 1 nocache;
create sequence CART_SEQ increment by 1 start with 1 nocache;


-- insert into file values(FILE_SEQ, 111, '.src\coffee1_americano','png'); <-파일이미지 불러오는 SQL문