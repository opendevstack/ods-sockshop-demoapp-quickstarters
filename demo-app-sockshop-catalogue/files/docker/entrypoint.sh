#!/bin/sh
DB_SERVICE=$1
PORT=$2

echo "Received parameters $0"
echo "Wait command:  MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $DB_SERVICE -u $MYSQL_USER -D $MYSQL_DATABASE -e 'SELECT 1'"
/tmp/scripts/wait-for-cmd.sh "MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $DB_SERVICE -u $MYSQL_USER -D $MYSQL_DATABASE -e 'SELECT 1'" 360

echo "MySQL is ready let's create and insert the collections"
/tmp/scripts/mysql_dump.sh $DB_SERVICE

echo "Finished startup, let's start the service"
/app/app_linux_amd64 $PORT
