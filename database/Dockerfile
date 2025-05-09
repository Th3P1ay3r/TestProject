FROM mysql
WORKDIR /tmp
COPY db/mysqlsampledatabase.sql /docker-entrypoint-initdb.d
ENV MYSQL_ROOT_PASSWORD root