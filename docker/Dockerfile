FROM ubuntu:14.04

RUN apt-get update
RUN apt-get -y install mysql-server
RUN /etc/init.d/mysql start \
    && mysql -uroot -e "grant all privileges on *.* to 'docker'@'%' identified by '123456docker';" \
    && mysql -uroot -e "grant all privileges on *.* to 'docker'@'47.103.15.32' identified by '123456docker';" \
    && mysql -uroot -e "grant all privileges on *.* to 'docker'@'localhost' identified by '123456docker';" 

RUN sed -Ei 's/^(bind-address|log)/#&/' /etc/mysql/my.cnf \
    && echo 'skip-host-cache\nskip-name-resolve' | awk '{ print } $1 == "[mysqld]" && c == 0 { c = 1; system("cat") }' /etc/mysql/my.cnf > /tmp/my.cnf \
    && mv /tmp/my.cnf /etc/mysql/my.cnf

EXPOSE 3306  
CMD ["/usr/bin/mysqld_safe"]
