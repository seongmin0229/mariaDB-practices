select version(), current_date, now()
from dual;

-- 수학함수, 사칙 연산도 한다.
select sign(pi() / 4), 1 + 2 * 3 - 4 / 5
from dual;

-- 대소문자 구분이 없다.
select VERsION(), current_date()
from dual;

-- table 생성: DDL
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- scheme 확인
describe pet;
desc pet;

-- table 삭제: DDL
drop table pet;
show tables;

-- insert: DML(C)
insert 
into pet()
values ('다옹이', '이성민', '고양이', 'm', '2022-09-01', null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet
set name = 'daongee'
where name = '다옹이';

-- delete: DML(D)
delete 
from pet
where name = 'daongee';

-- load data
load data local infile 'c:\\pet.txt' into table pet;

-- 내용에도 대소문자 구분이 없다.
update pet
set death = null
where name != 'bowser';

-- select 연습
select name, species
from pet
where name = 'Bowser';

select name, species, birth
from pet
where birth >= '1998-01-01';