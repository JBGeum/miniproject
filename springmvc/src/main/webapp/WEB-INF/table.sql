show databases;
CREATE DATABASE diarybbs;
create table diaryboard(
	no int auto_increment primary key,
    regdate datetime,
    title varchar(255) not null,    
    tag varchar(32),
    contents mediumtext not null,
    hit int default '0'    
    );
insert into diaryboard(regdate, title, tag, contents, hit) values(now(), 'title1', 'bad', '한글123', default);
update diaryboard set title='title1', tag='GOOD', contents='cont1' where no=1;
update diaryboard set title='title2', tag='BAD', contents='cont22' where no=2;
select * from diaryboard;
commit;

CREATE USER 'userdbbs'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON diarybbs.* to 'userdbbs'@'localhost';
flush privileges;
SHOW VARIABLES LIKE '%VERSION%';
alter user 'user1'@'localhost' identified with mysql_native_password by '1234';
use diarybbs;

drop table diaryboard;

insert into diaryboard(regdate, title, tag, contents, hit) values(now(), 'title1', 'bad', '한글123', default);
update diaryboard set title='title1', tag='GOOD', contents='cont1' where no=1;
update diaryboard set title='title2', tag='BAD', contents='cont22' where no=2;
select * from diaryboard;
delete from diaryboard where no=2;
commit;

show variables like 'c%';

CREATE USER 'user1'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON diarybbs.* to 'user1'@'%';
drop user 'user1'@'localhost';
FLUSH PRIVILEGES;
use mysql;
show grants for 'user1'@'localhost';
select user, host from mysql.user;
show variables where variable_name like '%dir%';