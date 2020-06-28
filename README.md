# radio
Demo projecto for Kubbo interview

Radio is a demo app for a system to manage a storage and create orders of products.

Its aim is to keep track of the stock available in a storage and the orders that are placed, keeping the data synchronized.

The user of this app will be able to:

- Add products that are coming to storage, may them be individual products (each one directly related to a phisycal product in storage) or composed products (artificial constructs consisting on the amalgamation of one or more individual products, e.g. "Shaving kit", composed by "Razor" x3, "Foam" x1 and "Aftershave" x1).
- Place orders of products (as many different products and amounts as wanted/available in stock), being able to edit the current status of the order (e.g. "Ordered", "In process", "In delivery",...) and to visualize its details at any time.
- Edit the products that are in storage.
- Always know how many composed products can be composed with the available individual products on storage. Hence, the user will also know the availability of the stock.

The back-end of the app consists of 2 microservices in Java using the framework Spring Boot, each one dealing with Product and Order.

The front-end will be another service in Angular 2+.

## Instructions to run the app

- Clone project
- Run script 'build_and_run.sh'

Once the images have been created and are stored locally in docker, it will be enough to run the script 'start_microservices.sh'

After this, access the application through 'http://localhost:8100', which will redirect to 'http://localhost:8100/home/tabs/product' as main page.
