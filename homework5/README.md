## Сервис для тестирования навыка сборки контейнера приложения

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
## Запуск проекта
```shell
helm install user-api ./helm/user-api --atomic
```

### Запуск теста helm
```shell
helm install --dry-run user-api ./helm/user-api
```

```shell
minikube addons enable ingress
```

```shell
kubectl create namespace m && helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && helm repo update && helm install nginx ingress-nginx/ingress-nginx --namespace m -f ./manifest/nginx-ingress.yaml
```

### Удаление сервиса
## Удаляем helm
```shell
helm -n postgres uninstall postgres-db
```

## Удаляем helm
```shell
helm uninstall user-api
```

```shell
k delete pvc --namespace postgres data-postgres-db-postgresql-0
```

## Удаляем секрет
```shell
k delete secret secret-db
```

```shell
k delete namespace postgres
```