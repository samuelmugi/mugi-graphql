package com.mugigraphql.service;

import com.mugigraphql.entity.Person;
import com.mugigraphql.repos.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mugi
 */
@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;


    public String addPerson(Person newPerson) {
        var person = personRepository.save(newPerson);
        return "Person " + person + " saved succussefully";
    }

    public Person getPerson(int id) {
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

}
