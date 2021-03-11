#!/bin/sh
DB_TEST_URI=${MONGO_BASE_URI}/${MONGO_TEST_DB} # uri to test connection to mongodb
PORT=-port=${SERVICE_PORT:-8080}
DB_SERVICE=${MYSQL_HOST:-localhost}
DB_PORT=${MYSQL_PORT:-3306}

echo "Received parameters $0"
echo "Wait command:  MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $DB_SERVICE -P $DB_PORT -u $MYSQL_USER -D $MYSQL_DATABASE -e 'SELECT 1'"
/tmp/scripts/wait-for-cmd.sh "MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $DB_SERVICE -P $DB_PORT -u $MYSQL_USER -D $MYSQL_DATABASE -e 'SELECT 1'" 360

#echo "MySQL is ready let's create and insert the collections"
#/tmp/scripts/mysql_dump.sh $DB_SERVICE

echo "Finished startup, let's start the service"
/app/app_linux_amd64 $PORT
