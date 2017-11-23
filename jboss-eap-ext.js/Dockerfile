# Centos 7 / JBoss EAP 6.4
# docker build --force-rm --rm -t eap-6.4 .
# docker run --rm -it -p 8080:8080 --name java-ee-app eap-6.4
# docker rmi -v -f eap-6.4
FROM centos:7.4.1708

MAINTAINER Maksim Kostromin <daggerok@gmail.com>

ARG JBOS_UID="1025"
ARG JBOSS_USER="jboss"
ARG EAP_ZIP_ARCHIVE="jboss-eap-6.4.0.zip"
ARG EAP_URL="https://www.dropbox.com/s/xl2io9dhc6zxw9m/${EAP_ZIP_ARCHIVE}"
ARG JBOSS_HOME="/home/${JBOSS_USER}/jboss-eap-6.4"
ARG JBOSS_ADMIN_USER="admin"
ARG JBOSS_ADMIN_PASSWORD="Admin.123"

ENV JBOS_UID="${JBOS_UID}" \
    JBOSS_USER="${JBOSS_USER}"

RUN yum update-minimal --security -y \
 && yum -y install wget sudo openssh-clients unzip java-1.8.0-openjdk-devel \
 && yum clean all \
 && echo "${JBOSS_USER} ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers \
 && sed -i "s/.*requiretty$/Defaults !requiretty/" /etc/sudoers \
 && groupadd -r -g ${JBOS_UID} ${JBOSS_USER} \
 && useradd -c "JBoss EAP user" -d /home/${JBOSS_USER} -g ${JBOS_UID} -G ${JBOSS_USER} -m -r ${JBOSS_USER} \
 && echo ${JBOSS_USER}: | chpasswd \
 && usermod -a -G wheel ${JBOSS_USER}

USER ${JBOSS_USER}
WORKDIR /home/${JBOSS_USER}

ENV EAP_ZIP_ARCHIVE="${EAP_ZIP_ARCHIVE}" \
    EAP_URL="${EAP_URL}" \
    JBOSS_HOME="${JBOSS_HOME}" \
    JBOSS_ADMIN_USER="${JBOSS_ADMIN_USER}" \
    JBOSS_ADMIN_PASSWORD="${JBOSS_ADMIN_PASSWORD}"

EXPOSE 4447 8080 9990 9999
#ENTRYPOINT ${JBOSS_HOME}/bin/standalone.sh -c standalone-full-ha.xml
ENTRYPOINT /bin/bash ${JBOSS_HOME}/bin/standalone.sh
CMD /bin/bash

RUN wget ${EAP_URL} -O ./${EAP_ZIP_ARCHIVE} \
 && unzip ./${EAP_ZIP_ARCHIVE} \
 && chmod +x ${JBOSS_HOME}/bin/*.sh \
 && ${JBOSS_HOME}/bin/add-user.sh ${JBOSS_ADMIN_USER} ${JBOSS_ADMIN_PASSWORD} --silent \
 && echo "JAVA_OPTS=\"\$JAVA_OPTS -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0\"" >> ${JBOSS_HOME}/bin/standalone.conf

# deploy app (context is /app, because of app.war. feel free use needed)
ADD target/*.war "${JBOSS_HOME}/standalone/deployments/app.war"
