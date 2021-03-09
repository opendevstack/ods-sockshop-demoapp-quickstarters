#!/bin/sh
DB_INIT_URI=$1     #uri format for mongo cli
SERVICE_PORT=$2    #-port=8080 
SERVICE_DB=$3      #-database=mongodb 
SERVICE_DB_URI=$4  #-mongo-host=demo-app-user-db:27017

echo Wait command: "mongo $DB_INIT_URI -u $MONGODB_USER -p $MONGODB_PASS --eval='quit()'"
/tmp/scripts/wait-for-cmd.sh "mongo $DB_INIT_URI -u $MONGO_USER -p $MONGO_PASS --eval='quit()'" 360

echo "MongoDB is ready let's create and insert the collections"
/tmp/scripts/mongo_create_insert.sh $DB_INIT_URI

echo "Finished startup, let's start the service"
./app_linux_amd64 $SERVICE_PORT $SERVICE_DB $SERVICE_DB_URI

