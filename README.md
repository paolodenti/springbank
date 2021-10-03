# CQRS & Event Sourcing Sample

## setup external environment

```bash
docker network create --attachable -d bridge springbankNet
docker run -d --name axon-server -p 8024:8024 -p 8124:8124 --network springbankNet --restart always axoniq/axonserver:latest
docker run -it -d --name mongo-container -p 27017:27017 --network springbankNet --restart always -v mongo_data_container:/data/db mongo:latest
docker run -it -d --name mysql-container -p 3306:3306 --network springbankNet -e MYSQL_ROOT_PASSWORD=root --restart always -v mysql_data_container:/var/lib/mysql mysql:latest
```