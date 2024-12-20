#!/bin/bash

function start() {
    ./build-images.sh
    echo "Starting all docker containers...."
}

function stop() {
    echo "Stopping all docker containers...."
}

function restart() {
    stop
    sleep 3
    start
}

action="start"

if [[ "$#" != "0"  ]]
then
    action=$@
fi

eval ${action}