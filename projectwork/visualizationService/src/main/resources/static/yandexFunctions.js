ymaps.ready(init)
var myMap;
function init() {
    // Создание карты.
    // https://tech.yandex.ru/maps/doc/jsapi/2.1/dg/concepts/map-docpage/
    myMap = new ymaps.Map("map", {
        // Координаты центра карты.
        // Порядок по умолчнию: «широта, долгота».
        center: [55.76, 37.64],
        // Уровень масштабирования. Допустимые значения:
        // от 0 (весь мир) до 19.
        zoom: 12,
        // Элементы управления
        // https://tech.yandex.ru/maps/doc/jsapi/2.1/dg/concepts/controls/standard-docpage/
        controls: [

            'zoomControl', // Ползунок масштаба
            'rulerControl', // Линейка
            'routeButtonControl', // Панель маршрутизации
            'trafficControl', // Пробки
            'typeSelector', // Переключатель слоев карты
            'fullscreenControl', // Полноэкранный режим

            // Поисковая строка
            new ymaps.control.SearchControl({
                options: {
                    // вид - поисковая строка
                    size: 'large',
                    // Включим возможность искать не только топонимы, но и организации.
                    provider: 'yandex#search'
                }

            })
        ]
    });
}

function getLocationByDeviceId(id) {
    $.ajax({
        url: "/visualization/getLocationByDeviceId?deviceId=" + id,
        type: "GET",
        success: function (response) {
            refreshByLocations(response.deviceName, response.latitude, response.longitude)
        }
    })
}

function getLocations(id) {
    $.ajax({
        url: "/visualization/getLocations?deviceId=" + id,
        type: "GET",
        success: function (response) {
            refresh(response)
        }
    })
}

function refreshByLocations(deviceName, latitude, longitude) {
    myMap.geoObjects.removeAll();
    // Добавление метки
    // https://tech.yandex.ru/maps/doc/jsapi/2.1/ref/reference/Placemark-docpage/
    var myPlacemark = new ymaps.Placemark([latitude, longitude], {
    // Хинт показывается при наведении мышкой на иконку метки.
    hintContent: deviceName,
    iconContent: deviceName
    }, {
        'preset': 'islands#yellowStretchyIcon'
    });

    // После того как метка была создана, добавляем её на карту.
    myMap.geoObjects.add(myPlacemark);

    myMap.setBounds(myMap.geoObjects.getBounds(), {checkZoomRange:true}).then(function(){
        if(myMap.getZoom() > 15) myMap.setZoom(15); // Если значение zoom превышает 15, то устанавливаем 15.
    });
}

function refresh(response) {
    response.locations.forEach(function(location) {
        // Добавление метки
        // https://tech.yandex.ru/maps/doc/jsapi/2.1/ref/reference/Placemark-docpage/
        var myPlacemark= new ymaps.Placemark([location.latitude, location.longitude], {
            //var myPlacemark = new ymaps.Placemark([55.76, 37.64], {
            // Хинт показывается при наведении мышкой на иконку метки.
            hintContent: response.deviceName,
            iconContent: response.deviceName
        }, {
            'preset': 'islands#yellowStretchyIcon'
        });

        // После того как метка была создана, добавляем её на карту.
        myMap.geoObjects.add(myPlacemark);
    });

    myMap.setBounds(myMap.geoObjects.getBounds(), {checkZoomRange:true}).then(function(){
        if(myMap.getZoom() > 15) myMap.setZoom(15); // Если значение zoom превышает 15, то устанавливаем 15.
    });
}

function refreshPolyline(response) {
    var routePoints = [];
    response.locations.forEach(function(location) {
        // Добавление метки
        // https://tech.yandex.ru/maps/doc/jsapi/2.1/ref/reference/Placemark-docpage/
        var myPlacemark= new ymaps.Placemark([location.latitude, location.longitude], {
            //var myPlacemark = new ymaps.Placemark([55.76, 37.64], {
            // Хинт показывается при наведении мышкой на иконку метки.
            hintContent: response.deviceName,
            iconContent: response.deviceName
        }, {
            'preset': 'islands#yellowStretchyIcon'
        });
        // После того как метка была создана, добавляем её на карту.
        myMap.geoObjects.add(myPlacemark);
        res.geoObjects.each(function (point) {
            routePoints.push(point.geometry.getCoordinates());
        });
        ymaps.route(routePoints).then(function (route) {
            map.geoObjects.add(route);
        })
    });

    myMap.setBounds(myMap.geoObjects.getBounds(), {checkZoomRange:true}).then(function(){
        if(myMap.getZoom() > 15) myMap.setZoom(15); // Если значение zoom превышает 15, то устанавливаем 15.
    });
}