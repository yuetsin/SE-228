use bookie;
select host, user from user;

create user docker identified by '123456docker';

grant all on bookie.* to docker@'47.103.15.32' identified by '123456docker' with grant option;

flush privileges;