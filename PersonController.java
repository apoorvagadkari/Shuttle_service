package com.shuttle_service.controller;

import com.shuttle_service.model.Person;
import com.shuttle_service.repository.PersonRepository;
import com.shuttle_service.state.InvalidPersonState;
import com.shuttle_service.state.PersonContext;
import com.shuttle_service.state.ValidPersonState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        PersonContext context = new PersonContext();

        // Check if suid is provided
        if (person.getSuid() == null) {
            context.setState(new InvalidPersonState());
        } else {
            context.setState(new ValidPersonState(personRepository));
        }

        // Delegate the call to the current state
        return context.createPerson(person);
    }
}
