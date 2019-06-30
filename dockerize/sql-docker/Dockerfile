FROM mysql:5.7

VOLUME my.cnf:/etc/mysql/my.cnf mysql

ADD setup.sh /etc/mysql/setup.sh
ADD script/init_db.sql /etc/mysql/init_db.sql
ADD script/add_example_data.sql /etc/mysql/add_example_data.sql
ADD script/privileges.sql /etc/mysql/privileges.sql

EXPOSE 3306
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes

CMD ["sh", "/etc/mysql/setup.sh"]