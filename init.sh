#!/bin/bash

PIDS=$(ps aux | grep 'java' | awk '{print $1}')

if [ -n "$PIDS" ]; then
  for PID in $PIDS; do
    echo "Deteniendo el proceso con PID: $PID"
    kill $PID
  done
else
  echo "No se encontraron procesos java -jar en ejecución."
fi


BASE_DIR="$(pwd)"

LOG_DIR="$BASE_DIR/logs"

mkdir -p "$LOG_DIR"


### CONFIG SERVER
cd "$BASE_DIR/config-server/config-server"
mvn clean install package
java -jar "$BASE_DIR/config-server/config-server/target/config-server-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/config-server.log" 2>&1 &

### TRANSACTIONS
cd "$BASE_DIR/transactions/transactions"
mvn clean install package
java -jar "$BASE_DIR/transactions/transactions/target/transactions-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/transactions.log" 2>&1 &


### PRODUCTS
cd "$BASE_DIR/product/product"
mvn clean install package
java -jar "$BASE_DIR/product/product/target/product-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/product.log" 2>&1 &

### CUSTOMER
cd "$BASE_DIR/customer/customer"
mvn clean install package
java -jar "$BASE_DIR/customer/customer/target/customer-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/customer.log" 2>&1 &
