FROM daggerok/jboss:eap-6.4-alpine
MAINTAINER Maksim Kostromin https://github.com/daggerok/docker

# check all apps healthy (in current example we are expecting to have apps with /ui/ and /rest-api/ contexts deployed:
HEALTHCHECK --interval=1s --timeout=3s --retries=30 \
 CMD wget -q --spider http://127.0.0.1:8080/rest-api/health \
  && wget -q --spider http://127.0.0.1:8080/ui/ \
  || exit 1

# deploy apps
COPY ./rest-api/target/*.war ./ui/target/*.war ${JBOSS_HOME}/standalone/deployments/
