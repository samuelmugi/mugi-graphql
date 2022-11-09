package com.mugigraphql.controller;

import com.mugigraphql.entity.Person;
import com.mugigraphql.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mugi
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/Person")
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public String addPerson(Person person) {
        return personService.addPerson(person);
    }

    @GetMapping
    public String getPerson(Person person) {
        return personService.addPerson(person);
    }


    @PutMapping
    public String updatePerson(Person person) {
        return personService.updatePerson(person);
    }

    @GetMapping
    public List<Person> listPersons() {
        return personService.listPersons();
    }
}
