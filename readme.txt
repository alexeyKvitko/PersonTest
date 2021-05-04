Чтобы иметь возможность запускать приложение Spring Boot, вам необходимо сначала его собрать.
Из папки проекта, которая содержит файл pom.xml выполняем комманду 

mvn clean package

Исполняемый JAR-файл Spring Boot делает процесс запуска очень простым:
Достаточно выполнить комманду :

java -jar target/person.jar

Swagger доступен по адресу:
http://localhost:8081/api/swagger-ui/index.html

Для запуска как сервис под Ubuntu:

1) Копируем target/person.jar в какую либу директорию, далее "/path/to/",
создаем символическую ссылку на исполняемый файл JAR.

$ sudo ln -s /path/to/person.jar /etc/init.d/person


Вы должны использовать полный путь к вашему исполняемому файлу JAR, иначе символическая ссылка не будет работать должным образом. 
Эта ссылка позволяет запустить приложение как сервис:
Сценарий поддерживает стандартные сервисные команды start , stop , restart и status.


2. Создаем скрипт с именем person.service, не забываем изменить /path/to/


[Unit]
Description=Person Test application
After=syslog.target
[Service]
ExecStart=/usr/bin/java -jar /path/to/person.jar 
ExecStop=/bin/kill -INT $MAINPID
ExecReload=/bin/kill -TERM $MAINPID

# In case if it gets stopped, restart it immediately
Restart     = always

Type        = simple

[Install]
WantedBy=multi-user.target



и помещаем его в каталог /etc/systemd/system 

Запускаем

$ sudo person start



