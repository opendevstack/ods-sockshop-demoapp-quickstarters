#!/usr/bin/env bash

SCRIPT_DIR=$(dirname "${BASH_SOURCE[0]}")
MONGODB_SERVICE=users

FILES=$SCRIPT_DIR/*-create.js
for f in $FILES; do mongo $MONGODB_SERVICE -u $MONGODB_USER -p $MONGODB_PASSWORD $f; done

FILES=$SCRIPT_DIR/*-insert.js
for f in $FILES; do mongo $MONGODB_SERVICE -u $MONGODB_USER -p $MONGODB_PASSWORD $f; done
