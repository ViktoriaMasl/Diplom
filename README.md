# **Дипломный проект профессии «Тестировщик»**  
В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

Приложение предлагает купить тур с помощью двух способов:
* Обычная оплата по дебетовой карте
* Выдача кредита по данным банковской карты  

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

* сервису платежей (Payment Gate)
* кредитному сервису (Credit Gate)  

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был обработан (при этом данные карт сохранять не допускается).  

[Ссылка на дипломное задание](https://github.com/netology-code/qa-diploma)  

## **Тестовая документация**  
[План автоматизации](https://github.com/ViktoriaMasl/Diplom/blob/master/documents/Plan.md)  
[Отчёт о проведённом тестировании](https://github.com/ViktoriaMasl/Diplom/blob/master/documents/Report.md)  
[Отчёт о проведённой автоматизации](https://github.com/ViktoriaMasl/Diplom/blob/master/documents/Summary.md)

## **Процедура запуска авто-тестов**

##### Для запуска автотестов необходимо:

1. склонировать репозиторий командой `git clone`;
2. с официального сайта https://hub.docker.com/ скачать образы MySql, PostgreSQL и Node.js с помощью команды `docker pull <имя_образа>` (необходим установленный Docker);
3. запустить контейнер с MySql, PostgreSQL и Node.js с помощью команды `docker-compose up -d --build`;
4. запустить приложение:  
* для использования БД MySQL запустить команду `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`;
* для использования БД PostgreSQL запустить команду `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`;
5. запустить автотесты:
* при использовании БД MySQL запустить команду `gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean tests`;
* при использовании БД PostgreSQL запустить команду `gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean tests`;
6. Для формирования отчета в трминале ввести команду `gradlew allureServe`. Отчет автоматически откроется в браузере.
7. после завершения автотестов необходимо остановить контейнеры с помощью команды `docker-compose down`.