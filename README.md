# Github Repositories API

Prosta aplikacja REST API, która zwraca listę repozytoriów GitHub użytkownika (z wyłączeniem forków) wraz z informacjami o ich gałęziach (branches).

Wykonana w technologii:
- Java 21
- Quarkus 3
- JUnit 5

## Uruchomienie aplikacji

### Wymagania:
- Java 21
- Maven 3.9.x

### Jak uruchomić aplikację lokalnie?

Z poziomu głównego katalogu projektu wykonaj:

```
mvn clean compile quarkus:dev
```

Aplikacja domyślnie uruchomi się na porcie `8080`.

## API Endpoint

### Pobranie repozytoriów użytkownika

```
GET /repositories/{username}
```

Przykład poprawnej odpowiedzi (200 OK):

```json
[
  {
    "repositoryName": "bank-zbozowy-mvn",
    "ownerLogin": "BKomosinski",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "bf83fa661a0013782f1a8f83fd24dbb69033b6da"
      }
    ]
  },
  {
    "repositoryName": "SortingMadness",
    "ownerLogin": "BKomosinski",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "a9127e992372561cb7e8e50c488d63d7dd5ebeb1"
      }
    ]
  },
  {
    "repositoryName": "InterpolacjaHermite-a",
    "ownerLogin": "BKomosinski",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "5f4cedb397c390660845af8a24f81144aeea70c9"
      }
    ]
  },
  {
    "repositoryName": "akai-rekrutacja",
    "ownerLogin": "BKomosinski",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "8610cc5421e6151bbf9079b99b91d33ac8da8787"
      }
    ]
  },
  {
    "repositoryName": "Saper",
    "ownerLogin": "BKomosinski",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "add225c008933fd01e37b7c240d36597ee6a7fa6"
      }
    ]
  }
]
```

Przykład odpowiedzi, gdy użytkownik nie istnieje (404 Not Found):

```json
{
  "status": 404,
  "message": "User 'nonExistingUser123' not found"
}
```

## Uruchomienie testów integracyjnych

Testy uruchamiasz za pomocą polecenia:

```
mvn test
```

Testy integracyjne wykonują prawdziwe zapytania do GitHub API (bez użycia mocków).

## Struktura projektu

```
com.example
├── Application.java                 # Klasa startowa aplikacji Quarkus
├── config
│   └── GithubApiConfig.java         # Konfiguracja REST klienta do GitHub API
├── controller
│   └── RepositoryController.java    # Kontroler REST obsługujący endpointy HTTP
├── dto
│   ├── BranchDto.java               # DTO do gałęzi repozytoriów w odpowiedzi API
│   ├── ErrorResponse.java           # DTO dla błędów zwracanych przez API
│   ├── RepositoryResponse.java      # DTO repozytoriów w odpowiedzi API
│   └── github                       # DTO używane do komunikacji z GitHub API
│       ├── BranchFromGithub.java
│       └── RepositoryFromGithub.java
├── exception
│   ├── UserNotFoundException.java   # Dedykowany wyjątek dla nieistniejącego użytkownika
│   └── UserNotFoundExceptionMapper.java # Konwerter wyjątków na JSON-owe odpowiedzi HTTP
└── service
    └── GithubService.java           # Logika biznesowa aplikacji
```

## Dokumentacja używanego API GitHub

Wykorzystane jest oficjalne API GitHub w wersji v3:

- Dokumentacja: https://developer.github.com/v3
- Endpoint API: https://api.github.com

## Ważne uwagi dodatkowe

- Aplikacja nie obsługuje paginacji (zgodnie z wymaganiami zadania).
- Testy integracyjne wykonują rzeczywiste zapytania do GitHub API, więc może wystąpić limitowanie API (60 zapytań na godzinę bez tokena autoryzacyjnego).

**Autor rozwiązania:**  
Bartłomiej Komosiński