FROM openjdk:21

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл в контейнер
COPY /target/productmanager-0.0.1-SNAPSHOT.jar backend.jar

LABEL authors="Shalnark0"

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "backend.jar"]
