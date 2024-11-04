package com.shuttle_service.state;

import com.shuttle_service.model.Person;
import org.springframework.http.ResponseEntity;

public class InvalidPersonState implements PersonState {
    @Override
    public ResponseEntity<Person> handle(Person person) {
        // Return bad request response if the SUID is not provided
        return ResponseEntity.badRequest().body(null);
    }
}
