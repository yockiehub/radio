#Start Orders microservice
docker run -d -p 8081:8081 -t radio/orders-service

#Start Products microservice
docker run -d -p 8080:8080 -t radio/products-service

#Start Radio UI service
docker run -d -p 8100:8100 -t radio-ui-image
