package com.example.FakeApi.controllers;


import com.example.FakeApi.models.People;
import com.example.FakeApi.services.FakerGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
