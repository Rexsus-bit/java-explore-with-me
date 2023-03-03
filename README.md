# _Java-explore-with-me_

Бэкенд для приложения, в котором можно делиться информацией об интересных событиях и находить компанию для участия в них. Приложение построено на микросервисной архитектуре.

### _Микросервисы_
1. Основной сервис содержит базовый функционал приложения. 
API сервиса разделено на три части:
   - публичная - доступна без регистрации любому пользователю сети
   - пользовательская - доступна авторизованным пользователям
   - административная - доступна администраторам сервиса
   
    См.подробнее [спецификацию основного сервиса (swagger)](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/rexsus-bit/java-explore-with-me/main/ewm-main-service-spec.json).

2. Сервис статистики ведет учет количества просмотров событий, а также позволяет делать выборки по статистике просмотров. См. подробнее [спецификацию сервиса статистики (swagger)](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/rexsus-bit/java-explore-with-me/main/ewm-stats-service-spec.json).

### _Стек технологий_
REST API service with Spring-Boot, JPA Hibernate, PostgreSQL, Java 11, Lombok, Docker

### _Запуск приложения_
Приложение запускается командой docker-compose up из корневой директории проекта

### _Эскиз визуального оформления_
![main front](./Front%20page%20concept.png)
