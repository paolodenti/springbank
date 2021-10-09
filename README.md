# CQRS & Event Sourcing Sample with Axon

## setup external environment

### OAuth2 test

OAuth2 auth posting `http://localhost:8084/oauth/token`.

Set Authorization header to `Basic c3ByaW5nYmFua0NsaWVudDpzcHJpbmdiYW5rU2VjcmV0`

Set x-www-form-urlencoded with keys:

* `grant_type`: `password`
* `username`: <your username>
* `password`: <your password>

```bash
docker network create --attachable -d bridge springbankNet
docker run -d --name axon-server -p 8024:8024 -p 8124:8124 --network springbankNet --restart always axoniq/axonserver:latest
docker run -it -d --name mongo-container -p 27017:27017 --network springbankNet --restart always -v mongo_data_container:/data/db mongo:latest
docker run -it -d --name mysql-container -p 3306:3306 --network springbankNet -e MYSQL_ROOT_PASSWORD=root --restart always -v mysql_data_container:/var/lib/mysql mysql:latest
```