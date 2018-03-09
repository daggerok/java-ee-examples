###FROM daggerok/jboss4-java5
###MAINTAINER Maksim Kostormin https://github.com/daggerok
###EXPOSE 8080 8009 8093 8083
###COPY --chown=jboss ./target/*.war ${JBOSS_HOME}/server/default/deploy/
##
##FROM daggerok/apache-tomcat:9.0.2-alpine
##COPY ./target/*.war ${TOMCAT_HOME}/webapps/

FROM openjdk:8u151-jre-alpine3.7
MAINTAINER Maksim Kostromin https://github.com/daggerok
ARG JBOSS_VERSION_ARG="4.2.3.GA"
ENV JBOSS_VERSION="${JBOSS_VERSION_ARG}"
ENV JBOSS_HOME="/opt/jboss-${JBOSS_VERSION}"
RUN apk --no-cache --update add bash curl unzip wget \
 && wget --no-cookies \
         --no-check-certificate \
         --header "Cookie: oraclelicense=accept-securebackup-cookie" \
                  "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" \
         -O /tmp/jce_policy-8.zip \
 && unzip -o /tmp/jce_policy-8.zip -d /tmp \
 && mv -f ${JAVA_HOME}/lib/security ${JAVA_HOME}/lib/backup-security \
 && mv -f /tmp/UnlimitedJCEPolicyJDK8 ${JAVA_HOME}/lib/security \
 && wget --no-check-certificate \
             -O /tmp/jboss-${JBOSS_VERSION}.zip \
             https://sourceforge.net/projects/jboss/files/JBoss/JBoss-${JBOSS_VERSION}/jboss-${JBOSS_VERSION}.zip \
 && unzip -d /opt /tmp/jboss-${JBOSS_VERSION}.zip \
 && chmod +x ${JBOSS_HOME}/bin/run.sh \
 && apk del unzip \
 && rm -rf /var/cache/apk/* /tmp/*
ENTRYPOINT bash ${JBOSS_HOME}/bin/run.sh
CMD /bin/bash
EXPOSE 8080 8009 8083 8093
#HEALTHCHECK --timeout=1s \
#            --retries=35 \
#            CMD curl -f http://127.0.0.1:8080/jboss4-java5/health || exit 1
COPY ./target/*.war ${JBOSS_HOME}/server/default/deploy/
