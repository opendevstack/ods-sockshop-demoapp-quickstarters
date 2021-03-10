FROM registry.access.redhat.com/rhel7

WORKDIR /app
COPY app_linux_amd64 ./app_linux_amd64

# Files needed to create collections, insert data and start the service
ADD ./user-db/scripts /tmp/scripts
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

CMD ["/tmp/scripts/entrypoint.sh", \
    "demo-app-user-db:27017/users", \
    "-port=8080", \
    "-database=mongodb", \
    "-mongo-host=demo-app-user-db:27017"]