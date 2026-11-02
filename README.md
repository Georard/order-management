# Order Management System

Projekt zrealizowany w ramach kursu Narzędzia Programistyczne. Aplikacja konsolowa w języku Java służąca do wczytywania i automatycznego przetwarzania zamówień zapisanych w formacie JSON.

## Wykorzystane technologie i narzędzia
* **Java 25**
* **Apache Maven**: zarządzanie zależnościami oraz automatyzacja cyklu życia projektu.
* **Wtyczki Maven**: `maven-checkstyle-plugin` (statyczna analiza kodu i zgodność ze standardem Google), `maven-javadoc-plugin` (automatyczne generowanie dokumentacji API), `spring-boot-maven-plugin` (tworzenie wykonywalnej paczki JAR).
* **Docker & Docker Compose**: pełna konteneryzacja środowiska uruchomieniowego.
* **Biblioteki**: FasterXML Jackson (deserializacja JSON), Apache Commons Configuration (obsługa plików właściwości).

## Uruchomienie aplikacji (Docker)

Do uruchomienia aplikacji wystarczy zainstalowany **Docker** (nie jest wymagana lokalna instalacja Javy ani Mavena).

### 1. Zbudowanie obrazu
W głównym katalogu projektu (obok pliku `Dockerfile`) uruchom w terminalu:
```bash
docker build -t order-management:1 .
```

### 2. Konfiguracja katalogu z zamówieniami
Utwórz osobny folder roboczy (np. poza projektem), a w nim:
1. Podkatalog `orders` zawierający pliki zamówień `.json`.
2. Plik `compose.yaml` o następującej zawartości:
```yaml
name: "order-management-app"
services:
  app:
    image: order-management:1
    container_name: "order-management-container"
    volumes:
      - "./orders:/app/orders"
```
Lub wykorzystaj obecny w projekcie foleder user-orders.

### 3. Start kontenera
Otwórz terminal w utworzonym folderze roboczym (z plikiem `compose.yaml`) i wywołaj:
```bash
docker compose up
```
Aplikacja wczyta pliki z katalogu `./orders`, wypisze wygenerowane numery zamówień na standardowe wyjście i zakończy działanie.
