#!/bin/bash
mkdir -p logs
# Test without env vars
cd logs
logs_dir=$PWD
cd ../
SERVER_PORT=8082

printf "Script location: $PWD\n"
cd ../../..
printf "Maven dir:       $PWD\n"

last_pid=$(netstat -anp | grep -i "$SERVER_PORT" | grep -oE "[0-9]+/" | grep -oE "[0-9]+")
echo "$last_pid"
[[ ! -z "$last_pid" ]] && kill "$last_pid"

echo ""

{ mvn spring-boot:run | tee -a "$logs_dir""/mvn-log.txt" & }

tee_process_pid=$!
# 15 seconds until UP
sleep 50

kill "$tee_process_pid"
last_pid=$(netstat -anp | grep -i "$SERVER_PORT" | grep -oE "[0-9]+/" | grep -oE "[0-9]+")
kill "$last_pid"
exit 0;
