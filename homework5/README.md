## Сервис для тестирования навыка сборки контейнера приложения

# Сборка образа
```shell
mvn clean install; docker build -t user-api:1.0.1 -f ./docker/Dockerfile .; docker tag user-api:1.0.1 alyanovao/user-api:1.0.1; docker login docker.io; docker push alyanovao/user-api:1.0.1
 ```

```shell
mvn clean install -DskipTests
```

## Сборка образа
```shell
docker build -t user-api:1.0.3-snapshot -f ./docker/Dockerfile .
```

## Установка тега
```shell
docker tag user-api:1.0.3-snapshot alyanovao/user-api:1.0.3-snapshot
```

## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/user-api:1.0.3-snapshot
```

```shell
k apply -f ./manifest_app
```

```shell
k delete -f ./manifest_app
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
## Запуск проекта
```shell
helm install user-api -n default ./helm/user-api --atomic
```

### Запуск теста helm
```shell
helm install --dry-run user-api ./helm/user-api
```

### Мониторинг
```shell
k create namespace monitoring
```

```shell
k config set-context --current --namespace=monitoring
```

## Запуск prometeus
```shell
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
```

```shell
helm repo add stable https://charts.helm.sh/stable
```

```shell
helm repo update
```

```shell
helm install prom prometheus-community/kube-prometheus-stack -f ./manifest/prometheus.yaml --atomic
```

## Запуск ingress
```shell
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
```

```shell
helm repo update
```

```shell
helm install nginx ingress-nginx/ingress-nginx -f ./manifest/nginx-ingress.yaml --atomic
```

## Проброс до графаны
```shell
kubectl port-forward service/prom-grafana 9000:80
```

## Посмотреть пароль
```shell
kubectl get secret prom-grafana -o jsonpath='{.data}'
```

```shell
kubectl port-forward service/prom-kube-prometheus-stack-prometheus 9090
```

```shell
helm install user-api ./helm/user-api -n default --atomic
```

```shell
kubectl apply -f ./manifest/service-monitor.yaml -n default
```

```comment
rps
sum(irate(http_server_requests_seconds_count{uri=~"/user/.*"}[1m])) by (uri, method, pod)


```

### Удаление сервиса
## Удаляем helm
```shell
helm -n postgres uninstall postgres-db
```

## Удаляем helm
```shell
helm uninstall user-api -n default
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

```shell
k delete namespace monitoring
```