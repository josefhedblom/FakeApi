package com.example.FakeApi;

import lombok.Getter;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class FakerGenerater {

    private final List<People> peopleData;

    public FakerGenerater() {
        this.peopleData = createFakePeople();
    }

    public List<People> generateNewData() {
        return createFakePeople();
    }

    private List<People> createFakePeople() {
        List<People> data = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String fullName = firstName + " " + lastName;
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
            data.add(new People(firstName, lastName, fullName, email));
        }
        return data;
    }
}
