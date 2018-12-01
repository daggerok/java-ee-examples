FROM daggerok/jboss-eap-6.4:6.4.21-alpine
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005\"" >> ${JBOSS_HOME}/bin/standalone.conf
EXPOSE 5005
HEALTHCHECK --timeout=1s --retries=66 \
        CMD wget -q --spider http://127.0.0.1:8080/app/health \
         || exit 1
ADD --chown=jboss ./jboss-eap-6.4/modules/org/postgresql ${JBOSS_HOME}/modules/org/postgresql
ADD --chown=jboss ./jboss-eap-6.4/standalone/configuration/standalone.xml ${JBOSS_HOME}/standalone/configuration/standalone.xml
ADD --chown=jboss ./target/*.war ${JBOSS_HOME}/standalone/deployments/app.war
