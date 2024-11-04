package com.shuttle_service.state;

import com.shuttle_service.model.Person;
import com.shuttle_service.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidPersonState implements PersonState {
    private final PersonRepository personRepository;

    public ValidPersonState(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<Person> handle(Person person) {
        // Save the person entity
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }
}
