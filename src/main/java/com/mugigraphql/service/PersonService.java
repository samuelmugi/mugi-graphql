package com.mugigraphql.service;

import com.mugigraphql.entity.Person;
import com.mugigraphql.repos.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mugi
 */
@Service
@AllArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;


    public String addPerson(Person newPerson) {
        log.info("newPerson:-"+newPerson);
        var person = personRepository.save(newPerson);
        return "Person " + person + " saved succussefully";
    }

    public Person getPerson(Long id) {
        var person = personRepository.findById(id);
        return person.get();
    }

    public String updatePerson(Person newPerson) {
        var person = personRepository.save(newPerson);
        return "Person " + person + " saved succussefully";
    }

    public List<Person> listPersons() {
        var persons = personRepository.findAll();
        return persons;
    }

    public Person findByEmail(String email) {
        return  personRepository.findByEmail(email).get();
    }
}
