FROM daggerok/jboss-eap-6.4:v2

# admin user is already defined in base image
RUN ${JBOSS_HOME}/bin/add-user.sh mak Admin.123 --silent

# check all apps healthy
HEALTHCHECK --interval=2s --timeout=2s --retries=35 \
 CMD curl -f http://127.0.0.1:8080/rest-api/health \
  && curl -f http://127.0.0.1:8080/ui/ \
  || exit 1

# deploy apps
COPY ./rest-api/target/*.war ./ui/target/*.war ${JBOSS_HOME}/standalone/deployments/
