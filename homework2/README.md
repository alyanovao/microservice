## Сервис для тестирования навыка сборки контейнера приложения

## Сборка проекта
```shell
./mvnw clean install
 ```

## Сборка образа
```shell
docker build -t customservice:1.0.0 .
```

## Установка тега
```shell
docker tag customservice:1.0.0 alyanovao/customservice:1.0.0
```

## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/customservice:1.0.0
```

## Запуск контейнера
```shell
docker run --rm --name service -it -d -p 8000:8000 -p 8001:8001 -e JAVA_OPTS='Xms128m -Xmx128m' alyanovao/customservice:1.0.0
```

## Запуск compose
```shell
docker-compose -p service up -d
```
Бизнес логика - сервер netty на порту 8001, метрики - сервер tomcat на порту 8000