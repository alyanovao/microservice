## Сервис заказа
Схемы взаимодействия сервисов заказа, биллинга и уведомления расположены в папке resources
Картинки приложены к чату сдачи ДЗ
из трех возможных вариантов выбрано синхронное взаимодействие
основания для выбора:
наиболее простой в реализации
прогнозируемый с точки зрения сроков для проектменеджмента
в условиях реализации быстрого проекта для проверки бизнес гипотиз, дедлайна и ограниченного времени реализую проект в виде mvp, после чего возможно выполнить оценку и проектироние более производительного сервиса с учетом замечаний и выявленных недостатков


# Сборка образа
```shell
mvn clean install -DskipTests;docker build -t notificationservice:1.0.1 -f ./notificationService/Dockerfile .;docker build -t billingservice:1.0.1 -f ./billingService/Dockerfile .;docker build -t orderservice:1.0.1 -f ./orderService/Dockerfile .;docker tag notificationservice:1.0.1 alyanovao/notificationservice:1.0.1;docker tag billingservice:1.0.1 alyanovao/billingservice:1.0.1;docker tag orderservice:1.0.1 alyanovao/orderservice:1.0.1;docker push alyanovao/notificationservice:1.0.1;docker push alyanovao/billingservice:1.0.1;docker push alyanovao/orderservice:1.0.1
```

```shell
mvn clean install -DskipTests
```

## Сборка образа
```shell
docker build -t notificationservice:1.0.1 -f ./notificationService/Dockerfile .
```

```shell
docker build -t billingservice:1.0.1 -f ./billingService/Dockerfile .
```

```shell
docker build -t orderservice:1.0.1 -f ./orderService/Dockerfile .
```

## Установка тега
```shell
docker tag notificationservice:1.0.1 alyanovao/notificationservice:1.0.1
```

```shell
docker tag billingservice:1.0.1 alyanovao/billingservice:1.0.1
```

```shell
docker tag orderservice:1.0.1 alyanovao/orderservice:1.0.1
```

## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/notificationservice:1.0.1
```

```shell
docker push alyanovao/billingservice:1.0.1
```

```shell
docker push alyanovao/orderservice:1.0.1
```

# Установка Приложения
## Установка чарта базы данных
## Создание alias
```shell
New-Alias -Name "k" kubectl
```

## Создание пространства
```shell
k create namespace postgres
```

## Создание базы данных
```shell
helm install postgres-db oci://registry-1.docker.io/bitnamicharts/postgresql --namespace postgres --create-namespace --set global.postgresql.auth.postgresPassword="postgres"
```

```shell
minikube addons enable ingress
```

```shell
k apply -f ./manifest
```

```shell
k delete -f ./manifest
```

### Удаление сервиса
## Удаляем helm
```shell
helm -n postgres uninstall postgres-db
```

```shell
k delete pvc --namespace postgres data-postgres-db-postgresql-0
```

k get all
```shell
k delete namespace postgres
```
