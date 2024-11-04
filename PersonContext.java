package com.shuttle_service.state;

import com.shuttle_service.model.Person;
import org.springframework.http.ResponseEntity;

public class PersonContext {
    private PersonState state;

    public void setState(PersonState state) {
        this.state = state;
    }

    public ResponseEntity<Person> createPerson(Person person) {
        return state.handle(person);
    }
}
