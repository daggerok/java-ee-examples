FROM openjdk:8u151-jre-alpine3.7
MAINTAINER Maksim Kostromin https://github.com/daggerok
RUN apk --no-cache add busybox-suid bash curl sudo \
 && adduser -h /home/appuser -s /bin/bash -D -u 1025 appuser wheel \
 && echo "appuser ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers \
 && sed -i "s/.*requiretty$/Defaults !requiretty/" /etc/sudoers \
 && apk del busybox-suid \
 && rm -rf /tmp/* /var/cache/apk/*
USER appuser
WORKDIR /home/appuser
VOLUME /home/appuser
ENV JAVA_OPTS="\
 -XX:+UnlockExperimentalVMOptions \
 -XX:+UseCGroupMemoryLimitForHeap \
 -XshowSettings:vm \
 -Djava.net.preferIPv4Stack=true "
ENTRYPOINT java ${JAVA_OPTS} -jar ./app.jar
CMD /bin/bash
EXPOSE 8080
HEALTHCHECK --timeout=2s \
            --retries=22 \
            CMD curl -f http://127.0.0.1:8080/ || exit 1
COPY --chown=appuser ./target/*.jar ./app.jar
