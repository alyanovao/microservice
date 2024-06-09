## Сервис для тестирования навыка сборки контейнера приложения

# Сборка образа

```shell
mvn clean install -DskipTests
```

```shell
```

## Сборка образа
```shell
docker build -t userservice:1.0.1 -f ./userService/Dockerfile .
```

```shell
docker build -t userauth:1.0.1 -f ./userAuthService/Dockerfile .
```

## Установка тега
```shell
docker tag userservice:1.0.1 alyanovao/userservice:1.0.1
```

```shell
docker tag userauth:1.0.1 alyanovao/userauth:1.0.1
```

## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/userservice:1.0.1
```

```shell
docker push alyanovao/userauth:1.0.1
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
k port-forward userauth-7898bdcdf4-47bbk 8888:8088
```
```shell
k logs userservice-697d98c6f6-8qttm
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

```shell
k delete namespace postgres
```
