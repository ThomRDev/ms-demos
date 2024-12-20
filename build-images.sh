#!/bin/bash

BASE_DIR="$(pwd)"

function clean_image() {
  local image_name="$1"
  if docker images | grep -q "$image_name"; then
    echo "Eliminando imagen existente: $image_name"
    docker rmi -f "$image_name"
  else
    echo "No se encontró la imagen $image_name, omitiendo..."
  fi
}

clean_image "thomrdev/eureka-server"
clean_image "thomrdev/config-server"
clean_image "thomrdev/ms-transactions"
clean_image "thomrdev/ms-product"
clean_image "thomrdev/ms-customer"
clean_image "thomrdev/ms-keycloack-adapter"
clean_image "thomrdev/ms-api-gateway"


cd "$BASE_DIR/eureka-server/eureka-server"
./mvnw clean install package -DskipTests
docker build -t thomrdev/eureka-server .

# cd "$BASE_DIR/observability/observability"

# docker build -t thomrdev/observability .

cd "$BASE_DIR/config-server/config-server"
./mvnw clean install package -DskipTests
docker build -t thomrdev/config-server .

cd "$BASE_DIR/transactions/transactions"
./mvnw clean install package -DskipTests
docker build -t thomrdev/ms-transactions .


cd "$BASE_DIR/product/product"
./mvnw clean install package -DskipTests
docker build -t thomrdev/ms-product .


cd "$BASE_DIR/customer/customer"
./mvnw clean install package -DskipTests
docker build -t thomrdev/ms-customer .


cd "$BASE_DIR/keycloackAdapter/keycloackAdapter"
./mvnw clean install package -DskipTests
docker build -t thomrdev/ms-keycloack-adapter .


cd "$BASE_DIR/api-gateway/api-gateway"
./mvnw clean install package -DskipTests
docker build -t thomrdev/ms-api-gateway .
