alter session set "_oracle_script"=true;
create user Team2 identified by java123;

grant connect, resource, unlimited tablespace to Team2;

conn Team2 / java123;