drop table users;

drop sequence seq_user_no;

create table users(
    no number(5),
    id varchar2(20) unique not null,
    password varchar2(20) not null,
    name varchar2(20),
    gender varchar2(10),
    PRIMARY key(no)
);

create sequence seq_user_no
INCREMENT by 1
start with 1;

insert into users
values(seq_user_no.nextval,
       'googurie',
       '1234',
       'jaeyeong',
       'male'
);

----------------------------------------------------

select *
from users;