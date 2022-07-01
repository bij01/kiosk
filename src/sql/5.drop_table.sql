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
purge recyclebin;

alter session set "_oracle_script"=true;
drop user Team2 cascade;

show user