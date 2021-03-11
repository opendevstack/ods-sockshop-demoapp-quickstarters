#!/bin/sh
DB_SERVICE=$1
SCRIPT_DIR=$(dirname "$0")

echo "Initial dump....."
if ! ls $SCRIPT_DIR/data/dump.sql 2>/dev/null 2>&1; then
    echo "Initial dump does not exists"
else
    FILES=$SCRIPT_DIR/data/dump.sql
    for f in $FILES; do MYSQL_PWD="$MYSQL_ROOT_PASSWORD" mysql -h $DB_SERVICE -u root -D $MYSQL_DATABASE < $f; done
fi

echo "Creating extra tables....."
if ! ls $SCRIPT_DIR/data/dump-create*.sql 2>/dev/null 2>&1; then
    echo "extra tables does not exists"
else
    FILES=$SCRIPT_DIR/data/dump-create*.sql
    for f in $FILES; do MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $DB_SERVICE -u $MYSQL_USER -D $MYSQL_DATABASE < $f; done
fi

echo "Inserting extra data....."
if ! ls $SCRIPT_DIR/data/dump-insert*.sql 2>/dev/null 2>&1; then
    echo "extra data does not exists"
else
    FILES=$SCRIPT_DIR/data/dump-insert*.sql
    for f in $FILES; do MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $DB_SERVICE -u $MYSQL_USER -D $MYSQL_DATABASE < $f; done
fi
