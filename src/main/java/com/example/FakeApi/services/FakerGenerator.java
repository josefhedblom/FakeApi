package com.example.FakeApi.services;

import com.example.FakeApi.models.People;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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