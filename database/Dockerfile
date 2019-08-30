FROM postgres:11
LABEL maintainer="denishuamanacu@gmail.com"

ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=ventas

COPY ./scripts/ /docker-entrypoint-initdb.d/
RUN chmod a+r /docker-entrypoint-initdb.d/*