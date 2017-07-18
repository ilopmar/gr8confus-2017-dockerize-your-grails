FROM postgres:9.6.3

VOLUME /var/lib/postgresql/data

COPY ["setup.sh", "/docker-entrypoint-initdb.d/"]

EXPOSE 5432
