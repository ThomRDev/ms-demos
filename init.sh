#!/bin/bash

./stop.sh

BASE_DIR="$(pwd)"

LOG_DIR="$BASE_DIR/logs"

mkdir -p "$LOG_DIR"


### EUREKA
cd "$BASE_DIR/eureka-server/eureka-server"
mvn clean install package
java -jar "$BASE_DIR/eureka-server/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/eureka-server.log" 2>&1 &

# ### observability
# cd "$BASE_DIR/observability/observability"
# mvn clean install package
# java -jar "$BASE_DIR/observability/observability/target/observability-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/observability.log" 2>&1 &


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

# ### CUSTOMER
cd "$BASE_DIR/customer/customer"
mvn clean install package
java -jar "$BASE_DIR/customer/customer/target/customer-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/customer.log" 2>&1 &



# ### GATEWAY
cd "$BASE_DIR/api-gateway/api-gateway"
mvn clean install package
java -jar "$BASE_DIR/api-gateway/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar" >> "$LOG_DIR/api-gateway.log" 2>&1 &
