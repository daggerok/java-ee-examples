FROM daggerok/jboss:4.2.3.GA
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \"" >> ${JBOSS_HOME}/bin/standalone.conf
EXPOSE 5005
ADD ./build/libs/*.war ${JBOSS_HOME}/server/default/deploy/app.war
