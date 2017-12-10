FROM jboss/wildfly:11.0.0.Final
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin123 --silent
CMD /bin/bash
ENTRYPOINT /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
EXPOSE 8080 9990
COPY ./build/libs/*.war ${JBOSS_HOME}/standalone/deployments/app.war
