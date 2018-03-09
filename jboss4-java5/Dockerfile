FROM daggerok/jboss4
MAINTAINER Maksim Kostromin https://github.com/daggerok
#HEALTHCHECK --timeout=2s \
#            --retries=22 \
#            CMD curl -f http://127.0.0.1:8080/jboss4-java5/health || exit 1
COPY ./target/*.war ${JBOSS_HOME}/server/default/deploy/
