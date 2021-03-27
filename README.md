## **Процедура запуска авто-тестов**

##### Для запуска автотестов необходимо:

1. склонировать репозиторий командой `git clone`;
2. с официального сайта https://hub.docker.com/ скачать образы MySql, PostgreSQL и Node.js с помощью команды `docker pull <имя_образа>` (необходим установленный Docker);
3. запустить контейнер с MySql, PostgreSQL и Node.js с помощью команды `docker-compose up -d --build`;
4. запустить приложение:  
* для использования БД MySQL в файле **build.gradle** в строке `systemProperty 'db.url', System.getProperty` прописать `url=jdbc:mysql://localhost:3306/app` и запустить команду `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar`;
* для использования БД PostgreSQL в файле **build.gradle** в строке `systemProperty 'db.url', System.getProperty` прописать `"url=jdbc:postgresql://localhost:5432/app"` и запустить команду `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`;
5. запустить автотесты:
* при использовании БД MySQL запустить команду `gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test`;
* при использовании БД PostgreSQL запустить команду `gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean test`;
6. после завершения автотестов необходимо остановить контейнеры с помощью команды `docker-compose down`.