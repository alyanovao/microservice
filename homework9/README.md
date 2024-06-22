## Сервисы реализации идемпотентности заказа
Для решения проблемы применен ключ идемпотентности в заголовке X-Request-ID, рассчитываемый значением hash содержания<br>
коллекции заказов - fingerprint https://cloud.google.com/compute/docs/reference/rest/v1/instances/setTags<br>
Перед созданием заказа вызывается метод получения fingerprint по клиенту, после чего сохраняется в переменных postman<br>
Перед выполнением следующего запроса - создания заказа в запрос подставляется заголовок X-Request-ID<br>
На стороне сервиса производится проверка заголовка с актуальным значением и при не соответствии возвращается ошибка



# Сборка образа
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
docker build -t orderservice:1.0.2 -f ./orderService/Dockerfile .
```
```shell
docker build -t deliveryservice:1.0.1 -f ./deliveryService/Dockerfile .
```
```shell
docker build -t storeservice:1.0.1 -f ./storeService/Dockerfile .
```

## Установка тега
```shell
docker tag notificationservice:1.0.1 alyanovao/notificationservice:1.0.1
```
```shell
docker tag billingservice:1.0.1 alyanovao/billingservice:1.0.1
```
```shell
docker tag orderservice:1.0.2 alyanovao/orderservice:1.0.2
```
```shell
docker tag deliveryservice:1.0.1 alyanovao/deliveryservice:1.0.1
```
```shell
docker tag storeservice:1.0.1 alyanovao/storeservice:1.0.1
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
docker push alyanovao/orderservice:1.0.2
```
```shell
docker push alyanovao/deliveryservice:1.0.1
```
```shell
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
