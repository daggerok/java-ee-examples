FROM openjdk:8u131-jre-alpine
MAINTAINER Maksim Kostromin https://github.com/daggerok
RUN apk --no-cache add busybox-suid bash curl \
 && addgroup app-group \
 && adduser -h /home/app -s /bin/bash -D -u 1025 app app-group
USER app
WORKDIR /home/app
VOLUME ["/home/app"]
ARG java_opts=" -Djava.net.preferIPv4Stack=true "
ENV JAVA_OPTS=${java_opts}
ENTRYPOINT java ${JAVA_OPTS} -jar ./app.jar
EXPOSE 8000
HEALTHCHECK --timeout=2s --retries=30 CMD curl -f http://127.0.0.1:8000/ || exit 1
COPY ./target/*.jar ./app.jar
