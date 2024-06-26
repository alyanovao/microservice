Сирвисы треккинга в микросервисной архитектуре
В данной работе планирую реализовать подход CQRS и Event Sourcing
Состав сервисов:
eventService - принимает запрос по http и регисиритует событие в kafka
consumerService - читает сообщение из очереди и регистрирует в бд MondoDB
visualizationService - читает данные из хранилища и отражает на карте

Для данного проекта используется функционал яндекс карт

Использую kafka без zookeeper
```shell
docker-compose -p broker -f docker/docker-compose.yml up -d
```
