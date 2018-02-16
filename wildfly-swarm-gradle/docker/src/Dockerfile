#FROM openjdk:8u131-jre-alpine
#MAINTAINER Maksim Kostromin <daggerok@gmail.com>
#RUN mkdir -p /var/app \
# && apk --no-cache add curl bash
#WORKDIR /var/app
#VOLUME ["/var/app"]
#CMD java -Djava.net.preferIPv4Stack=true -jar /var/app/app.jar
#EXPOSE 8080
#HEALTHCHECK --interval=5s --timeout=5s --retries=5 CMD curl -f http://127.0.0.1:8080/ || exit 1
#COPY ./build/libs/*.jar ./app.jar

FROM openjdk:8u151-jre-alpine3.7
MAINTAINER Maksim Kostromin https://github.com/daggerok
RUN apk --no-cache --update add busybox-suid bash curl unzip sudo openssh-client shadow wget \
 && adduser -h /home/appuser -s /bin/bash -D -u 1025 appuser wheel \
 && echo "appuser ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers \
 && sed -i "s/.*requiretty$/Defaults !requiretty/" /etc/sudoers \
 && wget --no-cookies \
         --no-check-certificate \
         --header "Cookie: oraclelicense=accept-securebackup-cookie" \
                  "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" \
         -O /tmp/jce_policy-8.zip \
 && unzip -o /tmp/jce_policy-8.zip -d /tmp \
 && mv -f ${JAVA_HOME}/lib/security ${JAVA_HOME}/lib/backup-security \
 && mv -f /tmp/UnlimitedJCEPolicyJDK8 ${JAVA_HOME}/lib/security \
 && apk del busybox-suid unzip openssh-client shadow wget \
 && rm -rf /var/cache/apk/* /tmp/*
USER appuser
WORKDIR /home/appuser
VOLUME /home/appuser
ARG JAVA_OPTS_ARGS="\
 -Djava.net.preferIPv4Stack=true \
 -XX:+UnlockExperimentalVMOptions \
 -XX:+UseCGroupMemoryLimitForHeap \
 -XshowSettings:vm "
ENV JAVA_OPTS="${JAVA_OPTS} ${JAVA_OPTS_ARGS}"
ENTRYPOINT java ${JAVA_OPTS} -jar ./app.jar
CMD /bin/bash
EXPOSE 8080
HEALTHCHECK --timeout=2s \
            --retries=22 \
            CMD curl -f http://127.0.0.1:8080/ || exit 1
COPY --chown=appuser ./build/libs/*-swarm.jar ./app.jar
