# Bygg ett REST API i Spring Boot för att generera slumpmässig data

Att bygga ett REST API i Spring Boot som genererar slumpmässig fake data
kan vara både roligt och lärorikt. I denna artikel beskriver jag hur jag har skapat
ett API som gör just det genom att använda FakerData-biblioteket för att
generera falska användardata som namn och e-post. API:t är utvecklat för att låta 
nya utvecklare kunna öva på API-anrop med realistisk men slumpmässig data utan att behöva skapa 
en egen databas eller använda externa tjänster med komplicerad dokumentation.

## Teknologier kommer som har används

För att bygga detta API har jag användt följande teknologier och bibliotek:

- **Java 17+**
- **Spring Boot**
- **Faker**

## Projektstruktur

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

## Skapa REST API:t

### Skapa en modell för People

`People.java` i `models`

```java package com.example.fakeapi.models;

public record People(String firstName, String lastName, String fullName,
String email) { } 
```

### Skapa en FakerGenerator för att generera data

`FakerGenerator.java` i `services`

```java package com.example.FakeApi.services;

@Component
public class FakerGenerator {

  private List<People> cachedPeopleData = null;

  public List<People> generateData() {
    if (cachedPeopleData == null) {
      Faker faker = new Faker();
      List<People> peopleData = new ArrayList<>();

      for (int i = 0; i < 100; i++) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = firstName + " " + lastName;
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
        peopleData.add(new People(firstName, lastName, fullName, email));
      }

      cachedPeopleData = peopleData;
    }
    return cachedPeopleData;
  }

  public List<People> generateNewData() {
    Faker faker = new Faker();
    List<People> peopleData = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String fullName = firstName + " " + lastName;
      String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
      peopleData.add(new People(firstName, lastName, fullName, email));
    }
    return peopleData;
  }
}
```

### Skapa en controller

`PeopleController.java` i `controllers`

```java package com.example.FakerApi.controllers;
@RestController
@RequestMapping("/api/people")
public class PeopleController {

  private final FakerGenerator fakerGenerator;

  public PeopleController(FakerGenerator fakerGenerator) {
    this.fakerGenerator = fakerGenerator;
  }

  @GetMapping
  public List<People> getPeople() {
    return fakerGenerator.generateData();
  }

  @GetMapping("/new")
  public List<People> generateNewPeople() {
    return fakerGenerator.generateNewData();
  }
}

```

## Testa API:t

### Starta servern

För att starta servern, kör kommandot:

```bash mvn spring-boot:run ```

### Testa GET-request med cURL

För att hämta den genererade datan via `/api/people` kan du använda
följande kommando:

```bash curl -X GET http://localhost:3050/api/people ```

För att generera ny data vid varje anrop, använd `/api/people/new`:

```bash curl -X GET http://localhost:3050/api/people/new ```
