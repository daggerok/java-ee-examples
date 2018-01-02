FROM daggerok/jboss-wildfly:11.0.0.Final-alpine
HEALTHCHECK --interval=1s --timeout=3s --retries=30 \
 CMD wget -q --spider http://127.0.0.1:8080/plain-http-servlet/ \
  || exit 1
ADD ./build/libs/*.war ${JBOSS_HOME}/standalone/deployments/
