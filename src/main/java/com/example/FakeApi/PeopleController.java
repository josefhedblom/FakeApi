package com.example.FakeApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final FakerGenerater fakerGenerater;

    public PeopleController(FakerGenerater fakerGenerater) {
        this.fakerGenerater = fakerGenerater;
    }

    @GetMapping
    public List<People> getPeople() {
        return fakerGenerater.getPeopleData();
    }

    @GetMapping("/new")
    public List<People> generateNewPeople() {
        return fakerGenerater.generateNewData();
    }
}
