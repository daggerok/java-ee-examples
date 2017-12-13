#FROM tomee:8-jre-7.0.3-plus
FROM tomee:8-jre-7.0.3-webprofile
MAINTAINER Maksim Kostromin https://github.com/daggerok/docker

CMD ["/bin/bash"]
ENTRYPOINT ["catalina.sh", "run"]

# check all apps healthy
HEALTHCHECK --interval=1s --timeout=3s --retries=30 \
 CMD curl -f http://127.0.0.1:8080/rest-api/health \
  && curl -f http://127.0.0.1:8080/ui/ \
  || exit 1

# deploy apps
COPY ./rest-api/target/*.war ./ui/target/*.war /usr/local/tomee/webapps/
