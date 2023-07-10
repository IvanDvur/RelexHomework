## Основная идея проекта
Сервис отзывов и оценки отеля

## Архитектура
- Сервис "USER-SERVICE" определяет функциональность при работе с клиентами(их добавление, получение из бд и т.д)
- Сервис "HOTEL-SERVICE" определяет функциональность при работе с отелями(добавление,получение из бд и т.д)
- Сервис "RATING-SERVICE" определяет функциональность при работе с рейтингами(добавление рейтинга определённому отелю, выгрузка среднего рейтинга всех отелей в .csv файле и т.д)
- API-GATEWAY - маршрутизирует запросы на необходимый микросервис, позволяет обращаться к разным сервисам по одному порту
- SERVICE-REGISTRY - реестр микросервисов Eureka

## Запуск 
В корневой директории проекта выполнить команду docker compose up

# Примеры запросов
## Добавление пользователя
curl --location 'http://localhost:8083/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Иван",
"email": "ivan565@list.ru",
"lastName": "Dvurechenskiy",
"username": "ivand"
}'
## Ответ
{
"userId": "a6c7764c-15bb-4465-8bfc-6cf223b0a1a4",
"firstName": "Иван",
"email": "ivan565@list.ru",
"lastName": "Dvurechenskiy",
"username": "ivand",
"userRatings": []
}
## Добавление пользователя с некорректный форматом данных (например невалидный Email)
curl --location 'http://localhost:8083/users' \
--header 'Content-Type: application/json' \
--data '{
"firstName": "Иван",
"email": "ivan565list.ru",
"lastName": "Dvurechenskiy",
"username": "ivand"
}'
## Ответ
{
"message": "Данные имеют некорректный формат",
"status": "BAD_REQUEST"
}
## Поиск пользователя по id
curl --location --request GET 'http://localhost:8083/users/a6c7764c-15bb-4465-8bfc-6cf223b0a1a4' \
--header 'Content-Type: application/json' \
--data '{
"firstName": "Иван",
"email": "ivan565list.ru",
"lastName": "Dvurechenskiy",
"username": "ivand"
}'
## Ответ
{
"userId": "a6c7764c-15bb-4465-8bfc-6cf223b0a1a4",
"firstName": "Иван",
"email": "ivan565@list.ru",
"lastName": "Dvurechenskiy",
"username": "ivand",
"userRatings": []
}

## Поиск пользователя по некорректному id
curl --location --request GET 'http://localhost:8083/users/a6c7764c-15bb-4465-8bfc-6cf223b0a1a3' \
--header 'Content-Type: application/json' \
--data '{
"firstName": "Иван",
"email": "ivan565list.ru",
"lastName": "Dvurechenskiy",
"username": "ivand"
}'
## Ответ
{
"message": "Пользователь c id a6c7764c-15bb-4465-8bfc-6cf223b0a1a3 не найден",
"status": "NOT_FOUND"
}
## Поиск всех пользователей при отключенном rating-service или hotel-service
curl --location 'http://localhost:8083/users'
## Ответ
{
"userId": "264eaea2-b2d6-4476-8ada-90a98913ad45",
"firstName": null,
"email": null,
"lastName": null,
"username": "Что-то пошло не так",
"userRatings": null
}
## Добавление отеля
curl --location 'http://localhost:8083/hotels' \
--header 'Content-Type: application/json' \
--data '{
"name":"BadHotel",
"location": "Voronezh",
"description":"worst hotel in the world"
}'
## Добавление рейтинга отелю
curl --location 'http://localhost:8083/ratings' \
--header 'Content-Type: application/json' \
--data '{
"userId": "11f5b4a2-e393-4422-817e-725afa2238f7",
"hotelId":"24a816f4-8c87-4eee-8db6-9e5330ec6f12",
"rating":"3",
"feedback":"disgusting hotel"
}'
## Просмотр средней оценки по всем отелям
curl --location 'http://localhost:8083/ratings/getAverage'
## Ответ
Hotel Name,Address,Average Rating\
SuperHotel,Voronezh,5.333333333333333\
BadHotel,Voronezh,2.0

