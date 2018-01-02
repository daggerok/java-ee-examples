FROM daggerok/apache-tomcat:8.5.24-alpine

HEALTHCHECK --interval=1s --timeout=3s --retries=30 \
  CMD wget -q --spider http://127.0.0.1:8080/plain-http-servlet/ || exit 1

ARG JPDA_OPTS_ARG="${JAVA_OPTS} -agentlib:jdwp=transport=dt_socket,address=1043,server=y,suspend=n"
ENV JPDA_OPTS="${JPDA_OPTS_ARG}"
EXPOSE 5005

COPY ./build/libs/*.war ${TOMCAT_HOME}/webapps/
