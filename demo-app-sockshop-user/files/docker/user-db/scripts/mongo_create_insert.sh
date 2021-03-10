#!/usr/bin/env bash

SCRIPT_DIR=$(dirname "$0")
MONGO_SERVICE=$1
USER=$2
PWD=$3

#mongod --fork --logpath /var/log/mongodb.log --dbpath /data/db/

FILES=$SCRIPT_DIR/*-create.js
for f in $FILES; do mongo $MONGO_SERVICE -u $MONGO_USER -p $MONGO_PASS $f; done

FILES=$SCRIPT_DIR/*-insert.js
for f in $FILES; do mongo $MONGO_SERVICE -u $MONGO_USER -p $MONGO_PASS $f; done
