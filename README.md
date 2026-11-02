# Order Management System

Projekt zrealizowany w ramach kursu Narzędzia Programistyczne (Politechnika Wrocławska). Aplikacja konsolowa w języku Java służąca do wczytywania i automatycznego przetwarzania zamówień zapisanych w formacie JSON.

## Wykorzystane technologie i narzędzia
* **Java 25**
* **Apache Maven**: zarządzanie zależnościami oraz automatyzacja cyklu życia projektu.
* **Wtyczki Maven**: `maven-checkstyle-plugin` (statyczna analiza kodu i zgodność ze standardem Google), `maven-javadoc-plugin` (automatyczne generowanie dokumentacji API), `spring-boot-maven-plugin` (tworzenie wykonywalnej paczki JAR).
* **Docker & Docker Compose**: pełna konteneryzacja środowiska uruchomieniowego.
* **Biblioteki**: FasterXML Jackson (deserializacja JSON), Apache Commons Configuration (obsługa plików właściwości).

## Uruchomienie aplikacji

Aplikacja jest w pełni skonteneryzowana. Do jej uruchomienia nie jest wymagane środowisko Java ani Maven, wystarczy zainstalowany Docker.

1. Upewnij się, że w folderze `./user-orders` (obok pliku `compose.yaml`) znajdują się pliki zamówień z rozszerzeniem `.json`.
2. Uruchom kontener za pomocą polecenia:
   ```bash
   docker compose up
