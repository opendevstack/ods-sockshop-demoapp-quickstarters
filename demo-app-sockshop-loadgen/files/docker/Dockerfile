FROM vimagick/casperjs:latest

ADD docker-entrypoint.sh /config/docker-entrypoint.sh
ADD casperjs.js /config/casperjs.js

# See https://docs.openshift.com/container-platform/3.11/creating_images/guidelines.html
RUN chgrp -R 0 /config && \
    chmod -R g=u /config && \
    chmod +x /config/docker-entrypoint.sh

USER 1001

ENTRYPOINT ["/config/docker-entrypoint.sh"]
CMD ["/config/casperjs.js"]