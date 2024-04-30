## Сервис для тестирования навыка сборки контейнера приложения

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

## Запуск compose базы
```shell
docker-compose -p postgress up -d
```

## Запуск проекта
```shell
kubectl apply -d .
```