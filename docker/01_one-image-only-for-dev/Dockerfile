FROM openjdk:8u131-jdk

# Locale setup
RUN apt-get update && \
    apt-get install -yq locales ca-certificates wget sudo && \
    rm -rf /var/lib/apt/lists/* && \
    localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8

# Add dev user
RUN useradd user -d /home/user -s /bin/bash && cp -r /etc/skel /home/user && chown user.user -R /home/user && echo "user ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers

# RabbitMQ
RUN echo 'deb http://www.rabbitmq.com/debian/ testing main' | tee /etc/apt/sources.list.d/rabbitmq.list && \
          wget -O- https://www.rabbitmq.com/rabbitmq-signing-key-public.asc | apt-key add -

# Dependencies
RUN export RABBITMQ_VERSION=3.6.10-1 && \
    export POSTGRESQL_VERSION=9.6.3-3 && \
    apt-get update && apt-get install -yq --force-yes \
        tmux vim curl bash zip \
        rabbitmq-server=$RABBITMQ_VERSION \
        postgresql-9.6=$POSTGRESQL_VERSION postgresql-client-9.6=$POSTGRESQL_VERSION postgresql-contrib-9.6=$POSTGRESQL_VERSION && \
        rm -rf /var/cache/apt/*

# Enable rabbitmq admin plugin
RUN /etc/init.d/rabbitmq-server start && \
    rabbitmq-plugins enable rabbitmq_management && \
    /etc/init.d/rabbitmq-server stop && \
    echo "[{rabbit, [{loopback_users, []}]}]." > /etc/rabbitmq/rabbitmq.config

# Configure database
USER root
COPY files/pg_hba.conf /etc/postgresql/9.6/main/pg_hba.conf
RUN /etc/init.d/postgresql start && \
    echo "CREATE DATABASE reverseapp; \
          CREATE ROLE reverseuser WITH LOGIN PASSWORD 'reversepass'; \
          GRANT ALL PRIVILEGES ON DATABASE reverseapp TO reverseuser;" | psql -U postgres && \
    /etc/init.d/postgresql stop

EXPOSE 8080 15672

USER user
WORKDIR /home/user

# Install SDKMan and Groovy
RUN curl -s "https://get.sdkman.io" | bash
RUN /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
                  sdk install groovy"

COPY ["files/start-services.sh", "/home/user/"]
COPY ["files/tmux.conf", "/home/user/.tmux.conf"]
