## Сервис для тестирования навыка сборки контейнера приложения

# Сборка и деплой сервиса
## Сборка проекта
```shell
mvn clean install
 ```

## Сборка образа
```shell
docker build -t user-api:1.0.0 .
```

## Установка тега
```shell
docker tag user-api:1.0.0 alyanovao/user-api:1.0.0
```

## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/user-api:1.0.0
```

## Запуск контейнера
```shell
docker run --name service -it -d -p 8000:8000 -e JAVA_OPTS='Xms128m -Xmx128m' user-api:1.0.0
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

## Создаем секрет
```shell
k apply -f ./db/secret.yml
```

## Создание базы данных
```shell
helm install postgres-db oci://registry-1.docker.io/bitnamicharts/postgresql --namespace postgres --create-namespace --set global.postgresql.auth.postgresPassword="postgres"
```

## Проверяем базу
## Запускаем в git bash
## Добавляем переменную
```shell
export POSTGRES_PASSWORD=$(kubectl get secret --namespace secret-db -o jsonpath="{.data.postgres-password}" | base64 -d)
```

## Пробрасываем до базы
```shell
kubectl port-forward --namespace postgres svc/postgres-db-postgresql 5432:5432 &
    PGPASSWORD="$POSTGRES_PASSWORD" psql --host 127.0.0.1 -U postgres -d postgres -p 5432
```

## Запуск проекта
```shell
kubectl apply -f ./manifest
```

## Добавляем ingres
minikube addons enable ingress

## Делаем проброс
minikube tunnel

### Удаление сервиса
## Удаляем helm
```shell
helm -n postgres uninstall postgres-db
```

```shell
k delete pvc --namespace postgres data-postgres-db-postgresql-0
```

```shell
k delete pv pvc-1755c27b-0ab0-427c-ad8c-306a83e08f74
```

## Удаляем секрет
```shell
k delete secret secret-db
```

## Удалить сервис
```shell
k delete -f ./manifest
```
```shell
k delete namespace postgres
```
