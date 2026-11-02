# pobiera obraz bazowy Linuksa z Javą V25 i Mavenem
FROM maven:3.9.12-eclipse-temurin-25
# tworzy i ustawia /app jak katalog bieżący
WORKDIR /app
# kopijuje pom.xml do obecnego katalogu (.)
COPY pom.xml .
# kopijuje zawartość src do obrazu tworząc src w app
COPY src ./src
COPY src/main/resources/appsettings.docker.properties src/main/resources/appsettings.properties
# uruchamia proces budowania paczki JAR z użyciem Maven już wewnątrz obrazu
RUN mvn clean package
# instrukcja dla kontejnera mówiąca co uruchomić automatycznie po uruchomieniu kontejnera po go utworzeniu z obrazu
CMD ["java", "-jar", "target/order-management-1.0-SNAPSHOT.jar"]
