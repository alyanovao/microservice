package ru.aao.eventservice.common;

public class Constant {
    public static final String NAMEPROJECT = "EventService";						        // название проекта

    public static final String LOG_ID = NAMEPROJECT + "LogID"; 					            // Глобальный универсальный идентификатор(GUID)
    public static final String CORRELATION_ID = NAMEPROJECT + "LogCorrelationId";	        // Локальный/частный универсальный идентификатор(GUID)

    public static final String LOG_COMPONENT = NAMEPROJECT + "LogComponent";		        // Название компонента/модуля
    public static final String LOG_DIRECTION = NAMEPROJECT + "LogDirection";		        // Направление логирования(In/Out/Inner)
    public static final String LOG_STEP = NAMEPROJECT + "LogStep";					        // Название этапа
    public static final String LOG_OPERATION = NAMEPROJECT + "LogOperation";		        // Название операции, выполняемая в компоненте/модуле
    public static final String LOG_REDELIVERY = NAMEPROJECT + "Redelivery";			        // информация для повторных запросов

    public static final String OPERATION_NAME = NAMEPROJECT + "OperationName"; 		        // Название метода/операции сервиса
    public static final String REST_REQUEST = NAMEPROJECT + "RestRequestBody"; 		        // Объект класса для request запроса веб-сервиса
    public static final String REST_RESPONSE = NAMEPROJECT + "RestResponseBody"; 	        // Объект класса для response запроса веб-сервиса
    public static final String ORIGINAL_BODY = "OriginalBody";
}
