## Сервис для тестирования навыка сборки контейнера приложения

## Сборка проекта
```shell
./mvnw clean install
 ```

## Сборка образа
```shell
docker build -t customservice:1.0.1 .
```

## Установка тега
```shell
docker tag customservice:1.0.1 alyanovao/customservice:1.0.1
```

## Подключение к удаленному репозиторию
```shell
docker login docker.io
```

## деплой образа
```shell
docker push alyanovao/customservice:1.0.1
```

## Запуск контейнера
```shell
docker run --rm --name service -it -d -p 8000:8000 -p 8001:8001 -e JAVA_OPTS='Xms128m -Xmx128m' alyanovao/customservice:1.0.1
```

## Запуск compose
```shell
docker-compose -p service -f docker/docker-compose.yml up -d
```

### Установка ingress контроллера
```shell
minikube addons enable ingress
```

### для запуска minikube driver=hyperv прописать driver/etc/hosts dns, например ru.aao
``` shell
minikube ip
например 127.1.1.1
127.1.1.1 ru.aao
```

### Запуск deployment
```shell
kubectl apply -f manifest/deployment.yml
```

### Создание service
```shell
kubectl apply -f manifest/service.yml
```

### Создание ingress
```shell
kubectl apply -f manifest/ingress.yml
```

### запуск deployment, service и ingress 
```shell
kubectl apply -f manifest/
```

### удаление deployment, service и ingress
```shell
kubectl delete -f manifest/
```

### добавление хоста в host контейнера:
```shell
echo "127.0.0.1 arch.homework" | sudo tee -a /etc/hosts
```

Бизнес логика - сервер netty на порту 8001, метрики - сервер tomcat на порту 8000
Время первоначальной инициализации 15 секунд 

http клиент с примерами: rest-api.http