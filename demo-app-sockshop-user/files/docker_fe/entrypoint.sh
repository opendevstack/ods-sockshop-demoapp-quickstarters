#!/bin/sh
SERVICE_PORT=-port=${SERVICE_PORT:-8080}
SERVICE_DB=-database=${SERVICE_DB:-mongodb}
MONGO_BASE_URI=${MONGO_BASE_URI:-localhost:27017}
SERVICE_DB_URI=-mongo-host=${MONGO_BASE_URI}
DB_TEST_URI=${MONGO_BASE_URI}/${MONGO_TEST_DB} # uri to test connection to mongodb

echo Wait command: "mongo $DB_TEST_URI -u $MONGO_USER -p $MONGO_PASS --eval='quit()'"
/tmp/scripts/wait-for-cmd.sh "mongo $DB_TEST_URI -u $MONGO_USER -p $MONGO_PASS --eval='quit()'" 360

echo "Finished startup, let's start the service"
./app_linux_amd64 $SERVICE_PORT $SERVICE_DB $SERVICE_DB_URI

