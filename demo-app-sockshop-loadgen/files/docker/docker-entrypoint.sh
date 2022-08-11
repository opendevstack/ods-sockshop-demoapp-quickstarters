#!/bin/bash
set -e
while :; do echo "Executing tests"; casperjs "$@"; sleep 30; done