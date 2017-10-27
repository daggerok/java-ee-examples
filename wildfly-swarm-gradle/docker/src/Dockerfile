FROM openjdk:8u131-jre-alpine
MAINTAINER Maksim Kostromin <daggerok@gmail.com>
RUN mkdir -p /var/app \
 && apk --no-cache add curl bash
WORKDIR /var/app
VOLUME ["/var/app"]
CMD java -Djava.net.preferIPv4Stack=true -jar /var/app/app.jar
EXPOSE 8080
HEALTHCHECK --interval=5s --timeout=5s --retries=5 CMD curl -f http://127.0.0.1:8080/ || exit 1
COPY ./build/libs/*.jar ./app.jar
