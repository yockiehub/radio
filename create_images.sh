cd products-service
./mvnw install dockerfile:build

cd ../orders-service
./mvnw install dockerfile:build

cd ..
