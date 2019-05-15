#!/usr/bin/env bash

export ATSD_LOGIN=axibase
export ATSD_PASSWORD=axibase
export ATSD_CONTAINER_NAME=atsd_api_test
export ATSD_HOST=127.0.0.1
export atsd_http_port=50088
export atsd_tcp_port=50081


# Check url http code with passed credentials
function url_http_status {
    url=$1
    login=$2
    password=$3
    echo $(curl -u ${login}:${password} \
    -w "%{http_code}" \
    -s \
    -o /dev/null \
    ${url})
}


function start_container {
    atsd_login=$1
    atsd_password=$2
    atsd_http_port=$3
    atsd_host=localhost
    check_url=http://${atsd_host}:${atsd_http_port}/version
    name=atsd-api-tests
    default_timezone="Asia/Kathmandu"
    docker run -d --name=${name} \
           -p ${atsd_http_port}:8088 \
           -p ${atsd_tcp_port}:8081 \
           -e axiname=${atsd_login} \
           -e axipass=${atsd_password} \
           -e timezone=${default_timezone} \
           -e DEPLOYMENT_TYPE=java-client-test \
           axibase/atsd:api_test
    # Wait while container is not starting up
    printf "Waiting to start ${name} container ...";
    while [[ $(url_http_status ${check_url} ${ATSD_LOGIN} ${ATSD_PASSWORD}) != 200 ]]; do
        printf "."
        sleep 3;
    done
    echo "Container is started"
}


function run_atsd_container {
    start_container ${ATSD_LOGIN} ${ATSD_PASSWORD} ${atsd_http_port}
}

function show_atsd_version {
    curl --user ${ATSD_LOGIN}:${ATSD_PASSWORD} ${check_url}
}

function run_test {
    mvn clean test \
    -Dmaven.test.failure.ignore=false \
    -Daxibase.tsd.api.server.name=${ATSD_HOST} \
    -Daxibase.tsd.api.server.port=${atsd_http_port} \
    -Daxibase.tsd.api.server.tcp.port=${atsd_tcp_port} \
    -Daxibase.tsd.api.username=${ATSD_LOGIN} \
    -Daxibase.tsd.api.password=${ATSD_PASSWORD};
}

function coverage_report {
    mvn jacoco:report
    bash <(curl -s https://codecov.io/bash)
}
