FROM node:10-alpine

ENV NODE_ENV "production"
ENV PORT 8080

WORKDIR /app
COPY dist ./

# See https://docs.openshift.com/container-platform/3.11/creating_images/guidelines.html
RUN chgrp -R 0 /app && \
    chmod -R g=u /app

USER 1001

EXPOSE 8080

CMD ["npm", "start"]
