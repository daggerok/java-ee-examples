FROM jboss/wildfly:11.0.0.Final
# set JBOSS_HOME=/opt/jboss/wildfly
RUN ${JBOSS_HOME}/bin/add-user.sh admin Admin123 --silent
CMD /bin/bash
ENTRYPOINT ${JBOSS_HOME}/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
EXPOSE 8080 9990
HEALTHCHECK --interval=2s --timeout=2s --retries=35 CMD curl -f http://127.0.0.1:8080/app/health || exit 1
ADD ./target/*.war ${JBOSS_HOME}/standalone/deployments/app.war
