cd products-service
./mvnw install dockerfile:build

cd ../orders-service
./mvnw install dockerfile:build

cd ../radio-ui
docker build -t radio-ui-image .

cd ..
