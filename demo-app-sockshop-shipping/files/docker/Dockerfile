FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app
COPY app.jar ./app.jar

# See https://docs.openshift.com/container-platform/3.11/creating_images/guidelines.html
RUN chgrp -R 0 /app && \
    chmod -R g=u /app

ENV JAVA_OPTS=-Dorg.slf4j.simpleLogger.defaultLogLevel=warn

USER 1001

EXPOSE 8080

CMD ["java","-Xmx512m", "-jar", "./app.jar", "--port=8080"]
