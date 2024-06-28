Сирвисы треккинга в микросервисной архитектуре
В данной работе планирую реализовать подход CQRS и Event Sourcing
Состав сервисов:
eventService - принимает запрос по http и регисиритует событие в kafka
consumerService - читает сообщение из очереди и регистрирует в бд MondoDB
visualizationService - читает данные из хранилища и отражает на карте

Для данного проекта используется функционал яндекс карт
Использую kafka без zookeeper
![img.png](img.png)
![img_1.png](img_1.png)

# Сборка проекта
```shell
mvn clean install -DskipTests
```

## Сборка образа
```shell
docker build -t consumerservice:1.0.0 -f ./consumerService/Dockerfile .
```

```shell
docker build -t eventservice:1.0.0 -f ./eventService/Dockerfile .
```

```shell
docker build -t visualizationservice:1.0.0 -f ./visualizationService/Dockerfile .
```

## Установка тега
```shell
docker tag consumerservice:1.0.0 alyanovao/consumerservice:1.0.0
```

```shell
docker tag eventservice:1.0.0 alyanovao/eventservice:1.0.0
```

```shell
docker tag visualizationservice:1.0.0 alyanovao/visualizationservice:1.0.0
```

## Деплой образа в dockerhub
```shell
docker push alyanovao/consumerservice:1.0.0
```

```shell
docker push alyanovao/eventservice:1.0.0
```

```shell
docker push alyanovao/visualizationservice:1.0.0
```

Запуск kafka - не нужно, вынес в manifest
```shell
docker-compose -p broker -f docker/docker-compose.yml up -d
```

Запуск Mongo - установлен Mongo server на локальной машине
https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/

## Alias
```shell
New-Alias -Name "k" kubectl
```
 
```shell
minikube addons enable ingress
```

```shell
minikube tunnel
```

```shell
k apply -f ./manifest
```

```shell
k delete -f ./manifest
```
