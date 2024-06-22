## Сервисы распределенной транзакции
Из возможных вариантов реализации распределенной транзакции выбрал оркерстрацию
Плюсы: основной плюс - наглядность и быстрое погружение в проект, в одном месте возможно сразу разобрать бизнес логику
При этом так же возможно горизонтальное масштабирование
При необходимости встраивания нового участника как правило требуются доработки не одного проекта
Наглядность и прозрачность, с моей точки зрения, преобладают над гибкостью хореограции при большом количестве участников бизнес процесса

# Сборка образа
```shell
mvn clean install -DskipTests
```

## Сборка образа
```shell
docker build -t notificationservice:1.0.1 -f ./notificationService/Dockerfile .

docker build -t billingservice:1.0.1 -f ./billingService/Dockerfile .

docker build -t orderservice:1.0.1 -f ./orderService/Dockerfile .

docker build -t deliveryservice:1.0.1 -f ./deliveryService/Dockerfile .

docker build -t storeservice:1.0.1 -f ./storeService/Dockerfile .
```

## Установка тега
```shell
docker tag notificationservice:1.0.1 alyanovao/notificationservice:1.0.1

docker tag billingservice:1.0.1 alyanovao/billingservice:1.0.1

docker tag orderservice:1.0.1 alyanovao/orderservice:1.0.1

docker tag deliveryservice:1.0.1 alyanovao/deliveryservice:1.0.1

docker tag storeservice:1.0.1 alyanovao/storeservice:1.0.1
```


## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/notificationservice:1.0.1

docker push alyanovao/billingservice:1.0.1

docker push alyanovao/orderservice:1.0.1

docker push alyanovao/deliveryservice:1.0.1

docker push alyanovao/storeservice:1.0.1
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

helm -n postgres uninstall postgres-db

k delete pvc --namespace postgres data-postgres-db-postgresql-0

k delete namespace postgres
```

### Удаление сервиса
## Удаляем helm
```shell
helm -n postgres uninstall postgres-db
```

```shell
k delete pvc --namespace postgres data-postgres-db-postgresql-0
```

```shell
k delete namespace postgres
```
