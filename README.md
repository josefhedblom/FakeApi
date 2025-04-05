# Bygg ett REST API i Spring Boot för att generera slumpmässig data

Att bygga ett REST API i Spring Boot som genererar slumpmässig fake data
kan vara både roligt och lärorikt. I denna artikel kommer vi att skapa
ett API som gör just det genom att använda FakerData-biblioteket för att
generera falska användardata som namn, e-post och ålder. API:t är
utformat för att både låta dig själv testa API-anrop och ge andra
möjlighet att öva på att göra API-anrop utan att behöva använda en
webbplats med komplicerad dokumentation.

## 1. Introduktion

I denna guide kommer vi att skapa ett Spring Boot API som:

- Genererar och returnerar fake data varje gång en GET-request görs. -
  Skapar ny fake data varje gång en ny GET-request görs via en särskild
  endpoint.

Syftet med detta API är att ge utvecklare och testare möjlighet att öva
på API-anrop och få tillgång till realistiska men slumpmässiga data utan
att behöva skapa en egen databas eller använda externa tjänster med
komplicerad dokumentation.

## 2. Teknologier vi kommer att använda

För att bygga detta API använder vi följande teknologier och bibliotek:

- **Java 17+** – Programmeringsspråket vi använder för backend. -
  **Spring Boot** – Ramverk för att snabbt skapa webbtjänster. - **Faker**
  – Bibliotek som genererar slumpmässig fake data. - **Maven** –
  Byggverktyg för att hantera beroenden.

## 3. Projektstruktur

När vi har satt upp vårt Spring Boot-projekt kommer det att ha följande
struktur:

```plaintext 
fake-api-project/
├── src/
│   ├── main/
│   │   ├── java/com/example/fakeapi/
│   │   │   ├── controllers/
│   │   │   │   ├── PeopleController.java
│   │   │   ├── models/
│   │   │   │   ├── People.java
│   │   │   ├── services/
│   │   │   │   ├── FakerGenerator.java
│   ├── resources/
│   │   ├── application.properties
├── pom.xml
```

## 4. Sätta upp projektet

### Steg 1: Skapa ett Spring Boot-projekt

Det enklaste sättet att skapa ett nytt Spring Boot-projekt är via Spring
Initializr.

Välj följande beroenden:

- **Spring Web** (för att skapa REST API:t)

Ladda ner projektet, extrahera det och öppna det i din IDE (t.ex.
IntelliJ IDEA eller VS Code).

## 5. Skapa REST API:t

### Steg 1: Skapa en modell för People

Skapa en ny fil `People.java` i `models`-paketet:

```java package com.example.fakeapi.models;

public record People(String firstName, String lastName, String fullName,
String email) { } 
```

### Steg 2: Skapa en FakerGenerator för att generera data

Skapa en ny fil `FakerGenerator.java` i `services`-paketet:

```java package com.example.fakeapi.services;

import com.example.fakeapi.models.People; import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList; import java.util.List;

@Service public class FakerGenerator {

    public List<People> generateData() { Faker faker = new Faker();
    List<People> peopleData = new ArrayList<>();

        for (int i = 0; i < 100; i++) { String firstName =
        faker.name().firstName(); String lastName =
        faker.name().lastName(); String fullName = firstName + " " +
        lastName; String email = firstName.toLowerCase() + "." +
        lastName.toLowerCase() + "@example.com"; peopleData.add(new
        People(firstName, lastName, fullName, email)); }

        return peopleData; } } 
```

### Steg 3: Skapa en controller

Skapa en ny fil `PeopleController.java` i `controllers`-paketet:

```java package com.example.fakeapi.controllers;

import com.example.fakeapi.models.People; import
com.example.fakeapi.services.FakerGenerator; import
org.springframework.web.bind.annotation.GetMapping; import
org.springframework.web.bind.annotation.RequestMapping; import
org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/people") public class
PeopleController {

    private final FakerGenerator fakerGenerator;

    public PeopleController(FakerGenerator fakerGenerator) {
        this.fakerGenerator = fakerGenerator;
    }

    @GetMapping
    public List<People> getPeople() {
        return fakerGenerator.generateData();  // Returnerar genererad data }

        @GetMapping("/new") public List<People> generateNewPeople () {
            return fakerGenerator.generateData();  // Skapar och returnerar ny data vid anrop
        }
    }
}
```

## 6. Testa API:t

### Starta servern

För att starta servern, kör kommandot:

```bash mvn spring-boot:run ```

### Testa GET-request med cURL

För att hämta den genererade datan via `/api/people` kan du använda
följande kommando:

```bash curl -X GET http://localhost:8080/api/people ```

För att generera ny data vid varje anrop, använd `/api/people/new`:

```bash curl -X GET http://localhost:8080/api/people/new ```

## 7. Sammanfattning

Grattis! Du har nu skapat ett enkelt REST API i Spring Boot som
använder FakerData-biblioteket för att generera slumpmässig data. Vi har
gått igenom:

- Skapa ett Spring Boot-projekt
- Lägg till beroenden för att använda FakerData
- Skapa en REST-controller som genererar och returnerar data
- Testa API:t via cURL eller webbläsare

### Nästa steg?

- Lägg till databas-stöd för att spara genererad data.
- Implementera validering och autentisering för mer avancerade funktioner.
