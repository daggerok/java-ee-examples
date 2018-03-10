FROM daggerok/jboss:eap-7.1:alpine
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \"" >> ${JBOSS_HOME}/bin/standalone.conf
EXPOSE 5005
HEALTHCHECK --interval=1s --timeout=3s --retries=30 \
 CMD wget -q --spider http://127.0.0.1:8080/plain-http-servlet/ \
  || exit 1
ADD ./target/*.war ${JBOSS_HOME}/standalone/deployments/
