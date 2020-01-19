# Auktionera

## Instructions

First setup a db with username and password

Run the AuktioneraApplication

To see all endpoints please visit http://localhost:8080/swagger-ui.html#/

First you have to register a new user, then authenticate that user.
Afterwards please copy the token that you received copy it in insomina under Bearer and check the box enabled,
then you should be good to go.

Start Rabbitmq
RabbitMQ is enabled from endpoint GET api/account Auktionera is the sender

Start receiver app and then it should received a message that contains username and id and saves it to DB


