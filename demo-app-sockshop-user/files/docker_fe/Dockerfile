FROM registry.access.redhat.com/ubi7/ubi

WORKDIR /app
COPY app_linux_amd64 ./app_linux_amd64

# Files needed to create collections, insert data and start the service
ADD ./*.sh /tmp/scripts/
COPY ./mongodb.repo /etc/yum.repos.d/mongodb.repo

# See https://docs.openshift.com/container-platform/3.11/creating_images/guidelines.html
RUN yum clean all && \
    yum install mongodb-org-shell -y && \
    chgrp -R 0 /app && \
    chmod -R g=u /app && \
    chmod u+x ./app_linux_amd64 && \
    chmod +x /tmp/scripts/*.sh 

USER 1001

EXPOSE 8080

CMD ["sh", "-c", \
    "/tmp/scripts/entrypoint.sh"]
