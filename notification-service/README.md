# Notification service

## Prerequisites

### Mongodb
Install Mongodb from Docker Hub

`docker pull bitnami/mongodb:7.0.11`

Start Mongodb server at port 27017 with root username and password: root/root

`docker run -d --name mongodb -p 27017:27017 -e MONGODB_ROOT_USER=root -e MONGODB_ROOT_PASSWORD=1163ruabin bitnami/mongodb:latest`
