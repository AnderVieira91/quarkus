#!/bin/bash
set -e

THIS_DIR=$(dirname $(readlink -f $0))
DEFAULT_FILE=$THIS_DIR/dev.env

docker run -d \
    --name quarkus-server \
    --env-file $THIS_DIR/docker-env \
    -p 38080:8080 \
    -p 38000:8000 \
    touch/projeto-quarkus:latest


function out {
    docker rm -f quarkus-server
    exit
}

docker logs -f quarkus-server
