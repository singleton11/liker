Likes service
=============

Purpose of this service is to provide ability to like different users by id

How to run
----------

There is `docker-compose.yml` to speed up your development. Just run `docker-compose up mongo` to 
run mongodb server in docker

To run tests just run `mvn test` (Ensure your mongo DB listens 27017 port).

To run this application you have to run spring app with argument `--mongo.uri=<mongo_uri>` or you 
can create a spring profile by creating `application-<profile_name>.yml` and just set up spring 
profile according to documentation.